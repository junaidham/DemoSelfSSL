package com.example.demoselfssl

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("status"  ) var status  : Int?               = null,
    @SerializedName("message" ) var message : String?            = null,
    @SerializedName("payload" ) var payload : ArrayList<Payload> = arrayListOf()
)

data class Payload (

    @SerializedName("subTransactionTypeList" ) var subTransactionTypeList : ArrayList<SubTransactionTypeList> = arrayListOf(),
    @SerializedName("name"                   ) var name                   : String?                           = null,
    @SerializedName("key"                    ) var key                    : String?                           = null

)

data class SubTransactionTypeList (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("key"  ) var key  : String? = null

)