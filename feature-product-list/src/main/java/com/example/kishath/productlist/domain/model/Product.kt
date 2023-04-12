package com.example.kishath.productlist.domain.model

import java.math.BigDecimal

data class Product(
    val id: String,
    val title: String,
    val price: BigDecimal,
    val category: String,
    val description: String,
    val imageUrl: String
)