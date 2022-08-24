package com.alcatras.kahveapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcatras.kahveapp.databinding.ActivityAllCoffeBinding

class AllCoffeActivity : AppCompatActivity() {
    private lateinit var classList:ArrayList<Cardviewitem>
    private lateinit var binding: ActivityAllCoffeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllCoffeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)




    }
}