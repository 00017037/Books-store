package com.example.book_store

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar

@ExperimentalMaterial3Api
@Composable

fun DatePicker(selectedDate: Calendar){
    Column {
        var formattedDate by remember { mutableStateOf("") }
        val context = LocalContext.current
        OutlinedTextField(
            value = formattedDate,
            onValueChange = {},
            label = { Text("Published Date") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        val datePicker = DatePickerDialog(
                            context
                            ,
                            { _, year, month, day ->
                                selectedDate.set(year, month, day)
                                formattedDate = "${day}/${month + 1}/${year}"
                            },
                            selectedDate.get(Calendar.YEAR),
                            selectedDate.get(Calendar.MONTH),
                            selectedDate.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Picker"
                    )
                }
            })
    }

}

@ExperimentalMaterial3Api
@Composable
fun Dropdown(selectedGenre: String, onGenreSelected: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    var genre by remember {
        mutableStateOf(selectedGenre)
    }
    Box(
        modifier = Modifier.padding(top = 5.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { newValue ->
                isExpanded = newValue
            },
        ) {

            TextField(
                value = genre,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                placeholder = {
                    Text(text = "Please select the genre")
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Sciense-fiction")
                    },
                    onClick = {
                        genre = "Science-fiction"
                        onGenreSelected(genre)
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Fantasy")
                    },
                    onClick = {
                        genre = "Fantasy"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Horror")
                    },
                    onClick = {
                        genre = "Horror"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Detective")
                    },
                    onClick = {
                        genre = "Detective"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Other")
                    },
                    onClick = {
                        genre = "Other"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
            }
        }

    }
}