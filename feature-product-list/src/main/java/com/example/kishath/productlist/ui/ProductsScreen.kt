package com.example.kishath.productlist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kishath.designsystem.components.ErrorItem
import com.example.kishath.designsystem.components.LoadingItem
import com.example.kishath.designsystem.components.TitleBar
import com.example.kishath.productlist.R
@Composable
fun ProductsScreen(
    viewModel: ProductViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TitleBar(stringResource(id = R.string.products_screen_title))
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding),
                color = MaterialTheme.colorScheme.background,
            ) {
                Content(viewModel)
            }
        }
    )
}

@Composable
private fun Content(viewModel: ProductViewModel) {

    LaunchedEffect(key1 = Unit) {
        viewModel.loadProducts()
    }

    when (val state = viewModel.uiState.collectAsState().value) {
        ProductViewModel.UiState.Loading -> LoadingItem()
        is ProductViewModel.UiState.Error -> ErrorItem()
        is ProductViewModel.UiState.Success -> ProductList(products = state.products) {}
    }
}
