package com.alcatras.kahveapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.alcatras.kahveapp.databinding.ActivitySelectedCoffeBinding

class SelectedCoffe : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedCoffeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectedCoffeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.recipe.movementMethod=ScrollingMovementMethod()
        binding.explanation.movementMethod=ScrollingMovementMethod()

    }
}