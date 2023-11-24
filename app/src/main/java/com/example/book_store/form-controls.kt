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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Calendar

@ExperimentalMaterial3Api
@Composable

fun DatePicker(selectedDate: Calendar){
    Column {
        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH) + 1  // Months are 0-based, so add 1
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)
        var formattedDate by remember { mutableStateOf("${day}/${month + 1}/${year}") }
        val context = LocalContext.current
        OutlinedTextField(
            value = formattedDate,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.published_date_label)) },
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
                    Text(text = stringResource(id = R.string.genre_placeholder))
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
                        Text(text = stringResource(id = R.string.sci_fi))
                    },
                    onClick = {
                        genre = "Science-fiction"
                        onGenreSelected(genre)
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.fantasy))
                    },
                    onClick = {
                        genre = "Fantasy"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.horror))
                    },
                    onClick = {
                        genre = "Horror"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.detective))
                    },
                    onClick = {
                        genre = "Detective"
                        onGenreSelected(genre)

                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.other))
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
