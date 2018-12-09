package br.com.luan2.nennospizza.data.model

import com.google.gson.annotations.SerializedName

class BaseRequest {

    constructor()

    constructor(name: String?, message: String?) {
        this.name = name
        this.message = message
    }


    @SerializedName("named")
    var name: String? = null

    @SerializedName("message")
    var message: String? = null

    override fun toString(): String {
        return "BaseRequest{" +
                "name = '" + name + '\'' +
                ",message = '" + message + '\'' +
                "}"
    }
}