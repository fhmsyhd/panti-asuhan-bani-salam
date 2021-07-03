package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Orphanage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.Constant.DB_ORPHANAGES

class AboutViewModel: ViewModel() {
    // database
    private val dbOrphanage = FirebaseDatabase.getInstance().getReference(DB_ORPHANAGES)

    // buat orphanage
    private val _orphanages = MutableLiveData<List<Orphanage>>()
    val orphanages: LiveData<List<Orphanage>>
        get() = _orphanages

    // buat orphanage untuk realtime update
    private val _orphanage = MutableLiveData<Orphanage>()
    val orphanage: LiveData<Orphanage>
        get() = _orphanage

    fun fetchOrphanage() {
        dbOrphanage.addListenerForSingleValueEvent(orphanageValueEventListener)
    }

    private val orphanageValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                val orphanages = mutableListOf<Orphanage>()
                for (orphanageSnapshot in snapshot.children) {
                    val orphanage = Orphanage(
                        orphanageSnapshot.child("id").getValue(String::class.java),
                        orphanageSnapshot.child("nama").getValue(String::class.java),
                        orphanageSnapshot.child("nomor").getValue(String::class.java),
                        orphanageSnapshot.child("pemilik").getValue(String::class.java),
                        orphanageSnapshot.child("alamat").getValue(String::class.java),
                        orphanageSnapshot.child("gambar").getValue(String::class.java),
                        orphanageSnapshot.child("logo").getValue(String::class.java),
                        orphanageSnapshot.child("maps").getValue(String::class.java)
                    )
                    orphanage.id = orphanageSnapshot.key
                    orphanage.let { orphanages.add(it) }
                }
                _orphanages.value = orphanages
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    // realtime update
    fun orphanageRealtimeUpdate(){
        dbOrphanage.addChildEventListener(getorphanageRealtimeUpate)
    }

    private val getorphanageRealtimeUpate = object : ChildEventListener {
        override fun onChildAdded(orphanageSnapshot: DataSnapshot, previousChildName: String?) {
            val orphanage = Orphanage(
                orphanageSnapshot.child("id").getValue(String::class.java),
                orphanageSnapshot.child("nama").getValue(String::class.java),
                orphanageSnapshot.child("nomor").getValue(String::class.java),
                orphanageSnapshot.child("pemilik").getValue(String::class.java),
                orphanageSnapshot.child("alamat").getValue(String::class.java),
                orphanageSnapshot.child("gambar").getValue(String::class.java),
                orphanageSnapshot.child("logo").getValue(String::class.java),
                orphanageSnapshot.child("maps").getValue(String::class.java)
            )
            orphanage.id = orphanageSnapshot.key
            _orphanage.value = orphanage
        }

        override fun onChildChanged(orphanageSnapshot: DataSnapshot, previousChildName: String?) {
            val orphanage = Orphanage(
                orphanageSnapshot.child("id").getValue(String::class.java),
                orphanageSnapshot.child("nama").getValue(String::class.java),
                orphanageSnapshot.child("nomor").getValue(String::class.java),
                orphanageSnapshot.child("pemilik").getValue(String::class.java),
                orphanageSnapshot.child("alamat").getValue(String::class.java),
                orphanageSnapshot.child("gambar").getValue(String::class.java),
                orphanageSnapshot.child("logo").getValue(String::class.java),
                orphanageSnapshot.child("maps").getValue(String::class.java)
            )
            orphanage.id = orphanageSnapshot.key
            _orphanage.value = orphanage
        }

        override fun onChildRemoved(orphanageSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(orphanageSnapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    override fun onCleared() {
        super.onCleared()
        dbOrphanage.removeEventListener(orphanageValueEventListener)
    }
}