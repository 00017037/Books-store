package com.example.book_store

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable

fun DatePicker(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    var formattedDate by remember { mutableStateOf(selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = formattedDate)) }
    LaunchedEffect(selectedDate) {
        formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        textFieldValue = TextFieldValue(text = formattedDate)

    }

    val context = LocalContext.current

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            formattedDate = it.text
            try {
                val parsedDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                onDateSelected(parsedDate)
            } catch (e: Exception) {
                // Handle parsing exceptions here
            }
        },
        label = { Text(stringResource(id = R.string.published_date_label)) },
        trailingIcon = {
            IconButton(
                onClick = {
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            val selectedDate = LocalDate.of(year, month + 1, day)
                            formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            textFieldValue = TextFieldValue(text = formattedDate)
                            onDateSelected(selectedDate)
                        },
                        selectedDate.year,
                        selectedDate.monthValue - 1,
                        selectedDate.dayOfMonth
                    )
                    datePicker.show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Picker"
                )
            }
        }
    )
}
@ExperimentalMaterial3Api
@Composable
fun Dropdown(selectedGenre: String, onGenreSelected: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    var genre by remember {
        mutableStateOf(selectedGenre)
    }
    LaunchedEffect(selectedGenre){
        genre = selectedGenre
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
