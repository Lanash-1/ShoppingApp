package model

import enums.Category

data class Product(var productId: Int, var productName: String,var productPrice: Int, var category: Category)