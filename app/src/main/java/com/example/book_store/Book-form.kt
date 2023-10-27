package com.example.book_store

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.book_store.book_details.RedirectButton
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable()
fun AddBookPage() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val context = LocalContext.current
        Header("Add New Book")
        RedirectButton(Modifier.padding(top = 15.dp))

       BookForm()

    }
}

enum class Genre {
    Fantasy, ScienceFiction, Cartoon
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun BookForm() {
    var bookTitle by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0) }
    var booksAvailable by remember { mutableStateOf(0) }
    var genre by remember { mutableStateOf("Detective") }
    var publishedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var briefDescription by remember { mutableStateOf("") }
    val context = LocalContext.current // Retrieve the context

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Book Title")
        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Book Title") }
        )
        Text(text = "Author Name")


        OutlinedTextField(
            value = authorName,
            onValueChange = { authorName = it },
            label = { Text("Author Name") }
        )
        Text(text = "Price")

        OutlinedTextField(
            value = price.toString(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                if (it.isNotEmpty() && it.matches(Regex("-?\\d*"))) {
                    price = it.toInt()
                } else {
                    price= 0
                }
            },
            label = { Text("Price") },
        )
        Text(text = "Available book Number")
        OutlinedTextField(
            value = booksAvailable.toString(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                if (it.isNotEmpty() && it.matches(Regex("-?\\d*"))) {
                    booksAvailable = it.toInt()
                } else {
                    booksAvailable= 0
                }
            },
            label = { Text("Price") },
        )
        Text(text = "Genre")
        Dropdown(genre){ selectedGenre ->
            genre = selectedGenre
        }
        Text(text = "Published Date")

        DatePicker(selectedDate = publishedDate)
        Text(text = "Brief Description")

        OutlinedTextField(
            value = briefDescription,
            onValueChange = { briefDescription = it },
            label = { Text("Brief Description") }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Upload Book Cover Img")
            uploadPhotoBtn()
        }

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    Log.d("BookForm", "Book Title: $bookTitle")
                    Log.d("BookForm", "Author Name: $authorName")
                    Log.d("BookForm", "Price: $price")
                    Log.d("BookForm", "Books Available: $booksAvailable")
                    Log.d("BookForm", "Genre: $genre")
                    Log.d("BookForm", "Published Date: ${publishedDate.}")
                    Log.d("BookForm", "Brief Description: $briefDescription")
                },
                modifier = Modifier
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),

                ) {
                Text(text = "Save")
            }
        }
    }
}



@Composable
fun SaveBtn(){
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                println()
            },
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),

            ) {
            Text(text = "Save")
        }
    }
}
@Composable()
fun uploadPhotoBtn(){
    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green))) {
        Row {
            Image(painterResource(id = R.drawable.upload), contentDescription = "add icon")
            Text(text = "Upload Photo")
        }

    }
}