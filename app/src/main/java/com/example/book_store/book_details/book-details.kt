package com.example.book_store.book_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.book_store.R
import com.example.book_store.book1
import models.BookDTO


@Preview(showBackground = true)
@Composable()
fun  RedirectButton(){
    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green))) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.back), contentDescription = "add icon")
            Text(text = "Back")
        }

    }
}

@Composable
fun BookDetails(book:BookDTO= book1){
    Column {
        Text(text = "Title: ${book.title}")

    }
}

@Composable
fun Bookinfo(book: BookDTO){
     Row {

     }
}