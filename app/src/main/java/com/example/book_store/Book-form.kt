package com.example.book_store

import android.app.AlertDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.book_store.book_details.RedirectButton
import com.example.book_store.list.CreateDeleteUpdBookViewModel
import kotlinx.coroutines.launch
import models.BookDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable()
fun AddBookPage(navController: NavController) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val context = LocalContext.current
        Header("Add New Book")
        RedirectButton(Modifier.padding(top = 15.dp),navController)
        BookForm(CreateDeleteUpdBookViewModel())

    }
}

enum class Genre {
    Fantasy, ScienceFiction, Cartoon
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun BookForm(viewModel:CreateDeleteUpdBookViewModel) {
    val book = SelectedBookService.getSelectedBook();
    var isFormValid by remember { mutableStateOf(false) }

    var bookTitle by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0) }
    var booksAvailable by remember { mutableStateOf(0) }
    var genre by remember { mutableStateOf("Detective") }
    var publishedDate by remember { mutableStateOf(LocalDate.now()) }
    var briefDescription by remember { mutableStateOf("") }
    val context = LocalContext.current // Retrieve the context
    val popupControl by remember{ mutableStateOf(false) }
    LaunchedEffect(key1 = book) {
        if (SelectedBookService.isEditMode) {
            val book = SelectedBookService.getSelectedBook() as BookDTO
            bookTitle = book.title
            authorName = book.author
            price = book.price.toInt()
            booksAvailable = book.available.toInt()
            genre = book.genre

            // Parse publishedDate string to LocalDate
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val parsedDate = LocalDate.parse(book.publishedDate, formatter)
            publishedDate = parsedDate // Set the publishedDate directly as a LocalDate
            Log.d("date",publishedDate.dayOfMonth.toString())
            briefDescription = book.desc
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Book Title")
        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Book Title") }
        )
        if(bookTitle.isNullOrEmpty()){
            ErrorMessage(text = "Book title should not be empty")
            isFormValid = false
        } else {
            isFormValid =true
        }
        Text(text = "Author Name")


        OutlinedTextField(
            value = authorName,
            onValueChange = { authorName = it },
            label = { Text("Author Name") }
        )
        if(authorName.isNullOrEmpty()){
            ErrorMessage(text = "Authorname should not be empty")
            isFormValid=false
        } else {
            isFormValid =true
        }
        Text(text = "Price")

        OutlinedTextField(
            value = price.toString(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                if (it.isNotEmpty() && it.matches(Regex("-?\\d*"))) {
                    price = it.toInt()
                } else {
                    price = 0
                }
            },
            label = { Text("Price") },

        )
        if(price<0){
            ErrorMessage(text = "Price should not be empty or less then empty")
            isFormValid =false
        } else {
            isFormValid =true
        }
        Text(text = "Available book Number")
        OutlinedTextField(
            value = booksAvailable.toString(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                if (it.isNotEmpty() && it.matches(Regex("-?\\d*"))) {
                    booksAvailable = it.toInt()
                } else {
                    booksAvailable = 0
                }
            },
            label = { Text("Available Books") },
        )
        if(booksAvailable<0){
            ErrorMessage(text = "Available Books should not be empty or less then empty")
            isFormValid = false
        } else {
            isFormValid =true
        }
        Text(text = "Genre")
        Dropdown(genre) { selectedGenre ->
            genre = selectedGenre
        }
        if(genre.isNullOrEmpty()){
            ErrorMessage(text = "Please fill the genre")
            isFormValid = false
        } else {
            isFormValid =true
        }
        Text(text = "Published Date")
        DatePicker(selectedDate = publishedDate) { newDate -> publishedDate = newDate }
        Text(text = "Brief Description")

        OutlinedTextField(
            value = briefDescription,
            onValueChange = { briefDescription = it },
            label = { Text("Brief Description") }
        )
        if(briefDescription.isNullOrEmpty()){
            ErrorMessage(text = "Please fill the description ")
            isFormValid = false
        } else {
            isFormValid =true
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Upload Book Cover Img")
            uploadPhotoBtn()
        }

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    val year = publishedDate.year
                    val month = publishedDate.monthValue
                    val day = publishedDate.dayOfMonth
                    var message = "Book created successfully!"
                   viewModel.viewModelScope.launch {
                       if(SelectedBookService.isEditMode){
                           message = "Book updated successfully!"
                           viewModel.updateBookById((book as BookDTO).id,
                               title = bookTitle,
                               authorName = authorName,
                               price = price.toDouble(),
                               bookAvailable = booksAvailable,
                               genre = genre,
                               publishedDate = "$year-$month-$day",
                               desc = briefDescription,)
                       } else {
                           viewModel.createNewBook(
                               title = bookTitle,
                               authorName = authorName,
                               price = price.toDouble(),
                               bookAvailable = booksAvailable,
                               genre = genre,
                               publishedDate = "$year-$month-$day",
                               desc = briefDescription,
                           )
                       }

                       AlertDialog.Builder(context)
                           .setTitle("Success")
                           .setMessage(message)
                           .setPositiveButton("OK") { dialog, _ ->
                               dialog.dismiss()
                           }
                           .create()
                           .show()
                   }
                },

                modifier = Modifier
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
                enabled = isFormValid,

                ) {
                Text(text = "Save")
            }

        }
    }
}





@Composable()
fun uploadPhotoBtn() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green))
    ) {
        Row {
            Image(painterResource(id = R.drawable.upload), contentDescription = "add icon")
            Text(text = "Upload Photo")
        }

    }
}

@Composable
fun ErrorMessage(text:String){
    Text(text = text, color = colorResource(id = R.color.red))
}

