package com.example.book_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import models.BookDTO
import models.Movie
import java.sql.Date


val book1 = BookDTO(
    1,
    "Harry Potter",
    "A little boy with a little girl",
    author = "J.K.Rolling",
    available = 12,
    genre = "Fantasy",
    price = 375,
    publishedDate = Date(12, 3, 3)
)
val sampleBooks: List<BookDTO> = listOf(
    BookDTO(
        1,
        "To Kill a Mockingbird",
        "A novel by Harper Lee",
        Date(1960, 7, 11),
        "Harper Lee",
        "Fiction",
        14.99,
        15
    ),
    BookDTO(
        2,
        "1984",
        "A dystopian novel by George Orwell",
        Date(1949, 6, 8),
        "George Orwell",
        "Science Fiction",
        12.99,
        10
    ),
    BookDTO(
        3,
        "Pride and Prejudice",
        "A classic novel by Jane Austen",
        Date(1813, 1, 28),
        "Jane Austen",
        "Romance",
        11.99,
        20
    ),
    BookDTO(
        4,
        "The Great Gatsby",
        "A novel by F. Scott Fitzgerald",
        Date(1925, 4, 10),
        "F. Scott Fitzgerald",
        "Fiction",
        13.99,
        8
    ),
    BookDTO(
        5,
        "Moby-Dick",
        "A novel by Herman Melville",
        Date(1851, 10, 18),
        "Herman Melville",
        "Adventure",
        14.49,
        12
    ),
    BookDTO(
        6,
        "The Catcher in the Rye",
        "A novel by J.D. Salinger",
        Date(1951, 7, 16),
        "J.D. Salinger",
        "Fiction",
        12.49,
        7
    )
)

@Preview(showBackground = true)
@Composable()
fun Books(books:List<BookDTO> = sampleBooks){
    LazyColumn(){
        items(books){
            book->
            BookItem(book)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable()
fun BookItem(book: BookDTO = book1) {
    Row(modifier = Modifier.fillMaxWidth()) {
        BookImage()
        BookDetails(
            title = book.title,
            author = book.author,
            price = book.price,
            remaining = book.available
        )
        ViewButton()

    }
}

@Composable()
fun BookImage() {
    Box(
        modifier = Modifier
            .width(66.dp)
            .height(100.dp)
    ) {
        AsyncImage(
            model = "https://images.booksense.com/images/403/353/9780590353403.jpg",
            contentDescription = "The book image cover",
            error = painterResource(id = R.drawable.harry),
            placeholder = painterResource(id = R.drawable.harry),
        )

    }
}

@Composable()
fun BookDetails(title: String, author: String, price: Number, remaining: Number) {
    Column(modifier = Modifier.padding((8.dp))) {
        Text(text = "title: $title")
        Text(text = "author: $author")
        Text(text = "price: $price$")
        Text(text = "book left: $remaining")
    }

}

@Composable()
fun ViewButton() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Row {
            Image(painterResource(id = R.drawable.view), contentDescription = "add icon")
            Text(text = "View Book Details")
        }

    }
}