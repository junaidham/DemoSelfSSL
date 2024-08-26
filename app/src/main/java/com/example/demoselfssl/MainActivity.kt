package com.example.demoselfssl

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.demoselfssl.net.*

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewResult = findViewById(R.id.tvResponse
        )

        findViewById<Button>(R.id.btnApi).setOnClickListener {
            fetchTransactions()
        }

        findViewById<Button>(R.id.button_post).setOnClickListener {
            postWallet()
        }

    }

    private fun fetchTransactions() {
        val retrofit = provideRetrofit()
        val apiService = provideApiService(retrofit)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTransactions()
                withContext(Dispatchers.Main) {
                    displayTransactions(response)


                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    textViewResult.text = "Error: ${e.message}"
                }
            }
        }
    }

    private fun displayTransactions(response: TransactionResponse) {
        val result = StringBuilder()
        result.append("Status: ${response.status}\n")
        result.append("Message: ${response.message}\n\n")

        response.payload.forEach { transactionType ->
            result.append("Transaction Type: ${transactionType.name} (${transactionType.key})\n")
            transactionType.subTransactionTypeList.forEach { subTransactionType ->
                result.append("  - ${subTransactionType.name} (${subTransactionType.key})\n")
            }
            result.append("\n")
        }

        textViewResult.text = result.toString()
    }




    private fun postWallet() {
        val retrofit = provideRetrofit()
        val apiService = provideApiService(retrofit)
        val request = WalletRequest(
            clientID = "DROID-COMTR-VER1",
            mobile = "9999912333",
            userType = "Agent"
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getWallet(request)
                withContext(Dispatchers.Main) {
                    displayWallet(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    textViewResult.text = "Error: ${e.message}"
                }
            }
        }
    }


    private fun displayWallet(response: WalletResponse) {
        val result = StringBuilder()
        result.append("Status: ${response.status}\n")
        result.append("Message: ${response.message}\n\n")
        result.append("Name: ${response.payload.name}\n")
        result.append("QR: ${response.payload.QR}\n")

        textViewResult.text = result.toString()
    }




}