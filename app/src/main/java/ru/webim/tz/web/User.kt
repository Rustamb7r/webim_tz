package ru.webim.tz.web

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("result")
    var result: String? = null
}