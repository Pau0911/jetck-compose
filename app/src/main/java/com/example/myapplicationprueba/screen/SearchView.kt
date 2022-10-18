package com.example.myapplicationprueba

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.myapplicationprueba.ui.theme.buttonPrimary
import com.example.myapplicationprueba.ui.theme.color_primary
import okhttp3.internal.wait


@Composable
fun SearchView(goToResultsView: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = text,
            shape = RoundedCornerShape(15.dp),
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
                goToResultsView(text)
            },colors = ButtonDefaults.buttonColors(backgroundColor = buttonPrimary),
            modifier = Modifier.padding(top = 10.dp)

        ) {
            Text(text = "Buscar", color = Color.White)

        }

    }
}


