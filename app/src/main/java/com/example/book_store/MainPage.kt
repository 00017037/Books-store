package com.example.book_store

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun MainPage(){
    Column {
        Header("Main Page")
        subMenu()
        Books()
    }
}