package org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.payment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.internal.service.Common.CLIENT_KEY
import com.google.android.material.snackbar.Snackbar
import com.midtrans.sdk.corekit.BuildConfig.BASE_URL
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import kotlinx.android.synthetic.main.fragment_transfer_ewallet.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityAmountBinding


class PaymentMidtrans: AppCompatActivity() {

    private lateinit var binding: ActivityAmountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SdkUIFlowBuilder.init()
            .setClientKey("SB-Mid-client-DTPCK8q-Sh577-zh")
            .setContext(applicationContext)
            .setTransactionFinishedCallback(object : TransactionFinishedCallback {
                override fun onTransactionFinished(result: TransactionResult) {
                    if (result.response != null) {
                        when (result.status) {
                            TransactionResult.STATUS_SUCCESS -> Toast.makeText(
                                applicationContext,
                                "Transaction Finished. ID: " + result.response.transactionId,
                                Toast.LENGTH_LONG
                            ).show()
                            TransactionResult.STATUS_PENDING -> Toast.makeText(
                                applicationContext,
                                "Transaction Pending. ID: " + result.response.transactionId,
                                Toast.LENGTH_LONG
                            ).show()
                            TransactionResult.STATUS_FAILED -> Toast.makeText(
                                applicationContext,
                                "Transaction Failed. ID: " + result.response.transactionId + ". Message: " + result.response.statusMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        result.response.validationMessages
                    } else if (result.isTransactionCanceled) {
                        Toast.makeText(applicationContext, "Transaction Canceled", Toast.LENGTH_LONG).show()
                    } else {
                        if (result.status.equals(
                                TransactionResult.STATUS_INVALID,
                                ignoreCase = true
                            )
                        ) {
                            Toast.makeText(applicationContext, "Transaction Invalid", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Transaction Finished with failure.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })
            .setMerchantBaseUrl("http://192.168.100.5/panti/ppresponses.php/")
//            .setMerchantBaseUrl("https://fhmsyhd.000webhostapp.com/index.php/")
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#32ad4a", "#2C9941", "#32ad4a"))
            .setLanguage("id")
            .buildSDK()

        binding.btnConfirm.setOnClickListener {

            val name = binding.etName.text
            val amount = binding.etAmount.text
            
            if (amount.isEmpty()) {
                showMessage("Jumlah donasi harus diisi!")
                refresh()
            } else if(amount.toString().toInt() < 10000) {
                showMessage("Mohon maaf, jumlah donasi minimal adalah Rp. 10.000")
            } else {
                var nama = name.toString()
                if (name.isEmpty()){
                    nama = "Hamba Allah"
                }
                val kalikan = 100000.toDouble()
                val transactionRequest = TransactionRequest(
                    "App-Panti-" + System.currentTimeMillis().toString() + "",
                    amount.toString().toDouble()
                )
                val detail = ItemDetails(
                    "NamaItemId",
                    amount.toString().toDouble(),
                    1,
                    "Donasi Panti"
                )
                val itemDetails = ArrayList<ItemDetails>()
                itemDetails.add(detail)
                uiKitDetail(transactionRequest, nama)
                transactionRequest.itemDetails = itemDetails
                MidtransSDK.getInstance().transactionRequest = transactionRequest
                MidtransSDK.getInstance().startPaymentUiFlow(this)
            }

//            val kalikan = 100000.toDouble()
//                val transactionRequest = TransactionRequest(
//                    "App-Panti-" + System.currentTimeMillis().toString() + "", kalikan
//                )
//                val detail = ItemDetails("NamaItemId", kalikan, 1, "Donasi Panti")
//                val itemDetails = ArrayList<ItemDetails>()
//                itemDetails.add(detail)
//                uiKitDetail(transactionRequest, name.toString())
//                transactionRequest.itemDetails = itemDetails
//                MidtransSDK.getInstance().transactionRequest = transactionRequest
//                MidtransSDK.getInstance().startPaymentUiFlow(this)

        }
    }

    fun uiKitDetail(transactionRequest: TransactionRequest, name: String){
        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = "Donatur"
//        customerDetails.phone = "00000"
//        customerDetails.firstName = name
//        customerDetails.lastName = ""
//        customerDetails.email = "email@gmail.com"

        val shippingAddress = ShippingAddress()
        shippingAddress.address = "Jl Terusan Buah Batu No 283 Dekat Tol Buah Batu Cipagalo"
        shippingAddress.city = "Bandung"
        shippingAddress.postalCode = "40287"
        customerDetails.shippingAddress = shippingAddress

        val billingAddress = BillingAddress()
        billingAddress.address = "Jl Terusan Buah Batu No 283 Dekat Tol Buah Batu Cipagalo"
        billingAddress.city = "Bandung"
        billingAddress.postalCode = "40287"
        customerDetails.billingAddress = billingAddress

        transactionRequest.customerDetails = customerDetails
    }

    private fun showMessage(messageResId: String) {
        val toast = Toast.makeText(
            this, messageResId,
            Toast.LENGTH_SHORT
        )
        toast.show()
    }

    private fun showSnackBar(messageSnackbar: String){
        Snackbar.make(window.decorView.rootView, messageSnackbar, Snackbar.LENGTH_LONG).show()
    }

    private fun refresh(){
        val name = binding.etName
        val amount = binding.etAmount

        name.setText("")
        amount.setText("")
    }
}