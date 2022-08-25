package com.alcatras.kahveapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcatras.kahveapp.databinding.ActivityAllCoffeBinding
import android.view.View
import com.alcatras.kahveapp.databinding.CoffelayoutforallcoffeBinding

class AllCoffeActivity : AppCompatActivity() {
    private lateinit var classList:ArrayList<Cardviewitem>
    private lateinit var binding: ActivityAllCoffeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllCoffeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        var americano=Cardviewitem(R.drawable.ammericano,"Americano","Americano, kahvenin içime hazır en saf hallerinden biri olan espressonun su ilave edilerek seyreltilmesiyle hazırlanan ve oldukça yaygın olarak tüketilen bir kahve türüdür.")
        var capucino=Cardviewitem(R.drawable.capuccino,"Cappucino","Cappuccino, espresso bazlı kahve çeşitlerinden biridir. Özellikle üst kısmında bulunan süt köpüğü ve hafif içimi sayesinde çok sevilir.")
        var espresso=Cardviewitem(R.drawable.espresso,"Espresso","Espresso, 1900'lü yıllarda İtalyanların keşfedip dünyaya tanıttığı, basınç gücünden yararlanılarak yapılan hızlı bir kahve demleme yöntemidir")
        var filtre=Cardviewitem(R.drawable.filtre,"Filtre","Filtre kahve, öğütülmüş kahve çekirdeklerinin sıcak suyla buluşturulup demlenmesi sonucu elde edilen kahve çeşididir. Demlenen kahve, kağıt ya da metal filtreden süzülerek posasından ayrıştırılır ve servise hazır hale gelir.")
        var latte=Cardviewitem(R.drawable.latte,"Latte","Latte, Espresso kahvesi ile birlikte sütün bir araya gelmesi ile oluşan İtalyan usulü bir kahve çeşitidir. Kelime anlamı Süt olan Lattenin asıl kullanım adı, Caffe Latte’dir.")

        var macchiato=Cardviewitem(R.drawable.macchiato,"Coffee Macchiato","Macchiato kahvesi espresso kahveden elde edilen bir kahvedir. Espresso kahve, süt köpüğüyle süslenir ve içimi daha yumuşak hale gelir.")
        var turk=Cardviewitem(R.drawable.turk,"Türk Kahvesi","Tarihçe. 1543 yılında Yemen Valisi Özdemir Paşa, lezzetine hayran kaldığı kahveyi İstanbul'a getirdi. Türkler tarafından bulunan yepyeni hazırlama metodu sayesinde kahve, güğüm ve cezvelerde pişirilerek Türk Kahvesi adını aldı.")
        classList= arrayListOf(turk,macchiato,latte,filtre,espresso,capucino,americano)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=Adapter(classList)



    }
    /*

     fun change(view:View,position:Int){


        var intent= Intent(this,SelectedCoffe::class.java)
        intent.putExtra("list",classList.get(position))
        startActivity(intent)

    }

     */




}