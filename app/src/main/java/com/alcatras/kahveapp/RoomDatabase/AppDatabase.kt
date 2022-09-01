package com.alcatras.kahveapp.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
@Database (entities = [CoffeItemClass::class], version = 3, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun coffeDao():CoffeDao

}