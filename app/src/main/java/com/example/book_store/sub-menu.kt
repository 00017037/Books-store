package com.example.book_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable()
fun subMenu( navController: NavController){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
       Text(text = "Main Adevertisement List",modifier = Modifier.align(Alignment.CenterVertically))
        addBookBtn(navController)
    }
}

@Composable()
fun addBookBtn(navController: NavController){

    Button(onClick = {SelectedBookService.isEditMode=false;navController.navigate("add_edit");}, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green))) {
       Row {
          Image(painterResource(id = R.drawable.add), contentDescription = "add icon")
           Text(text = "Add new Book")
       }

   }
}