package com.alcatras.kahveapp.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.alcatras.kahveapp.R
import com.alcatras.kahveapp.RoomDatabase.AppDatabase
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import com.alcatras.kahveapp.databinding.CoffelayoutforallcoffeBinding
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class AdapterOfSelectedCoffe(private val classList: List<CoffeItemClass>, val listener: Listener, val context:Context) : RecyclerView.Adapter<AdapterOfSelectedCoffe.ClassHolder>() {

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
        val db = Room.databaseBuilder(this.context,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration()
            .build()
        val coffeDao = db.coffeDao()
        setImage(position,holder)

        holder.binding.ivfavouriteStars.setOnClickListener {
            classList[position].isFavourite=setFavourite(position)
            setImage(position, holder)
            coffeDao.update(classList.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
        holder.binding.cardView.setOnClickListener {
            listener.onItemClick(position,classList[position],holder
            )
        }

        fromUrl(classList[position].coffeImage,position,holder)
        holder.binding.coffeName.text= classList[position].coffeName
        holder.binding.tvExplanationOfCoffe.text= classList[position].aboutCoffe


    }

    override fun getItemCount(): Int {
        return classList.size
    }
    fun fromUrl(url:String,position: Int,holder:ClassHolder){
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.imageView)
    }
    fun setFavourite(position: Int):Boolean{
        classList.get(position).isFavourite = !classList.get(position).isFavourite
        return classList.get(position).isFavourite
    }
    fun setImage(position: Int,holder:ClassHolder){
        if(classList[position].isFavourite){
            holder.binding.ivfavouriteStars.setImageResource(R.drawable.kirmizi)
        }
        else{
            holder.binding.ivfavouriteStars.setImageResource(R.drawable.yildiz)
        }
    }
}
