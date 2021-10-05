package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentTransferEwalletBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.DownloadImage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.payment.PaymentMidtrans
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.needs.NeedsActivity
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class TransferEwalletFragment : Fragment() {

    private lateinit var binding: FragmentTransferEwalletBinding
    var myDownloadId: Long = 0

    private var uriImage = "https://firebasestorage.googleapis.com/v0/b/peduli-rumah-yatim.appspot.com/o/img%2Fcode_qr.jpg?alt=media&token=f628dd00-5b52-4b2d-92a5-671f26a1e0c5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransferEwalletBinding.inflate(inflater, container, false)

        binding.btnDonasi.setOnClickListener {
            val intent = Intent(requireContext(), PaymentMidtrans::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}