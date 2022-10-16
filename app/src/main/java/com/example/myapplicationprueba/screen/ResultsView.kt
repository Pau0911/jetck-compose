package com.example.myapplicationprueba

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myapplicationprueba.model.Product
import com.example.myapplicationprueba.screen.state.ErrorItem
import com.example.myapplicationprueba.screen.state.LoadingItem
import com.example.myapplicationprueba.screen.state.LoadingView
import com.example.myapplicationprueba.viewModel.ResultsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow


@Composable
fun ResultsView(text: String, resultViewModel: ResultsViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Mostrando resultados para:$text")

        }, backgroundColor = Color.Yellow)
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            ProductList(products = resultViewModel.getResults(text))
        }
    }

}


@Composable
fun ProductList(products: Flow<PagingData<Product>>) {
    val lazyProductItems = products.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyProductItems) { product ->
            ProductItem(product = product!!, onClick = {

            })
        }

        lazyProductItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyProductItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() })
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyProductItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(message = e.error.localizedMessage!!, onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}


@Composable
fun ProductItem(product: Product,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f
                )
            ) {
                val painter= rememberAsyncImagePainter(product.thumbnail)
                Image( painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop )
//                ProductImage(product.thumbnail,modifier = Modifier
//                    .padding(start = 16.dp)
//                    .size(90.dp)
//                )

            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black
                )
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = product.price.toString(),
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductImage(
    imageUrl: String, modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        alpha = 0.45f,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )

}

@Composable
fun ProductTitle(
    title: String, modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}