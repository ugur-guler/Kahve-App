package com.alcatras.kahveapp.RoomDatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class CoffeItemClass(
    @ColumnInfo
    @SerializedName(value = "image")
    val coffeImage:String,
    @ColumnInfo
    @SerializedName(value = "name")
    val coffeName:String,
    @ColumnInfo
    @SerializedName(value = "explanation")val aboutCoffe:String,
    @ColumnInfo val recipe:String,
    @ColumnInfo val hardness:Float,
    @ColumnInfo val simplicity:Float,
    @ColumnInfo
    @SerializedName(value = "favourite")
    var isFavourite:Boolean

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null


}