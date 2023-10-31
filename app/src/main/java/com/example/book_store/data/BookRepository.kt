package com.example.book_store.data

import android.util.Log
import com.example.book_store.api.MyListResponse
import com.example.book_store.api.RetrofitInstance
import models.BookDTO
import models.BookFromResponse


class BookRepository {
    suspend fun getBookList(): List<BookDTO> {
        val books = mutableListOf<BookDTO>()

        val response: MyListResponse<BookFromResponse> =
            RetrofitInstance.bookService.getAllBoks("17037")
        try {
            val booksFromResponse = response.data;
            Log.d("respsone,",booksFromResponse.toString())
            if (booksFromResponse != null) {
                for (movieFromResponse in booksFromResponse) {
                    if (movieFromResponse.title != null && !movieFromResponse.description.isNullOrEmpty() && movieFromResponse.date != null && !movieFromResponse.type.isNullOrEmpty() && movieFromResponse.price > 0 && movieFromResponse.age > 0 && !movieFromResponse.phone.isNullOrEmpty()) {
                        books.add(
                            BookDTO(
                                desc = movieFromResponse.description,
                                publishedDate = movieFromResponse.date,
                                genre = movieFromResponse.type,
                                price = movieFromResponse.price,
                                title = movieFromResponse.title,
                                available = movieFromResponse.age,
                                author = movieFromResponse.phone,
                                id = movieFromResponse.id

                            )
                        )
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return books

    }
}