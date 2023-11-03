package models

import com.google.gson.annotations.SerializedName

data class NewBookBody(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("phone")
    val phone: String,  // Phone is explicitly defined as a String
    @SerializedName("age")
    val age: Int,       // Age is explicitly defined as an Int
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)
