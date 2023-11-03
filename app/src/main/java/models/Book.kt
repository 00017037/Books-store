package models

data class Movie(val name: String, val desc: String, val actors: List<String>, val budget: Int) {
}

data class BookDTO (
   val id: Int,
   val desc: String,
   val publishedDate:String,
   val title: String,
   val author: String,
   val genre: String,
   val price: Number,
   val available: Number
){}

data class NewBookDTO(
 val title: String,
 val desc: String,
 val publishedDate:String,
 val author: String,
 val genre: String,
 val price: Number,
 val available: Number
)