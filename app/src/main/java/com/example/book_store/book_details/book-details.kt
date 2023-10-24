package com.example.book_store.book_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.book_store.BookImage
import com.example.book_store.Header
import com.example.book_store.R
import com.example.book_store.book1
import models.BookDTO
import java.util.Date


@Composable
fun RedirectButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.back), contentDescription = "add icon")
            Text(text = "Back")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BookDetails(book: BookDTO = book1) {
    Column {
        Header("View Book Details")
        RedirectButton(
            Modifier
                .padding(top = 16.dp)
                .padding(start = 16.dp)
        )
        Text(
            text = "Title: ${book.title}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        BookInfo(book)
        Text(text = "Brief Descrption:", modifier = Modifier.padding(16.dp))
        Text(text = book.desc, modifier = Modifier.padding(16.dp))
        ViewBooksControls()

    }
}

@Composable
fun BookInfo(book: BookDTO) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookImage(
            Modifier
                .width(140.dp)
                .height(223.dp)
        )
        BookList(
            genre = book.genre,
            author = book.author,
            publishedDate = book.publishedDate,
            price = book.price,
            available = book.available
        )

    }
}

@Composable
fun BookList(genre: String, author: String, publishedDate: Date, price: Number, available: Number) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(120.dp)) {
        Text(text = "Genre: $genre")
        Text(text = "Author: $author")
        Text(text = "Publication Year: $publishedDate")
        Text(text = "Price: $price$")
        Text(text = "Available: $available")
    }
}

@Composable
fun EditBtn() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.yellow))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.edit), contentDescription = "edit icon")
            Text(text = "Edit")
        }
    }
}

@Composable
fun DeleteBtn() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.delete), contentDescription = "delete icon")
            Text(text = "Delete")
        }
    }
}

@Composable
fun ViewBooksControls() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        DeleteBtn()
        EditBtn()
    }
}

