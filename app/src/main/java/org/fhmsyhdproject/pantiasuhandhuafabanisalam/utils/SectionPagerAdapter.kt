package org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.TransferBankFragment
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.TransferEwalletFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.trasnfer_bank, R.string.scan_qr)
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getItem(position: Int): Fragment =
            when(position){
                0 -> TransferBankFragment()
                1 -> TransferEwalletFragment()
                else -> Fragment()
            }
}