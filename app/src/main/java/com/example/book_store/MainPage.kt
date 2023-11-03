package com.example.book_store

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainPage(navController: NavController){
    Column {
        Header("Main Page")
        subMenu(navController)
        Books(navController)
    }
}