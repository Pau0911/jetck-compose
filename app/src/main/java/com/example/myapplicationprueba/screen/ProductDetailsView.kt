package com.example.myapplicationprueba.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplicationprueba.R
import com.example.myapplicationprueba.Response
import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.viewModel.ProductDetailsViewModel

@Composable
fun DetailsView(id:String, viewModel: ProductDetailsViewModel = hiltViewModel(),modifier: Modifier = Modifier){

        fun launch() {
            viewModel.getDetailProduct(id)
        }

        launch()

        Box(
            modifier = modifier
                .fillMaxSize()
           // color = MaterialTheme.colors.background
        ) {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text(text = "Detalles del producto")

//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Default.Favorite)
//            }
                }, backgroundColor = Color.Yellow)
            }){

                when(val productResponse = viewModel.productState.value){
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
//                    DetailScreen(
//                        product = productResponse.data
//                    )
                        StandardCard(Modifier.align(Alignment.Center),productResponse.data)
                    }
//                is Response.Failure -> {
//                    ErrorButton(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = stringResource(id = R.string.error_message),
//                        onClick = {
//                            launch()
//                        }
//                    )
//                }
                }
            }

        }

}



@Composable
fun StandardCard(
    modifier: Modifier = Modifier
        .verticalScroll(rememberScrollState())
    ,productDetails: ProductDetails?,
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
        Column (modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()){
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
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(Modifier.fillMaxWidth()) {
                    // Encabezado
                    Text(text = productDetails?.title.toString(), style = MaterialTheme.typography.h6)

                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = productDetails?.condition.toString(), style = MaterialTheme.typography.body1)
                    }
                }
            }

            // Multimedia
            Image(
                painter = rememberAsyncImagePainter(productDetails?.thumbnail),
                contentDescription = "Product",
                Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .height(194.dp)
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                // Texto de ayuda
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = productDetails?.price.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                    )
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

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Comprar")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(onClick = {
                        }) {
                            Text(text = "Agregar al carriro")
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

//
//
//@Composable
//fun DetailsProduct(modifier: Modifier,product: ProductDetails?){
//
//    Column(
//        modifier = modifier
//    ) {
//        val painter= rememberAsyncImagePainter(product?.thumbnail)
//        HeaderImage(painter,modifier.align(Alignment.CenterHorizontally))
//        Spacer(modifier = Modifier.padding(16.dp))
//        elementsProduct(product)
//    }
//
//}
//
//@Composable
//fun HeaderImage(painter: Painter, modifier: Modifier) {
//    Image(painter =painter, contentDescription ="Imagen producto",
//        modifier = modifier
//            .height(300.dp)
//            .clip(shape = CutCornerShape(12.dp)),
//        contentScale = ContentScale.Crop)
//}
//
//@Composable
//fun elementsProduct(product: ProductDetails?){
//    Column(Modifier.padding(16.dp)) {
//        Row() {
//            if (product != null) {
//                Text(text =product.title )
//            }
//        }
//        Row() {
//            if (product != null) {
//                Text(text = product.price.toString())
//            }
//        }
//    }
//
//
//}
//
//
//@Composable
//fun DetailScreen(
//    modifier: Modifier = Modifier,
//    product: ProductDetails? = null
//) {
//    if(product == null) return
//    val scrollState = rememberScrollState()
//    val name = product.title
//    val imageUrl = product.thumbnail
//    val releaseDate = product.seller_address.country.name
//    Log.d("Dd",name)
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .verticalScroll(scrollState),
//
//    ) {
//        ProductHeader(modifier=Modifier,imageUrl,name,releaseDate)
//    }
//}
//
//@Composable
//fun ProductHeader(
//    modifier: Modifier = Modifier,
//    imageUrl: String = "",
//    name: String = "",
//    releaseDate: String = "",
//) {
//    ConstraintLayout(
//        modifier = modifier
//    ) {
//        val (
//            photoAvatar,
//            nameText,
//            titleText,
//        ) = createRefs()
//        val imagePainter = rememberAsyncImagePainter(
//            model = imageUrl,
//        )
//        Image(
//            painter = imagePainter,
//            contentDescription = name,
//            modifier = Modifier
//                .size(96.dp)
//                .clip(CircleShape)
//                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
//                .constrainAs(photoAvatar) {
//
//                },
//        )
//        Text(
//            text = name,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .constrainAs(nameText){
//                    start.linkTo(photoAvatar.end, 16.dp)
//                    top.linkTo(parent.top)
//                }
//        )
//        Text(
//            text = releaseDate,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Medium,
//            modifier = Modifier.constrainAs(titleText){
//                start.linkTo(photoAvatar.end, 16.dp)
//                top.linkTo(nameText.bottom, 4.dp)
//            }
//        )
//    }
//}