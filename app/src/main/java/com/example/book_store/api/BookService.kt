package com.example.book_store.api

import models.BookFromResponse
import models.NewBookBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("records/all")
    suspend fun getAllBoks(@Query("student_id") student_id: String):
            MyListResponse<BookFromResponse>

    @POST("records?student_id=17037")
    suspend fun addBook(@Body body: NewBookBody): Response<NewBookBody>

    @DELETE("records/{id}?student_id=17037")
    suspend fun deleteBookByID(
        @Path("id") id: Int,
    ):MyDeleteResponse

    @PUT("records/{id}?student_id=17037")
    suspend fun updateBookByID(@Path("id") id: Int,@Body body: NewBookBody):MyUpdateResponse
}