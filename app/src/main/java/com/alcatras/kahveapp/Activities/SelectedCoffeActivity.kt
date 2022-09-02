package com.alcatras.kahveapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.alcatras.kahveapp.Adapter.AdapterOfSelectedCoffe
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.databinding.ActivitySelectedCoffeBinding
import com.bumptech.glide.Glide

class SelectedCoffeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedCoffeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectedCoffeBinding.inflate(layoutInflater)
        val view=binding.root

        setContentView(view)
        val intent=intent
        var data=intent.getSerializableExtra("data") as CoffeItemClass

        fromUrl(data.coffeImage)
        binding.tvExplanationOfCoffe.text = data.aboutCoffe
        binding.tvRecipeOfCoffe.text = data.recipe
        binding.hardness.setRating(data.hardness)
        binding.simplicity.setRating(data.simplicity)
        binding.tvcoffeName.text = data.coffeName


        binding.tvRecipeOfCoffe.movementMethod=ScrollingMovementMethod()
        binding.tvExplanationOfCoffe.movementMethod=ScrollingMovementMethod()
    }
    fun fromUrl(url:String){
        Glide.with(applicationContext)
            .load(url)
            .into(binding.imageView)
    }


}