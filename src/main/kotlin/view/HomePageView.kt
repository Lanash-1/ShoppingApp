package view

import enums.HomePage

class HomePageView {
    fun homePage(){
        for(option: HomePage in HomePage.values()) println("${option.ordinal+1}. $option")

    }
}