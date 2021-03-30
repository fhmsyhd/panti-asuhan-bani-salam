package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_donation.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentDonationBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.SectionPagerAdapter


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

        return binding.root
    }



}