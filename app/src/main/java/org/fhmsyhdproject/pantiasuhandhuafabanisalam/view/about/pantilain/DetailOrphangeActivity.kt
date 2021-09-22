package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.pantilain

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_content.*
import kotlinx.android.synthetic.main.activity_detail_orphange.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Orphanage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityDetailOrphangeBinding

class DetailOrphangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailOrphangeBinding

    private var nomor = ""
    private var nomorWA = ""
    private var maps = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrphangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Panti Asuhan"
            setDisplayHomeAsUpEnabled(true)
        }

        val itemOrphanage = intent?.getParcelableExtra<Orphanage>("detailOrphanage")

        if (itemOrphanage != null) {
            initUI(itemOrphanage)
            btnMenu()
        }
    }

    private fun initUI(item: Orphanage){
        Glide.with(this).load(item.gambar)
            .into(img_panti)
        Glide.with(this).load(item.logo)
            .into(img_logo)
        tv_nama_panti.text = item.nama
        nomor = item.nomor.toString()
        nomorWA = item.nomorwa.toString()
        maps = item.maps.toString()
    }

    private fun btnMenu(){
        binding.btnWhatsapp.setOnClickListener {
            openWhatsapp()
        }

        binding.btnSms.setOnClickListener {
            openSms()
        }

        binding.btnTelepon.setOnClickListener {
            openPhone()
        }

        binding.btnLocation.setOnClickListener {
            openMaps()
        }
    }

    private fun openWhatsapp(){
        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Assalamualaikum Wr. Wb...")
                putExtra("jid", "${nomorWA}@s.whatsapp.net")
                type = "text/plain"
                setPackage("com.whatsapp")
            }
            startActivity(sendIntent)
        }catch (e: Exception){
            e.printStackTrace()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }

    private fun openSms(){
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.type = "vnd.android-dir/mms-sms"
        smsIntent.putExtra("address", nomor)
        smsIntent.putExtra("sms_body", "Assalamualaikum Wr. Wb...")
        startActivity(smsIntent)
    }

    private fun openPhone(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$nomor")
        startActivity(intent)
    }

    private fun openMaps(){
        val gmmIntentUri =
            Uri.parse(maps)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
//        val intent = Intent(Intent.ACTION_VIEW)
//                startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}