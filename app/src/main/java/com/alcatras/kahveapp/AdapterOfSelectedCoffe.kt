package com.alcatras.kahveapp

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
/*
        holder.binding.coffeName.text=classList.get(position).name
        holder.binding.imageView.setImageResource(classList.get(position).image)
        //holder.binding.ratingBar.setRating(classList.get(position).rating.toFloat())
        holder.binding.explanation.text=classList.get(position).explanation
        /*
        holder.binding.ratingBar.setOnClickListener {
            if(holder.binding.ratingBar.getRating()==1f){
                holder.binding.ratingBar.setRating(1f)

            }
            else{
                holder.binding.ratingBar.setRating(0f)
            }
        }

         */

/*
        if(holder.binding.ratingBar.isActivated){
            holder.binding.ratingBar.setRating((1).toFloat())
        }

 */


        holder.binding.cardView.setOnClickListener {
           listener.onItemClick(position,classList[position],holder)




        }


/*
            var intent=Intent(holder.itemView.context,SelectedCoffe::class.java)
            intent.putExtra("list",classList.get(position))
            holder.itemView.context.startActivity(intent)

 */

 */








    }

    override fun getItemCount(): Int {
        return classList.size
    }




    }
