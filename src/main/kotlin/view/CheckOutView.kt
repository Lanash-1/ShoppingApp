package view

import controller.CartController
import model.CustomerDetails
import utility.Helper

class CheckOutView {

    private val helper = Helper()
    private val customerDetails = CustomerDetails("","","","",false)
    private val paymentView = PaymentView()
    fun checkoutOperations(cartController: CartController) {
        customerDetails.apply {
            this.name = getInput("Enter your name: ")
            this.address = getInput("Enter your address: ")
            this.landmark = getInput("Enter landmark: ")
            this.mobileNumber = getInput("Enter mobile number: ")
            this.isPaid = paymentView.paymentOptions(cartController)
        }
    }

    private fun getInput(displayMessage: String): String {
        while(true){
            print(displayMessage)
            val input = readLine()!!
            if(helper.checkNotEmpty(input)){
                return input
            }else{
                println("Field should not be empty")
            }
        }
    }


}