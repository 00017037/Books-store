package models

import com.google.gson.annotations.SerializedName

data class BookFromResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String,
)

