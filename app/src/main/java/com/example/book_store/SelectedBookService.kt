package com.example.book_store

import models.BookDTO

object SelectedBookService {
    private var selectedBook: BookDTO? = null
    public  var isEditMode = true;

    fun setSelectedBook(book: BookDTO) {
        selectedBook = book
    }

    fun getSelectedBook(): BookDTO? {
        return selectedBook
    }

}