package com.example.myapplicationprueba

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchView(goViewDetails: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = text,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .background(color = Color.White),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icono de buscar"
                )
            },
            onValueChange = { newText ->
                text = newText
            },
            placeholder = { Text(text = "Buscar un producto") },

            )
        Button(
            onClick = {
                goViewDetails(text)
            },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
            modifier = Modifier.padding(top = 10.dp)

        ) {
            Text(text = "Buscar")

        }

    }
}


