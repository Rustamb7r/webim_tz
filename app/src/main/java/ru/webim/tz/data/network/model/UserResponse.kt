package ru.webim.tz.data.network.model

import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("result")
    var result: String? = null
}