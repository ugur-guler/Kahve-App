package com.alcatras.kahveapp.RoomDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CoffeDao {
    @Query("SELECT * FROM coffeitemclass")
    fun getAll(): Flowable<List<CoffeItemClass>>
    @Insert
    fun insertAll( coffe:ArrayList<CoffeItemClass>):Completable
    @Query("DELETE  FROM coffeitemclass")
    fun deleteAll():Completable
    @Update
    fun  update(coffeItemClass: CoffeItemClass):Completable

}