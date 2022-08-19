package view

import controller.CartController
import enums.CartViewOptions
import model.Product

class CartView {

    fun showOptions(cartController: CartController) {
        while(true){
            viewCartItems(cartController)
            for(option: CartViewOptions in CartViewOptions.values()) println("${option.ordinal}. $option")
            break
        }
    }

    private fun viewCartItems(cartController: CartController) {
        for(i in 0 until cartController.products.size){
            println("\n${i+1}. ${cartController.products[i].productName} - ${cartController.products[i].productPrice} - x${cartController.quantity[i]} = ${cartController.products[i].productPrice * cartController.quantity[i]}")
        }
        println("Total bill amount: ${getTotalBillAmount(cartController.products,cartController.quantity)}")
    }

    private fun getTotalBillAmount(products: ArrayList<Product>, quantity: ArrayList<Int>): Int {
        var total = 0
        for(i in 0 until products.size){
            total += (products[i].productPrice * quantity[i])
        }
        return total

    }
}