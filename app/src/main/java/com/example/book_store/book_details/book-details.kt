package com.example.book_store.book_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.book_store.BookImage
import com.example.book_store.Header
import com.example.book_store.R
import com.example.book_store.SelectedBookService
import com.example.book_store.list.CreateDeleteUpdBookViewModel
import kotlinx.coroutines.launch
import models.BookDTO


@Composable
fun RedirectButton(modifier: Modifier = Modifier,navController: NavController) {
    Button(
        onClick = { navController.navigate("main")},
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green)),
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.back), contentDescription = "add icon")
            Text(text = "Back")
        }

    }
}

@Composable
fun BookDetails(navController: NavController) {
    val book = SelectedBookService.getSelectedBook()
    Column {
        Header(stringResource(id = R.string.view_details_page))
        RedirectButton(
            Modifier
                .padding(top = 16.dp)
                .padding(start = 16.dp),
            navController
        )
        Text(
            text = "Title: ${(book as BookDTO).title}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        BookInfo(book as BookDTO)
        Text(text = "Brief Descrption:", modifier = Modifier.padding(16.dp))
        Text(text = book.desc, modifier = Modifier.padding(16.dp))
        ViewBooksControls(navController)

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
fun BookList(genre: String, author: String, publishedDate: String, price: Number, available: Number) {

    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .height(180.dp)
        .padding(start = 20.dp)) {
        Text(text = stringResource(id = R.string.genere) +": $genre")
        Text(text = stringResource(id = R.string.author)+": $author")
        Text(text = stringResource(id = R.string.pub_year) +": $publishedDate")
        Text(text = stringResource(id = R.string.price)+": $price$")
        Text(text = stringResource(id = R.string.available)+": $available")
    }
}

@Composable
fun EditBtn(navController: NavController) {
    Button(
        onClick = { SelectedBookService.isEditMode = true; navController.navigate("add_edit") },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.yellow))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.edit), contentDescription = "edit icon")
            Text(text = "Edit")
        }
    }
}


@Composable
fun ViewBooksControls(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        DeleteBtn(navController,CreateDeleteUpdBookViewModel())
        EditBtn(navController)
    }
}
@Composable
fun DeleteBtn(navController: NavController,viewModel: CreateDeleteUpdBookViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    Button(
        onClick = {
            openDialog.value = true
        },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.delete), contentDescription = "delete icon")
            Text(text = stringResource(id = R.string.delete))
        }
    }
    DeleteBookDialog(openDialog, onConfirmDelete = {
        viewModel.viewModelScope.launch {
            val selectedBook = SelectedBookService.getSelectedBook() as BookDTO;
            val result = viewModel.deleteBookByID(selectedBook.id)
        }
    })
}

@Composable
fun DeleteBookDialog(
    openDialog: MutableState<Boolean>,
    onConfirmDelete: () -> Unit
) {
    if (openDialog.value) {
        val deleteDialogText = stringResource(id = R.string.delete_book)
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(deleteDialogText) },
            text = { Text(stringResource(id = R.string.delete_conf_message) ) },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        onConfirmDelete()
                    }
                ) {
                    Text(stringResource(id = R.string.delete))
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}