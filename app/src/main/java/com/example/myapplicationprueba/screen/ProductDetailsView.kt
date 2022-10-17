package com.example.myapplicationprueba.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import coil.compose.rememberAsyncImagePainter
import com.example.myapplicationprueba.Response
import com.example.myapplicationprueba.model.ProductDetails
import com.example.myapplicationprueba.viewModel.ProductDetailsViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailsView(id:String, viewModel: ProductDetailsViewModel = hiltViewModel(),modifier: Modifier = Modifier){

        fun launch() {
            viewModel.getDetailProduct(id)
        }
        launch()
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            when(val productResponse = viewModel.productState.value){
                is Response.Loading -> {
                }
                is Response.Success -> {
                    DetailScreen(
                        product = productResponse.data
                    )
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
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    product: ProductDetails? = null
) {
    if(product == null) return
    val scrollState = rememberScrollState()
    val name = product.title
    val imageUrl = product.thumbnail
    val releaseDate = product.seller_address.country.name
    Log.d("Dd",name)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),

    ) {
        ProductHeader(modifier=Modifier,imageUrl,name,releaseDate)
    }
}

@Composable
fun ProductHeader(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    name: String = "",
    releaseDate: String = "",
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            photoAvatar,
            nameText,
            titleText,
        ) = createRefs()
        val imagePainter = rememberAsyncImagePainter(
            model = imageUrl,
        )
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
                .constrainAs(photoAvatar) {

                },
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(nameText){
                    start.linkTo(photoAvatar.end, 16.dp)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = releaseDate,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(titleText){
                start.linkTo(photoAvatar.end, 16.dp)
                top.linkTo(nameText.bottom, 4.dp)
            }
        )
    }
}