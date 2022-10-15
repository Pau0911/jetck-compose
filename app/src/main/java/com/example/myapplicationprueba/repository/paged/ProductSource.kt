package com.example.myapplicationprueba.repository.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplicationprueba.model.Product
import com.example.myapplicationprueba.repository.ProductRepository

class ProductSource(
    private val productRepository: ProductRepository,
    private val searchText: String,
    private val siteId: String
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val nextPage = params.key ?: 1
            val productListResponse = productRepository.getResponse(siteId, searchText, nextPage)

            LoadResult.Page(
                data = productListResponse.products,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = productListResponse.paging.offset?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        TODO("Not yet implemented")
    }
}