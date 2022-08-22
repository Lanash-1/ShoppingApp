package controller

import discount.*
import enums.Category
import interfaces.CartDataServices
import model.Cart
import model.Product
import utility.CartData

class CartController: CartDataServices {

    lateinit var cart: Cart
    var products = ArrayList<Product>()
    var quantity = ArrayList<Int>()
    var isPaid = false
    private val cartData = CartData()

    override fun addProductToCart(product: Product, quantity: Int){
        products.add(product)
        this.quantity.add(quantity)
        cart = Cart(products,this.quantity)
        cartData.addProductToCart(product,quantity)
    }

    fun getTotalBillAmount(products: ArrayList<Product>, quantity: ArrayList<Int>): Int {
        var total = 0
        for(i in 0 until products.size){
            total += (products[i].productPrice * quantity[i])
        }
        return total
    }

    fun applyDiscountToProducts(products: ArrayList<Product>) {
        val updatedProductsList = ArrayList<Product>()
        for (i in 0 until products.size){
            updatedProductsList.add(applyDiscount(products[i]))
        }

        this.products = updatedProductsList

    }

    private fun applyDiscount(product: Product): Product{
        when(product.category){
            Category.ELECTRONICS -> {
                val electronicsDiscount: ProductsDiscount = ElectronicsDiscount()
                product.productPrice = electronicsDiscount.applyDiscount(product.productPrice)
                return product
            }
            Category.FASHION -> {
                val fashionDiscount: ProductsDiscount = FashionDiscount()
                product.productPrice = fashionDiscount.applyDiscount(product.productPrice)
                return product
            }
            Category.TOYS -> {
                val toysDiscount: ProductsDiscount = ToysDiscount()
                product.productPrice = toysDiscount.applyDiscount(product.productPrice)
                return product
            }
            Category.GROCERY -> {
                val groceryDiscount: ProductsDiscount = GroceryDiscount()
                product.productPrice = groceryDiscount.applyDiscount(product.productPrice)
                return product
            }
        }
    }

}