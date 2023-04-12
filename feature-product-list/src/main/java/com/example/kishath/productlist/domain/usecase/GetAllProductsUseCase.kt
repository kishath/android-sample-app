package com.example.kishath.productlist.domain.usecase

import com.example.kishath.core.domain.FlowUseCase
import com.example.kishath.core.domain.UseCaseResult
import com.example.kishath.productlist.domain.ProductRepository
import com.example.kishath.productlist.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) : FlowUseCase<Unit, List<Product>>(
    coroutineDispatcher = Dispatchers.IO
) {
    override fun execute(parameters: Unit): Flow<UseCaseResult<List<Product>>> {
        return flow {
            emit(UseCaseResult.Loading)
            emit(
                UseCaseResult.Success(
                    productRepository.getAllProducts()
                )
            )
        }
    }
}