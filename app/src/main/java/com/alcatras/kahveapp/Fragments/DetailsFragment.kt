package com.alcatras.kahveapp.Fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alcatras.kahveapp.R
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass

import com.alcatras.kahveapp.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {
    private lateinit var binding:FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args=this.arguments
        val data= args?.get("data") as CoffeItemClass
        fromUrl(data.coffeImage)
        binding.tvExplanationOfCoffe.text = data.aboutCoffe
        binding.tvRecipeOfCoffe.text = data.recipe
        binding.hardness.setRating(data.hardness)
        binding.simplicity.setRating(data.simplicity)
        binding.tvcoffeName.text = data.coffeName
        binding.tvRecipeOfCoffe.movementMethod= ScrollingMovementMethod()
        binding.tvExplanationOfCoffe.movementMethod= ScrollingMovementMethod()
    }
    fun fromUrl(url:String){
        Glide.with(requireContext())
            .load(url)
            .into(binding.imageView)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
}