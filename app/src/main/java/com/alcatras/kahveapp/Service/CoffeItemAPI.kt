package com.alcatras.kahveapp.Service

import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import retrofit2.Call
import retrofit2.http.GET
//https://jsonkeeper.com/b/
// 4YR3
interface CoffeItemAPI {
    @GET("4YR3")
    fun getData(): Call<List<CoffeItemClass>>
}