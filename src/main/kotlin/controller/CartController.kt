package controller

import interfaces.CartDataServices
import model.Cart
import model.Product

class CartController: CartDataServices {

    lateinit var cart: Cart
    var products = ArrayList<Product>()
    var quantity = ArrayList<Int>()

    override fun addProductToCart(product: Product, quantity: Int){
        products.add(product)
        this.quantity.add(quantity)
        cart = Cart(products,this.quantity)
    }
}