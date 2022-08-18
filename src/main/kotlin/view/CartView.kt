package view

import enums.CartViewOptions

class CartView {

    fun showOptions(){
        while(true){
            for(option: CartViewOptions in CartViewOptions.values()) println("${option.ordinal}. $option")
        }
    }
}