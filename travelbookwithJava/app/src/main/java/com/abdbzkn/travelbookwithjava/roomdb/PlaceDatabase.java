package com.abdbzkn.travelbookwithjava.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.abdbzkn.travelbookwithjava.model.Place;

@Database(entities = {Place.class},version = 1)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placedao();
}
