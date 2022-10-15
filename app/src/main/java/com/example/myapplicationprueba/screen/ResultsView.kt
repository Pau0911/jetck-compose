package com.example.myapplicationprueba

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
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
        TopAppBar(title = { Text(text = "Mostrando resultados") })
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
            ProductItem(product = product!!)
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
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductTitle(
            product.title!!, modifier = Modifier.weight(1f)
        )
        ProductImage(
            product.thumbnail, modifier = Modifier
                .padding(start = 16.dp)
                .size(90.dp)
        )
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