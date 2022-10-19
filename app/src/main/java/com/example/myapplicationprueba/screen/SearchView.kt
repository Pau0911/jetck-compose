package com.example.myapplicationprueba

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplicationprueba.ui.theme.Fonts
import com.example.myapplicationprueba.ui.theme.buttonPrimary
import com.example.myapplicationprueba.ui.theme.color_background
import com.example.myapplicationprueba.ui.theme.color_primary


@Composable
fun SearchView(goToResultsView: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(backgroundColor = color_primary) {
        }
    }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            BackgroundContent()
            Spacer(modifier = Modifier.height(24.dp))
            TextField(
                value = text,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Normal),


                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Icono de buscar"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = color_background,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Blue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = { Text(text = "Buscar un producto",fontFamily = Fonts,
                    fontWeight = FontWeight.Light) },
            )
            Button(
                onClick = {
                    if (text.isNotEmpty()) goToResultsView(text) else Toast.makeText(
                        context,
                        "Por favor ingresar busqueda",
                        Toast.LENGTH_SHORT
                    ).show()
                }, colors = ButtonDefaults.buttonColors(backgroundColor = buttonPrimary),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(text = "Buscar", color = Color.White)

            }

        }
    }


}

@Composable
fun BackgroundContent() {
    Image(
        painter = painterResource(id = R.drawable.logo_1),
        contentDescription = "Logo",
        modifier = Modifier.size(150.dp)
    )

}
