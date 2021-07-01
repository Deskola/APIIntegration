package com.example.webintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {
    private lateinit var payment: Payment
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webview)
//        payment = Payment()
        operation()
    }

    fun operation(){
        val live = live
        val oid = oid
        val inv = inv
        val ttl = ttl
        val tel = tel
        val eml = eml
        val vid = vid
        val curr = curr
        val p1 = p1
        val p2 = p2
        val p3 = p3
        val p4 = p4
        val cbk = cbk
        val cst = cst
        val crl = crl

        val viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        val data = "$live$oid$inv$ttl$tel$eml$vid$curr$p1$p2$p3$p4$cbk$cst$crl"
        val hashString = HmacSha1Sig.calculateRFC210HMAC(data, hashKey)
        Log.i("Hash", hashString)
        coroutineScope.launch {
            val response = MarsApi.retrofitService.getURL(
                    live,oid,inv,ttl,tel,eml,vid,curr,p1,p2,p3,p4,cbk,cst,crl,hashString
            )

            val mimeType = "text/html"
            val htmlContent = response.body()
           // val encoding = Base64.encodeBase64String(htmlContent.toByteArray())
            val encoding = android.util.Base64.encodeToString(htmlContent?.toByteArray(), android.util.Base64.NO_PADDING)

            webView.settings.javaScriptEnabled = true

            if (htmlContent != null) {
                webView.loadDataWithBaseURL(null,htmlContent,mimeType,encoding,null)
            }

            Log.i("Response", response.body().toString())
        }

    }



//    fun createSignature(data: String, key: String): String {
//        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
//        val sha1Hmac = Mac.getInstance("HmacSHA1")
//        sha1Hmac.init(secretKey)
//
//        return toHexString(sha1Hmac.doFinal(data.toByteArray()))
//
//        //return  Base64.encodeBase64String(sha1Hmac.doFinal(data.toByteArray()))
//    }
//
//    private fun toHexString(byteArray: ByteArray): String{
//        val formatter = Formatter()
//        for (b in byteArray){
//            formatter.format("%02x",b)
//        }
//
//        return formatter.toString()
//    }

//    fun getHashForString(stringToHash: String): String {
//        val digest = MessageDigest.getInstance("SHA-1")
//        val result = digest.digest(stringToHash.toByteArray(Charsets.UTF_8))
//        val sb = StringBuilder()
//        for (b in result) {
//            sb.append(String.format("%02X", b))
//        }
//        return sb.toString()
//    }
}