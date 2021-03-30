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
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentTransferEwalletBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.DownloadImage
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class TransferEwalletFragment : Fragment() {

    private lateinit var binding: FragmentTransferEwalletBinding
    var myDownloadId: Long = 0

    private var uriImage = "https://firebasestorage.googleapis.com/v0/b/peduli-rumah-yatim.appspot.com/o/img%2Fcode_qr.jpg?alt=media&token=f628dd00-5b52-4b2d-92a5-671f26a1e0c5"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransferEwalletBinding.inflate(inflater, container, false)

        binding.btnDownload.setOnClickListener {
            //getBitmapFromURL(uriImage)
            //DownloadImage(requireContext()).execute(uriImage)
            downloadImage()

        }

        var br = object:BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                var id = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id==myDownloadId){
                    Toast.makeText(requireContext(), "Image Downloaded", Toast.LENGTH_SHORT).show()
                }
            }
        }
        activity?.registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        return binding.root
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url
                .openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            Toast.makeText(requireContext(), "Image Downloaded", Toast.LENGTH_SHORT).show()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun downloadImage() {
        var request = DownloadManager.Request(
                Uri.parse("uriImage"))
                .setTitle("Test Download")
                .setDescription("Desc Download")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setAllowedOverMetered(true)

        var dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        myDownloadId = dm.enqueue(request)
    }

}