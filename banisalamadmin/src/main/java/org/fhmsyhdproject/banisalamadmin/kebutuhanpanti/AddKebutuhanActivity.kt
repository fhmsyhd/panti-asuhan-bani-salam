package org.fhmsyhdproject.banisalamadmin.kebutuhanpanti

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.fhmsyhdproject.banisalamadmin.R
import org.fhmsyhdproject.banisalamadmin.data.ActivityDb.Companion.getInstance
import org.fhmsyhdproject.banisalamadmin.databinding.ActivityAddKebutuhanBinding

class AddKebutuhanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKebutuhanBinding

    private var selectedImageBitmap: Bitmap? = null
    private var uriUpload: Uri? = null
    private var urlDownload: String? = null
    private var imgChange: Boolean = false
    private lateinit var database: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private val viewModel: KebutuhanViewModel by lazy {
        val factory = KebutuhanViewModelFactory(getInstance())
        ViewModelProvider(this, factory).get(KebutuhanViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kebutuhan)
    }
}