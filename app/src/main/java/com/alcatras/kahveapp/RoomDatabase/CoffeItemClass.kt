package com.alcatras.kahveapp.RoomDatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class CoffeItemClass(
    @ColumnInfo val coffeImage:Int,
    @ColumnInfo val coffeName:String,
    @ColumnInfo val aboutCoffe:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}