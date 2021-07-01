package com.example.webintegration

import retrofit2.Response
import retrofit2.http.*

interface MarsApiServiceInterface {
    enum class MarsApiFilter(val value: String){
        SHOW_RENT("rent"),
        SHOW_BUY("buy"),
        SHOW_ALL("all")
    }

    @FormUrlEncoded
    @POST("ke/")
    suspend  fun getURL(
       @Field("live") live: String,
       @Field("oid") oid: String,
       @Field("inv") inv: String,
       @Field("ttl") ttl: String,
       @Field("tel") tel: String,
       @Field("eml") eml: String,
       @Field("vid") vid: String,
       @Field("curr") curr: String,
       @Field("p1") p1: String,
       @Field("p2") p2: String,
       @Field("p3") p3: String,
       @Field("p4") p4: String,
       @Field("cbk") cbk: String,
       @Field("cst") cst: String,
       @Field("crl") crl: String,
       @Field("hsh") hsh: String,
    ):Response<String>
}