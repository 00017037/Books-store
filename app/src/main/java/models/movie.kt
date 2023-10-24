package models

import java.sql.Date
import java.text.DateFormat

data class Movie(val name: String, val desc: String, val actors: List<String>, val budget: Int) {


}

data class BookDTO(
    val id: Number,
    val title: String,
    val desc: String,
    val publishedDate:Date,
    val author: String,
    val genre: String,
    val price: Number,
    val available: Number
){}