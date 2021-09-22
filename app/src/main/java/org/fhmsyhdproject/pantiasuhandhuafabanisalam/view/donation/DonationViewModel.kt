package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Bank
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Needs
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.Constant

class DonationViewModel: ViewModel() {
    // database
    private val dbNeeds = FirebaseDatabase.getInstance().getReference(Constant.DB_NEEDS)
    private val dbBank = FirebaseDatabase.getInstance().getReference(Constant.DB_BANK)

    // buat needs
    private val _needies = MutableLiveData<List<Needs>>()
    val needies: LiveData<List<Needs>>
        get() = _needies

    // buat needs untuk realtime update
    private val _needs = MutableLiveData<Needs>()
    val needs: LiveData<Needs>
        get() = _needs

    // buat bank
    private val _banks = MutableLiveData<List<Bank>>()
    val banks: LiveData<List<Bank>>
        get() = _banks

    // buat bank untuk realtime update
    private val _bank = MutableLiveData<Bank>()
    val bank: LiveData<Bank>
        get() = _bank

    fun fetchNeeds() {
        dbNeeds.addListenerForSingleValueEvent(needsValueEventListener)
    }

    private val needsValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                val needies = mutableListOf<Needs>()
                for (needsSnapshot in snapshot.children) {
                    val needs = Needs(
                        needsSnapshot.child("id").getValue(String::class.java),
                        needsSnapshot.child("title").getValue(String::class.java),
                        needsSnapshot.child("content").getValue(String::class.java),
                        needsSnapshot.child("image").getValue(String::class.java),
                        needsSnapshot.child("date").getValue(String::class.java),
                        needsSnapshot.child("source").getValue(String::class.java)
                    )
                    needs.id = needsSnapshot.key
                    needs.let { needies.add(it) }
                }
                _needies.value = needies
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    // realtime update
    fun needsRealtimeUpdate(){
        dbNeeds.addChildEventListener(getneedsRealtimeUpate)
    }

    private val getneedsRealtimeUpate = object : ChildEventListener {
        override fun onChildAdded(needsSnapshot: DataSnapshot, previousChildName: String?) {
            val needs = Needs(
                needsSnapshot.child("id").getValue(String::class.java),
                needsSnapshot.child("title").getValue(String::class.java),
                needsSnapshot.child("content").getValue(String::class.java),
                needsSnapshot.child("image").getValue(String::class.java),
                needsSnapshot.child("date").getValue(String::class.java),
                needsSnapshot.child("source").getValue(String::class.java)
            )
            needs.id = needsSnapshot.key
            _needs.value = needs
        }

        override fun onChildChanged(needsSnapshot: DataSnapshot, previousChildName: String?) {
            val needs = Needs(
                needsSnapshot.child("id").getValue(String::class.java),
                needsSnapshot.child("title").getValue(String::class.java),
                needsSnapshot.child("content").getValue(String::class.java),
                needsSnapshot.child("image").getValue(String::class.java),
                needsSnapshot.child("date").getValue(String::class.java),
                needsSnapshot.child("source").getValue(String::class.java)
            )
            needs.id = needsSnapshot.key
            _needs.value = needs
        }

        override fun onChildRemoved(needsSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(needsSnapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    fun fetchBank() {
        dbBank.addListenerForSingleValueEvent(bankValueEventListener)
    }

    private val bankValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                val banks = mutableListOf<Bank>()
                for (bankSnapshot in snapshot.children) {
                    val bank = Bank(
                        bankSnapshot.child("id").getValue(String::class.java),
                        bankSnapshot.child("name").getValue(String::class.java),
                        bankSnapshot.child("number").getValue(String::class.java),
                        bankSnapshot.child("image").getValue(String::class.java)
                    )
                    bank.id = bankSnapshot.key
                    bank.let { banks.add(it) }
                }
                _banks.value = banks
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    // realtime update
    fun bankRealtimeUpdate(){
        dbBank.addChildEventListener(getBankRealtimeUpate)
    }

    private val getBankRealtimeUpate = object : ChildEventListener {
        override fun onChildAdded(bankSnapshot: DataSnapshot, previousChildName: String?) {
            val bank = Bank(
                bankSnapshot.child("id").getValue(String::class.java),
                bankSnapshot.child("name").getValue(String::class.java),
                bankSnapshot.child("number").getValue(String::class.java),
                bankSnapshot.child("image").getValue(String::class.java)
            )
            bank.id = bankSnapshot.key
            _bank.value = bank
        }

        override fun onChildChanged(bankSnapshot: DataSnapshot, previousChildName: String?) {
            val bank = Bank(
                bankSnapshot.child("id").getValue(String::class.java),
                bankSnapshot.child("name").getValue(String::class.java),
                bankSnapshot.child("number").getValue(String::class.java),
                bankSnapshot.child("image").getValue(String::class.java)
            )
            bank.id = bankSnapshot.key
            _bank.value = bank
        }

        override fun onChildRemoved(bankSnapshot: DataSnapshot) {
        }

        override fun onChildMoved(bankSnapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    override fun onCleared() {
        super.onCleared()
        dbNeeds.removeEventListener(needsValueEventListener)
        dbBank.removeEventListener(bankValueEventListener)
    }
}