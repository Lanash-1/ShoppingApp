package view

import controller.CartController
import enums.CartOptions
import utility.Helper

class CartView {

    private val helper = Helper()


    fun showOptions(cartController: CartController) {
        while(cartController.getTotalBillAmount(cartController.products,cartController.quantity) != 0){
            viewCartItems(cartController)
            cartController.applyDiscountToProducts(cartController.products)
            viewCartItems(cartController)
            for(option: CartOptions in CartOptions.values()) println("${option.ordinal+1}. $option")
            print("\nEnter your choice: ")
            try{
                val option = readLine()?.toInt()
                option?.let {
                    if(helper.checkValidRecord(option, CartOptions.values().size)){
                        val entry: CartOptions = CartOptions.values()[option-1]
                        if(cartViewOperations(entry, cartController)){
                            return
                        }
                    }else{
                        println("Select from available options")
                    }
                }
            }catch (error: Exception){
                println("Enter only valid options")
            }
            break
        }
    }

    private fun cartViewOperations(entry: CartOptions, cartController: CartController): Boolean {
        return when(entry){
            CartOptions.CHECKOUT -> {
                val checkoutView = CheckOutView()
                checkoutView.checkoutOperations(cartController)
                false
            }
            CartOptions.CONTINUE_SHOPPING -> {
                true
            }
        }
    }

    private fun viewCartItems(cartController: CartController) {
        for(i in 0 until cartController.products.size){
            println("\n${i+1}. ${cartController.products[i].productName} - ${cartController.products[i].productPrice} - x${cartController.quantity[i]} = ${cartController.products[i].productPrice * cartController.quantity[i]}")
        }
        println("Total bill amount: ${cartController.getTotalBillAmount(cartController.products,cartController.quantity)}")
    }



}