package com.example.book_store.api

import models.BookFromResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("records/all")
    suspend fun getAllBoks(@Query("student_id") student_id: String):
            MyListResponse<BookFromResponse>

}