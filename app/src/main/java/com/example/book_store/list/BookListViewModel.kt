package com.example.book_store.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.BookRepository
import kotlinx.coroutines.launch
import models.BookDTO

class BookListViewModel(private val booksRepository: BookRepository) : ViewModel() {

    val booksLiveData: MutableLiveData<List<BookDTO>> by lazy { MutableLiveData<List<BookDTO>>() }


    fun getAllBooks() {
        viewModelScope.launch {
            val books = booksRepository.getBookList()
            booksLiveData.value = books;
        }
    }

    init{
        getAllBooks()
    }

}