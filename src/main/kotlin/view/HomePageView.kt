package view

import controller.CartController
import enums.HomePage
import utility.Helper

class HomePageView {

    private var cartController = CartController()

    fun homePage(){
        while(true) {
            for (option: HomePage in HomePage.values()) println("${option.ordinal + 1}. $option")
            print("\nEnter your choice: ")
            try {
                val option = readLine()?.toInt()
                val helper = Helper()
                option?.let {
                    if(helper.checkValidRecord(it, HomePage.values().size)){
                        val entry: HomePage = HomePage.values()[option-1]
                        if(homePageOperation(entry) || cartController.isPaid){
                            return
                        }
                    }else{
                        println("Enter from available options")
                    }
                }
            }catch (error: Exception){
                println("Enter only valid option.")
            }
        }
    }

    private fun homePageOperation(option: HomePage): Boolean{
        return when(option){
            HomePage.VIEW_PRODUCTS -> {
                val productsView = ProductsView()
                productsView.showOptions(cartController)
                false
            }
            HomePage.VIEW_CART -> {
                println("Shopping cart")
                val cartView = CartView()
                cartView.showOptions(cartController)
                false
            }
            HomePage.EXIT -> {
                println("\nClosing App....")
                true
            }
        }
    }
}