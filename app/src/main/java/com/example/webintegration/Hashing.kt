package com.example.webintegration

import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SignatureException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacSha1Sig{
    private var HMAC_SHA1_ALGO = "HmacSHA1"

    private fun toHexString(byteArray: ByteArray): String{
        val formatter = Formatter()
        for (b in byteArray){
            formatter.format("%02x",b)
        }

        return formatter.toString()
    }

    @Throws(SignatureException::class, NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun calculateRFC210HMAC(data: String, key: String): String{
        val signatureKey = SecretKeySpec(key.toByteArray(), HMAC_SHA1_ALGO)
        val mac = Mac.getInstance(HMAC_SHA1_ALGO)
        mac.init(signatureKey)

        return toHexString(mac.doFinal(data.toByteArray()))
    }

}