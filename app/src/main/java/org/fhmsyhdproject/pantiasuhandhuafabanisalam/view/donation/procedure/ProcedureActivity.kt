package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.procedure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityProcedureBinding

class ProcedureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcedureBinding

    // utils
    private var isFilterShow: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProcedureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Cara Berdonasi"
            setDisplayHomeAsUpEnabled(true)
        }

        openProcedure()

    }

    private fun openProcedure(){
        clickContent(binding.tvTfBank, binding.tvTitleTfBank, binding.tvContentTfBank)
        clickContent(binding.tvTfEwallet, binding.tvTitleTfEwallet, binding.tvContentTfEwallet)
        clickContent(binding.tvTfBarang, binding.tvTitleTfBarang, binding.tvContentTfBarang)
    }

    private fun clickContent(textTitle: ViewGroup, title: TextView, textContent: TextView) {
        textTitle.setOnClickListener {
            if (isFilterShow) {
                showContent(textContent, true)
                title.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_up,
                    0
                )
                isFilterShow = false
            } else {
                isFilterShow = true
                showContent(textContent, false)
                title.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_down,
                    0
                )
            }
        }
    }

    private fun showContent(text: TextView, show: Boolean) {
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}