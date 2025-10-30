package com.abdbzkn.artbookwithjava;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.abdbzkn.artbookwithjava.databinding.ActivityArtBinding;
import com.google.android.material.snackbar.Snackbar;
import java.io.ByteArrayOutputStream;

public class ArtActivity extends AppCompatActivity {
     private ActivityArtBinding binding;
     //bunları onCreate altına register etmem gerek.
     ActivityResultLauncher<Intent> activityResultLauncher; //galeriye gitmek için
     ActivityResultLauncher<String> permissionLauncher; //izni istemek için
    Bitmap selectedImage;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerLauncher();
        database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
        //new mi geldi old mu geldi control et
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if (info.equals("new")) {
//new art
            binding.nameText.setText("");
            binding.artistText.setText("");
            binding.yearText.setText("");
            binding.imageView.setImageResource(R.drawable.selectimage);
            binding.button.setVisibility(View.VISIBLE);

        }else {
//id yolladı degeri cekmeye calısıyor
            int artId = intent.getIntExtra("artId",0);
            binding.button.setVisibility(View.INVISIBLE);

            try {
                Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?",new String[] {String.valueOf(artId)});
                int artNameIx = cursor.getColumnIndex("artname");
                int painterNameIx = cursor.getColumnIndex("paintername");
                int yearIx = cursor.getColumnIndex("year");
                int imageIx = cursor.getColumnIndex("image");

                while(cursor.moveToNext()){
                    binding.nameText.setText(cursor.getString(artNameIx));
                    binding.artistText.setText(cursor.getString(painterNameIx));
                    binding.yearText.setText(cursor.getString(yearIx));
                    byte[] bytes = cursor.getBlob(imageIx);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    binding.imageView.setImageBitmap(bitmap);
                }
            cursor.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void save(View view){
        String name = binding.nameText.getText().toString();
        String artistName = binding.artistText.getText().toString();
        String year = binding.yearText.getText().toString();

        Bitmap smallImage = makeSmallerImage(selectedImage,300); //deneyerek bul 300 degisebiir.
        //veriye çevirmeliyiz bu kücük fotoyu
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray = outputStream.toByteArray(); //veri tabanına kaydetmeye hazırız...

        try {
           database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY,artname VARCHAR,paintername VARCHAR,year VARCHAR,image BLOB)");

            String sqlString = "INSERT INTO arts (artname,paintername,year,image) VALUES (?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            //direk içine koymak varkne niye böyle yaptım avantajı var : sqLiteStatement.bind yazınca cıkan şeyleri baglayabiliyorum
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,artistName);
            sqLiteStatement.bindString(3,year);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(ArtActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //YANİ BÜTÜN AKTİVİTELERİ KAPAT VE YENİ AÇTIGIM AKTİVİTEYİ ÇALIŞTIR DEMEK.
        startActivity(intent);
/* böyle de yapılabilir ancak kullanışsız
        finish(); //main aktivitede finish çalıştırma çünkü bir şey kaydetmeden geri dönebilir kullanıcı.
        Intent intent = new Intent(ArtActivity.this,MainActivity.class);
        startActivity(intent);
*/
    }
    public Bitmap makeSmallerImage(Bitmap image, int maxsimumsize){
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if(bitmapRatio > 1){
            //landscape image
            width = maxsimumsize;
            height = (int) (width/bitmapRatio) ;
        } else {
            //portrait image
            height = maxsimumsize;
            width = (int) (height*bitmapRatio);
        }
        return image.createScaledBitmap(image,width,height,true);
    }
    public void selectImage(View view){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ){ //eğer 33 ve üstüyse bu olmalı
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES)){
                    //istemek zorundaysak kullanıcıya mesaj göndr ve tekrar iste çünkü uyg için gerekli
                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                        }
                    }).show();
                } else {
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                }
            } else {
                Intent intenToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intenToGallery);
            }

        } else{//33 altı
            //özet
            // izin varsa galeriye git yoksa istiycez ancak iki senaryo var birisinde zorunlu olarak acıklama göster iste digeri direk iste

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//request permission
//kullanıcıya mantık gösterip tekrarla reddederse o sebeple birdaha ayırıcaz snackbar falan göstericez.
                //kontrol edelim göstermek zorunda mı kalıcaz bakalım
                //buna tamamen işletim sistemi kendisi karar veriyor
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //istemek zorundaysak kullanıcıya mesaj göndr ve tekrar iste çünkü uyg için gerekli
                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //request permission tıkladı şimdi izni iste
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    }).show();

                } else {
                    // zprunda degilsek direk izni istememiz gerekiyor request permission
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                }

            } else {
//permission granted go gallery iznimiz var galeriye git (19 öncesi için sorgu yok direk dalıyoruz galeriye
                Intent intenToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //galeriye gidicem görsel pick edicem demek.
                //intenti nasıl kullanıcaz ve napıcaz seçtikten sonra onu yazmalıyım.
                activityResultLauncher.launch(intenToGallery);
            }
        }
    }
    private void registerLauncher(){

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    //kullanıcı bir şeyler seçtiyse sorun olmadıysa
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null){
                        Uri imageData = intentFromResult.getData();
                        //nerde oldugunu biliyorum artık
                        //artık direk içinde gösterebilirim aşagıda imageData nerde oldugunu soyluyor image ın ama bu işe yaramaz şuan bitmape çevirmeliyiz 0 1 ler ile olmalı foto ve datayı çekmekliyiz
                        //bu yüzden alttakini yorum satırına alıp imageData yani yerindeki fotoyu alıp bitmap yapıcam ve istedigim her şeyi ona uygulayabilicem
                        //binding.imageView.setImageURI(imageData);

                        try { //burda yazarkne hata alıyordum ilk başta çünkü sadece ust seviye tellerde gecerli bu satırlar az sonra tüm teller icin düzeltmeler yapıcam androidin vers kapsamaya çalısıcam

                           if(Build.VERSION.SDK_INT >= 28) { //28 ve üstü
                               ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageData);
                               selectedImage = ImageDecoder.decodeBitmap(source);
                               binding.imageView.setImageBitmap(selectedImage);
                           } else { //28 altı
                               selectedImage = MediaStore.Images.Media.getBitmap(ArtActivity.this.getContentResolver(),imageData);
                                binding.imageView.setImageBitmap(selectedImage);
                           }

                        }catch (Exception e){ //görsel ile alakalı hatalar uyg çökertmesin diye try and catch kulllanıyorum
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    //permission granted
                    Intent intenToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intenToGallery);
                }else {
                    //permission denied
                    Toast.makeText(ArtActivity.this,"Permission needed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}