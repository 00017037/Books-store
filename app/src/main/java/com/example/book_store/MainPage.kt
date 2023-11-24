package com.example.book_store

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun MainPage(navController: NavController){
    Column {
        Header(stringResource(id = R.string.main_page))
        subMenu(navController)
        Books(navController)
    }
}