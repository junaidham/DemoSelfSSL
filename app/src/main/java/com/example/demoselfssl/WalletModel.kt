package com.example.demoselfssl

data class WalletRequest(
    val clientID: String,
    val mobile: String,
    val userType: String
)

data class WalletResponse(
    val status: Int,
    val message: String,
    val payload: WalletPayload
){
    data class WalletPayload(
        val name: String,
        val QR: String
    )

}



