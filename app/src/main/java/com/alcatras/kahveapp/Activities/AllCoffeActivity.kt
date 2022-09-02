package com.alcatras.kahveapp.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.CursorIndexOutOfBoundsException
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcatras.kahveapp.databinding.ActivityAllCoffeBinding
import androidx.room.Room
import com.alcatras.kahveapp.Adapter.AdapterOfSelectedCoffe
import com.alcatras.kahveapp.RoomDatabase.AppDatabase
import com.alcatras.kahveapp.RoomDatabase.CoffeDao
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.Service.CoffeItemAPI
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.Serializable

class AllCoffeActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    val gson = Gson()
    val baseUrl = "https://jsonkeeper.com/b/"
    private lateinit var coffeList: ArrayList<CoffeItemClass>
    private lateinit var binding: ActivityAllCoffeBinding


    val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = this.getSharedPreferences("com.alcatras.kahveapp.Activities",
            Context.MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        binding = ActivityAllCoffeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration()
            .build()
        val coffeDao = db.coffeDao()

        //loadData()
        binding.recyclerView.layoutManager=LinearLayoutManager(applicationContext)

        var a=sharedPreferences.getInt("value",0)
        if(a==0){
            loadData(coffeDao)
            a += 1
            sharedPreferences.edit().putInt("value",a).apply()

        }
        getData(coffeDao)


    }


    fun loadData(coffeDao:CoffeDao) {
        //internet permission is needed
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CoffeItemAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CoffeItemClass>> {
            override fun onResponse(
                call: Call<List<CoffeItemClass>>,
                response: Response<List<CoffeItemClass>>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        val model=ArrayList(it)
                        insertData(model,coffeDao)
                    }
                }
            }

            override fun onFailure(call: Call<List<CoffeItemClass>>, t: Throwable) {
                t.printStackTrace()
                println("fail")
            }
        })
    }
    fun insertData(coffeArray:ArrayList<CoffeItemClass>,coffeDao:CoffeDao){
        coffeDao.insertAll(coffeArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
    fun getData(coffeDao:CoffeDao){
        coffeDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                binding.recyclerView.adapter =
                    AdapterOfSelectedCoffe(it, object : AdapterOfSelectedCoffe.Listener {
                        override fun onItemClick(
                            position: Int,
                            item: CoffeItemClass,
                            holder: AdapterOfSelectedCoffe.ClassHolder
                        ) {
                            val intent = Intent(holder.itemView.context, SelectedCoffeActivity::class.java)
                            intent.putExtra("data",item )
                            holder.itemView.context.startActivity(intent)

                        }

                    },applicationContext)


            }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

private fun Intent.putExtra(s: String, item: CoffeItemClass) {

}







