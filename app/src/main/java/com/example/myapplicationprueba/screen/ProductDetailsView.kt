package com.example.myapplicationprueba.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplicationprueba.R
import com.example.myapplicationprueba.Response
import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.ui.theme.Fonts
import com.example.myapplicationprueba.ui.theme.buttonPrimary
import com.example.myapplicationprueba.ui.theme.color_primary
import com.example.myapplicationprueba.viewModel.ProductDetailsViewModel

@Composable
fun DetailsView(
    id: String,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    fun launch() {
        viewModel.getDetailProduct(id)
    }

    launch()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Scaffold(topBar = {
            TopAppBar(title = {
                IconButton(
                    modifier = Modifier.padding(start = 6.dp),
                    onClick = {}
                ) {
                    Icon(
                        Icons.Filled.Star,
                        "contentDescription", tint = buttonPrimary
                    )
                }
                Text(
                    text = "Detalles del producto",
                    fontFamily = Fonts,
                    fontWeight = FontWeight.Normal
                )
            }, backgroundColor = color_primary, modifier = modifier.align(Alignment.Center))
        }) {

            when (val productResponse = viewModel.productState.value) {
                is Response.Loading -> {
                }
                is Response.Success -> {
                    StandardCard(Modifier.align(Alignment.Center), productResponse.data)
                }

            }
        }

    }

}


@Composable
fun StandardCard(
    modifier: Modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(top = 20.dp),

    productDetails: ProductDetails?,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium,

    ) {
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
    ) {
        // Contenedor
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically


            ) {
                // Miniatura
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    // Encabezado
                    Text(
                        text = productDetails?.title.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Fonts,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            // Multimedia
            Image(
                rememberAsyncImagePainter(productDetails?.pictures?.get(0)?.url),
                contentDescription = "Product",
                Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .height(194.dp)
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                // Texto de ayuda
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    if (productDetails != null) {
                        Text(
                            color = Color.Black,
                            text = String.format("$ %,d", productDetails.price),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = Fonts,
                                fontWeight = FontWeight.Light
                            ),
                        )
                    }
                }
            }

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {
                if (productDetails?.seller_address?.city?.name != null) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Ubicaci??n"
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        color = Color.Black,
                        text = productDetails?.seller_address?.city?.name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Fonts,
                            fontWeight = FontWeight.Light
                        ),
                    )

                }

            }

            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    color = Color.Black,
                    text = "Caracteristicas",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Fonts,
                        fontWeight = FontWeight.Normal
                    ),
                )
            }

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    if (productDetails?.attributes?.get(0)!= null) {
                        Text(
                            color = Color.Black,
                            text = productDetails?.attributes.get(0).name + ":",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = Fonts,
                                fontWeight = FontWeight.Light
                            ),
                        )
                    }

                }
            }

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                    if (productDetails?.attributes?.get(0) != null) {
                        Text(
                            color = Color.Black,
                            text = productDetails?.attributes.get(0).value_name,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = Fonts,
                                fontWeight = FontWeight.Light
                            ),
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                Box(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {

                    // Botones
                    Row(modifier = Modifier.align(Alignment.CenterStart)) {

                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = buttonPrimary)
                        ) {
                            Text(text = "Comprar", color = Color.White)

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(backgroundColor = buttonPrimary)
                        ) {
                            Text(text = "Agregar al carrito", color = Color.White)
                        }
                    }

                    // Iconos
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}


