package interfaces

import model.Product

interface CartDataServices {

    fun addProductToCart(product: Product, quantity: Int)
}