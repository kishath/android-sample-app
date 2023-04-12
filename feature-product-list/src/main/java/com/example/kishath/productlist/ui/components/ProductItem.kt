package com.example.kishath.productlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kishath.productlist.R
import com.example.kishath.productlist.domain.model.Product
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Composable
fun ProductItem(product: Product, onClicked: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .testTag("ProductItem")
            .clickable { onClicked(product) }
            .semantics { productText = product.title }
    ) {
        Box(
            modifier = Modifier
                .width(165.dp)
                .height(165.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(13.dp))
            )
        }
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 14.dp),
            text = product.title,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 4.dp),
            text = stringResource(
                id = R.string.product_item_price,
                product.price.toCurrency()
            ),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

val ProductItemSemanticsMatcher = SemanticsPropertyKey<String>("ProductItemSemanticsMatcher")
var SemanticsPropertyReceiver.productText by ProductItemSemanticsMatcher

private fun BigDecimal.toCurrency(): String =
    NumberFormat.getNumberInstance(Locale("en", "US")).format(this.toDouble())
