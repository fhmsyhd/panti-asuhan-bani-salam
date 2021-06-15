package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert.ACTION
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentAboutBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.gallery.GalleryActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member.AdministratorActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member.ChildrenActivity


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private var isFilterShow: Boolean = true
    val number = "6285222322669"
    val numberr = "085222322669"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        openAbout()
        btnContact()
        btnMenu()

        return binding.root
    }

    private fun btnContact(){
        binding.btnWhatsapp.setOnClickListener {
            openWhatsapp()
        }

        binding.btnSms.setOnClickListener {
            openSms()
        }

        binding.btnTelepon.setOnClickListener {
            openPhone()
        }
    }

    private fun btnMenu(){
        binding.btnChildren.setOnClickListener {
            changeActivity(ChildrenActivity::class.java)
        }

        binding.btnAdmin.setOnClickListener {
            changeActivity(AdministratorActivity::class.java)
        }

        binding.btnGallery.setOnClickListener {
            changeActivity(GalleryActivity::class.java)
        }

        binding.btnLocation.setOnClickListener {
            changeActivity(openMaps()::class.java)
        }
    }

    private fun openAbout(){
        clickContent(binding.tvJudulLatar, binding.tvIsiLatar)
        clickContent(binding.tvJudulVisi, binding.tvIsiVisi)
        clickContent(binding.tvJudulMisi, binding.tvIsiMisi)
    }

    private fun clickContent(textTitle: TextView, textContent: ViewGroup) {
        textTitle.setOnClickListener {
            if (isFilterShow) {
                showContent(textContent, true)
                textTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrow_up,
                        0
                )
                isFilterShow = false
            } else {
                isFilterShow = true
                showContent(textContent, false)
                textTitle.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrow_down,
                        0
                )
            }
        }
    }

    private fun showContent(text: ViewGroup, show: Boolean) {
        val transition = Slide(Gravity.TOP)
        transition.duration = 300
        transition.addTarget(text)
        //TransitionManager.beginDelayedTransition(binding.tvIsiLatar, transition)
        if (show) {
            text.visibility = View.VISIBLE
        } else {
            text.visibility = View.GONE
        }
    }

    private fun openWhatsapp(){
        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Assalamualaikum Wr. Wb. Panti Asuhan Bani salam. Saya dapat kontak panti dari aplikasi Bani Salam...")
                putExtra("jid", "${number}@s.whatsapp.net")
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
        smsIntent.putExtra("address", numberr)
        smsIntent.putExtra("sms_body", "Assalamualaikum Wr. Wb. Panti Asuhan Bani salam. Saya dapat kontak panti dari aplikasi Bani Salam...")
        startActivity(smsIntent)
    }

    private fun openPhone(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$numberr")
        startActivity(intent)
    }

    private fun openMaps(){
        val gmmIntentUri =
            Uri.parse("geo:0,0?q=Panti+Asuhan+Yatim+Bani+Salam")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
//        val intent = Intent(Intent.ACTION_VIEW)
//                startActivity(intent)
    }

    private fun changeActivity(activity: Class<*>){
        val intent = Intent(requireContext(), activity)
        startActivity(intent)
    }
}