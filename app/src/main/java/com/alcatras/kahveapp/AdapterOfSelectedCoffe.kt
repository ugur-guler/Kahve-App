package com.alcatras.kahveapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.databinding.CoffelayoutforallcoffeBinding


class AdapterOfSelectedCoffe(val classList: List<CoffeItemClass>, val listener: Listener) : RecyclerView.Adapter<AdapterOfSelectedCoffe.ClassHolder>() {
    interface Listener {
        fun onItemClick(position: Int, item: CoffeItemClass, holder: ClassHolder)
    }


    class ClassHolder(val binding: CoffelayoutforallcoffeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        val binding = CoffelayoutforallcoffeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClassHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {


        holder.binding.coffeName.text=classList.get(position).coffeName
        holder.binding.imageView.setImageBitmap(toBitmap(classList.get(position).image))
        //holder.binding.ratingBar.setRating(classList.get(position).rating.toFloat())
        holder.binding.tvExplanationOfCoffe.text=classList.get(position).aboutCoffe
        holder.binding.cardView.setOnClickListener {
            listener.onItemClick(position,classList[position],holder)

        }

    }

    override fun getItemCount(): Int {
        return classList.size
    }
    fun toBitmap(byteArray: ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }




    }
