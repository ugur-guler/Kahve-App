package com.alcatras.kahveapp.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.alcatras.kahveapp.Adapter.AdapterOfSelectedCoffe
import com.alcatras.kahveapp.R
import com.alcatras.kahveapp.RoomDatabase.AppDatabase
import com.alcatras.kahveapp.RoomDatabase.CoffeDao
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.Service.CoffeItemAPI
import com.alcatras.kahveapp.databinding.FragmentListedCoffeBinding
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListedCoffeFragment : Fragment() {
    private lateinit var binding: FragmentListedCoffeBinding
    lateinit var sharedPreferences : SharedPreferences
    val gson = Gson()
    val baseUrl = "https://jsonkeeper.com/b/"
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences=
            requireContext().getSharedPreferences("com.alcatras.kahveapp.Fragments",Context.MODE_PRIVATE)!!

        val db = Room.databaseBuilder(
            this.requireContext(),
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration()
            .build()
        val coffeDao = db.coffeDao()

        //loadData()
        binding.recyclerView.layoutManager= LinearLayoutManager(this.context)

        var a=sharedPreferences.getInt("value",0)
        if(a==0){
            loadData(coffeDao)
            a += 1
            sharedPreferences.edit().putInt("value",a).apply()

        }
        getData(coffeDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentListedCoffeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    fun loadData(coffeDao: CoffeDao) {
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
    fun insertData(coffeArray:ArrayList<CoffeItemClass>, coffeDao: CoffeDao){
        coffeDao.insertAll(coffeArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
    fun getData(coffeDao: CoffeDao){
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
                            val fragment=DetailsFragment()
                            val bundle=Bundle()
                            bundle.putSerializable("data",item)
                            fragment.arguments=bundle
                            fragmentManager?.beginTransaction()?.replace(R.id.frameLayout,fragment)?.commit()


                        }

                    },this.requireContext())


            }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}


