package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_donation.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentDonationBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.SectionPagerAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.pantilain.DetailOrphangeActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.needs.NeedsActivity

class DonationFragment : Fragment() {

    private lateinit var binding: FragmentDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)

        val sectionPagerAdapter = SectionPagerAdapter(requireContext(), childFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.btnNeeds.setOnClickListener {
            val intent = Intent(requireContext(), NeedsActivity::class.java)
            startActivity(intent)
        }

        optionsMenu()
        
        return binding.root
    }

    private fun optionsMenu(){
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.info_donate -> {
                    val intent = Intent(requireContext(), NeedsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

}