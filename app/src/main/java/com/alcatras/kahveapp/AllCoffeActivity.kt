package com.alcatras.kahveapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcatras.kahveapp.databinding.ActivityAllCoffeBinding
import androidx.room.Room
import com.alcatras.kahveapp.RoomDatabase.AppDatabase
import com.alcatras.kahveapp.RoomDatabase.CoffeItemClass
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AllCoffeActivity : AppCompatActivity() {
    private lateinit var coffeList: ArrayList<CoffeItemClass>
    private lateinit var binding: ActivityAllCoffeBinding
    val compositeDisposable=CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCoffeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        val coffeDao = db.coffeDao()
        /*

        var americano=CoffeItemClass(R.drawable.ammericano,"Americano","Americano, kahvenin içime hazır en saf hallerinden biri olan espressonun su ilave edilerek seyreltilmesiyle hazırlanan ve oldukça yaygın olarak tüketilen bir kahve türüdür.")
        var capucino=CoffeItemClass(R.drawable.capuccino,"Cappucino","Cappuccino, espresso bazlı kahve çeşitlerinden biridir. Özellikle üst kısmında bulunan süt köpüğü ve hafif içimi sayesinde çok sevilir.")
        var espresso=CoffeItemClass(R.drawable.espresso,"Espresso","Espresso, 1900'lü yıllarda İtalyanların keşfedip dünyaya tanıttığı, basınç gücünden yararlanılarak yapılan hızlı bir kahve demleme yöntemidir")
        var filtre=CoffeItemClass(R.drawable.filtre,"Filtre","Filtre kahve, öğütülmüş kahve çekirdeklerinin sıcak suyla buluşturulup demlenmesi sonucu elde edilen kahve çeşididir. Demlenen kahve, kağıt ya da metal filtreden süzülerek posasından ayrıştırılır ve servise hazır hale gelir.")
        var latte=CoffeItemClass(R.drawable.latte,"Latte","Latte, Espresso kahvesi ile birlikte sütün bir araya gelmesi ile oluşan İtalyan usulü bir kahve çeşitidir. Kelime anlamı Süt olan Lattenin asıl kullanım adı, Caffe Latte’dir.")
        var macchiato=CoffeItemClass(R.drawable.macchiato,"Coffee Macchiato","Macchiato kahvesi espresso kahveden elde edilen bir kahvedir. Espresso kahve, süt köpüğüyle süslenir ve içimi daha yumuşak hale gelir.")
        var turk=CoffeItemClass(R.drawable.turk,"Türk Kahvesi","Tarihçe. 1543 yılında Yemen Valisi Özdemir Paşa, lezzetine hayran kaldığı kahveyi İstanbul'a getirdi. Türkler tarafından bulunan yepyeni hazırlama metodu sayesinde kahve, güğüm ve cezvelerde pişirilerek Türk Kahvesi adını aldı.")
        coffeList= arrayListOf<CoffeItemClass>(americano,capucino,espresso,filtre,latte,macchiato,turk)
*/


        compositeDisposable.add(
            coffeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()


        )
        /*

            coffeList = coffeDao.getAll()
            binding.recyclerView.layoutManager = LinearLayoutManager(this@AllCoffeActivity)
            binding.recyclerView.adapter = Adapter(coffeList, object : Adapter.Listener {
                override fun onItemClick(
                    position: Int,
                    item: CoffeItemClass,
                    holder: Adapter.ClassHolder
                ) {
                    val intent = Intent(holder.itemView.context, SelectedCoffe::class.java)
                    holder.itemView.context.startActivity(intent)
                }
            }
            )

         */
        fun take() {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@AllCoffeActivity)
            binding.recyclerView.adapter = AdapterOfSelectedCoffe(coffeList, object : AdapterOfSelectedCoffe.Listener {
                override fun onItemClick(
                    position: Int,
                    item: CoffeItemClass,
                    holder: AdapterOfSelectedCoffe.ClassHolder
                ) {
                    val intent = Intent(holder.itemView.context, SelectedCoffeActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }
            }
            )


            /*

     fun change(view:View,position:Int){


        var intent= Intent(this,SelectedCoffe::class.java)
        intent.putExtra("list",classList.get(position))
        startActivity(intent)



    }

     */
        }
    }
}






