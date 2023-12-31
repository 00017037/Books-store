package com.example.book_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.book_store.data.BookRepository
import com.example.book_store.list.BookListViewModel
import models.BookDTO


val book1 = BookDTO(
    1,
    title = "Harry Potter",
   desc = "A little boy with a little girl",
    author = "J.K.Rolling",
    available = 12,
    genre = "Fantasy",
    price = 375,
    publishedDate ="2023-10-31",
)
val sampleBooks: List<BookDTO> = listOf(

    BookDTO(
        4,
        "The Great Gatsby",
        "A novel by F. Scott Fitzgerald",
        "2023-10-31",        "F. Scott Fitzgerald",
        "Fiction",
        13.99,
        8
    ),
    BookDTO(
        5,
        "Moby-Dick",
        "A novel by Herman Melville",
        "2023-10-31",        "Herman Melville",
        "Adventure",
        14.49,
        12
    ),
    BookDTO(
        6,
        "The Catcher in the Rye",
        "A novel by J.D. Salinger",
        "2023-10-31",
        "J.D. Salinger",
        "Fiction",
        12.49,
        7
    )
)


@Composable()
fun Books(

     navController: NavController,
     viewModel: BookListViewModel = BookListViewModel(
         BookRepository()
     ),
) {
    val books  by viewModel.booksLiveData.observeAsState()


    if(!books.isNullOrEmpty()){
        LazyColumn() {
            items(items=books!!, itemContent = { book ->
                BookItem(book,navController)
                Spacer(modifier = Modifier.height(16.dp))
            })
        }
    }

}


@Composable()
fun BookItem(book: BookDTO,navController: NavController ) {
    Row(modifier = Modifier.fillMaxWidth()) {
        BookImage(
            modifier = Modifier
                .width(66.dp)
                .height(100.dp)
        )
        BookDetails(
            title = book.title,
            author = book.author,
            price = book.price,
            remaining = book.available
        )
        ViewButton(book,navController)

    }
}

@Composable()
fun BookImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = "https://images.booksense.com/images/403/353/9780590353403.jpg",
            contentDescription = stringResource(id = R.string.book_cover),
            error = painterResource(id = R.drawable.harry),
            placeholder = painterResource(id = R.drawable.harry),
            modifier = modifier
        )

    }
}

@Composable()
fun BookDetails(title: String, author: String, price: Number, remaining: Number) {
    Column(modifier = Modifier.padding((8.dp))) {
        Text(text = stringResource(id = R.string.title) +": $title")
        Text(text = stringResource(id = R.string.author) +": $author")
        Text(text = stringResource(id = R.string.price)+": $price$")
        Text(text = stringResource(id = R.string.book_left)+ ": $remaining")
    }

}

@Composable()
fun ViewButton(bookBookDTO: BookDTO,navController: NavController) {
    Button(
        onClick = {

            SelectedBookService.setSelectedBook(bookBookDTO);
            navController.navigate("view-details")
        },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Row {
            Image(painterResource(id = R.drawable.view), contentDescription = "add icon")
            Text(text = stringResource(id = R.string.view_books_details))
        }

    }
}