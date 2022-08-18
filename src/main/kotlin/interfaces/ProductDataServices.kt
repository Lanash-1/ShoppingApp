package interfaces

import enums.Category
import model.Product

interface ProductDataServices {
    fun getAllProducts(): ArrayList<Product>

    fun getProductsByCategory(category: Category): ArrayList<Product>

}