package com.abdbzkn.cryptoappwithjava.service;

import com.abdbzkn.cryptoappwithjava.CryptoModel;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
public interface CryptoAPI {
    //baseurl/...
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //GET POST UPDATE DELETE
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<CryptoModel>> getData();
   // Call<List<CryptoModel>> getData();
}