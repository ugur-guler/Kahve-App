package com.alcatras.kahveapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alcatras.kahveapp.databinding.CoffelayoutforallcoffeBinding

class Adapter(val classList : ArrayList<Cardviewitem>) : RecyclerView.Adapter<Adapter.ClassHolder>() {
    class ClassHolder(val binding : CoffelayoutforallcoffeBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        val binding = CoffelayoutforallcoffeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClassHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {

        holder.binding.name.text=classList.get(position).name
        holder.binding.imageView.setImageResource(classList.get(position).image)
        holder.binding.ratingBar.setRating(classList.get(position).rating.toFloat())
        holder.binding.explanation.text=classList.get(position).explanation
        holder.binding.ratingBar.setOnClickListener {
            if(holder.binding.ratingBar.getRating()==1f){
                holder.binding.ratingBar.setRating(1f)

            }
            else{
                holder.binding.ratingBar.setRating(0f)
            }
        }

/*
        if(holder.binding.ratingBar.isActivated){
            holder.binding.ratingBar.setRating((1).toFloat())
        }

 */

        holder.binding.cardView.setOnClickListener {


            var intent=Intent(holder.itemView.context,SelectedCoffe::class.java)
            holder.itemView.context.startActivity(intent)
            Singleton.myVar=classList.get(position)


        }

        /*
        holder.binding.coffeName.text=classList.get(position).name
        var a=classList.get(position).image

        holder.binding.imageView.setImageResource(a)
        holder.binding.information.text=classList.get(position).explanation
        holder.binding.ratingBar.rating=classList.get(position).rating.toFloat()
        /*
        holder.binding.recyclerRowTextView.text = landmarkList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailsActivity::class.java)
            intent.putExtra("landmark",landmarkList.get(position))
            //MySingleton.selectedLandmark = landmarkList.get(position))
            holder.itemView.context.startActivity(intent)
            }
         */

         */

    }

    override fun getItemCount(): Int {
        return classList.size
    }

}