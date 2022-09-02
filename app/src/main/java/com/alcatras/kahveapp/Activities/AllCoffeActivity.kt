package com.alcatras.kahveapp.Activities

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.CursorIndexOutOfBoundsException
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcatras.kahveapp.databinding.ActivityAllCoffeBinding
import androidx.room.Room
import com.alcatras.kahveapp.Adapter.AdapterOfSelectedCoffe
import com.alcatras.kahveapp.Fragments.ListedCoffeFragment
import com.alcatras.kahveapp.R
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
    private lateinit var binding: ActivityAllCoffeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCoffeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listedCoffeFragment=ListedCoffeFragment()
        val fragmentManager=supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.frameLayout,listedCoffeFragment).commit()
    }

}







