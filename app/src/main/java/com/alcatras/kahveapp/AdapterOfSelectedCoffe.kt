package com.alcatras.kahveapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.alcatras.kahveapp.RoomDatabase.AppDatabase
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.databinding.CoffelayoutforallcoffeBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable


class AdapterOfSelectedCoffe(val classList: List<CoffeItemClass>, val listener: Listener,val context:Context) : RecyclerView.Adapter<AdapterOfSelectedCoffe.ClassHolder>() {
        val compositeDisposble=CompositeDisposable()
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
        val db=Room.databaseBuilder(context,
            AppDatabase::class.java,"database-name"
        ).fallbackToDestructiveMigration().build()
        val dao=db.coffeDao()

        holder.binding.ivfavouriteStars.setOnClickListener {
            var favouriteStatus=setFavourite(position)



                dao.updateFavourite(favouriteStatus,classList.get(position).id!!)


            if (favouriteStatus==0){
                holder.binding.ivfavouriteStars.setImageResource(R.drawable.yildiz)

            }
            else{
                holder.binding.ivfavouriteStars.setImageResource(R.drawable.kirmizi)
            }

        }
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
    fun setFavourite(position: Int):Int{
        if (classList.get(position).favourite==0){

            classList.get(position).favourite=1
        }
        else{
            classList.get(position).favourite=0
        }
        return classList.get(position).favourite
    }




    }
