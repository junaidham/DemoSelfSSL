package com.example.demoselfssl.net

import com.example.demoselfssl.TransactionResponse
import com.example.demoselfssl.WalletRequest
import com.example.demoselfssl.WalletResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("transactions/list")
    suspend fun getTransactions(): TransactionResponse

    @POST("wallet/list")
    suspend fun getWallet(@Body request: WalletRequest): WalletResponse

}