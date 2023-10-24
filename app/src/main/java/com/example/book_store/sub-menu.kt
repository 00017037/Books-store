package com.example.book_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable()
fun subMenu(){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
       Text(text = "Main Adevertisement List",modifier = Modifier.align(Alignment.CenterVertically))
        addBookBtn()
    }
}

@Composable()
fun addBookBtn(){
   Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green))) {
       Row {
          Image(painterResource(id = R.drawable.add), contentDescription = "add icon")
           Text(text = "Add new Book")
       }

   }
}