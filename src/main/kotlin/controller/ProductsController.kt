package controller

import enums.Category
import model.Product
import utility.ProductData

class ProductsController {

    private val productData = ProductData()


    fun getAllProducts(): ArrayList<Product> {
        return productData.getAllProducts()
    }

    fun getProductsByCategory(category: Category): ArrayList<Product>{
        return productData.getProductsByCategory(category)
    }

}