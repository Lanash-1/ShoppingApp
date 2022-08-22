package view

import controller.CartController
import enums.CardOptions
import enums.PaymentMethodOptions
import enums.PaymentOptions
import enums.UpiOptions
import model.Cart
import payment.*
import utility.Helper

class PaymentView {

    private val helper = Helper()
    fun paymentOptions(cartController: CartController): Boolean {
        while(true) {
            for (option: PaymentOptions in PaymentOptions.values()) {
                println("${option.ordinal + 1}. $option")
            }
            print("\nEnter your choice: ")
            try {
                val option = readLine()?.toInt()
                option?.let {
                    if (helper.checkValidRecord(option, PaymentOptions.values().size)) {
                        val entry: PaymentOptions = PaymentOptions.values()[option - 1]
                        return paymentOperations(entry, cartController)
                    } else {
                        println("Select from available options")
                    }
                }
            } catch (error: Exception) {
                println("Enter only valid options")
            }
        }
    }

    private fun paymentOperations(entry: PaymentOptions, cartController: CartController): Boolean {
        return when(entry){
            PaymentOptions.PAY_NOW -> {
                paynowOptions(cartController.getTotalBillAmount(cartController.products, cartController.quantity))
                cartController.isPaid = true
                true
            }
            PaymentOptions.CASH_ON_DELIVERY -> {
                cartController.cart = Cart(ArrayList(), ArrayList())
                cartController.products = ArrayList()
                cartController.quantity = ArrayList()
                cartController.isPaid = true
                println("Order placed successfully")
                false
            }
        }
    }

    private fun paynowOptions(totalBillAmount: Int){
        while(true){
            for(option: PaymentMethodOptions in PaymentMethodOptions.values()) println("${option.ordinal+1}. $option")
            print("Enter your choice: ")
            try {
                val option = readLine()?.toInt()
                option?.let {
                    if (helper.checkValidRecord(option, PaymentMethodOptions.values().size)) {
                        val entry: PaymentMethodOptions = PaymentMethodOptions.values()[option - 1]
                        paymentMethodOperations(entry, totalBillAmount)
                    } else {
                        println("Select from available options")
                    }
                }
                break
            }catch (error: Exception){
                println("Enter only valid options")
            }
        }
    }

    private fun paymentMethodOperations(entry: PaymentMethodOptions, totalBillAmount: Int) {
        when(entry){
            PaymentMethodOptions.UPI -> {
                upiOptions(totalBillAmount)
            }
            PaymentMethodOptions.CARD -> {
                cardOptions(totalBillAmount)
            }
        }
    }

    private fun cardOptions(totalBillAmount: Int) {
        while(true){
            for (option: CardOptions in CardOptions.values()) println("${option.ordinal+1}. $option")
            print("Enter your choice: ")
            try {
                val option = readLine()?.toInt()
                option?.let {
                    if (helper.checkValidRecord(option, CardOptions.values().size)) {
                        val entry: CardOptions = CardOptions.values()[option - 1]
                        cardOperations(entry, totalBillAmount)
                    } else {
                        println("Select from available options")
                    }
                }
                break
            }catch (error: Exception){
                println("Enter only valid options")
            }
        }
    }

    private fun cardOperations(entry: CardOptions, totalBillAmount: Int) {
        val card = when(entry){
            CardOptions.DEBIT_CARD -> {
                DebitCard()
            }
            CardOptions.CREDIT_CARD -> {
                CreditCard()
            }
        }
        val cardTransaction = CardTransaction(card)
        val transaction = Transaction(cardTransaction)
        transaction.performTransaction(totalBillAmount)
    }

    private fun upiOptions(totalBillAmount: Int) {
        while(true){
            for (option: UpiOptions in UpiOptions.values()) println("${option.ordinal+1}. $option")
            print("Enter your choice: ")
            try {
                val option = readLine()?.toInt()
                option?.let {
                    if (helper.checkValidRecord(option, UpiOptions.values().size)) {
                        val entry: UpiOptions = UpiOptions.values()[option - 1]
                        upiOperations(entry, totalBillAmount)
                    } else {
                        println("Select from available options")
                    }
                }
                break
            }catch (error: Exception){
                println("Enter only valid options")
            }
        }

    }

    private fun upiOperations(entry: UpiOptions, totalBillAmount: Int) {
        val upi = when(entry){
            UpiOptions.GPAY -> {
                Gpay()
            }
            UpiOptions.PAYTM -> {
                Paytm()
            }
        }
        val upiTransaction = UpiTransaction(upi)
        val transaction = Transaction(upiTransaction)
        transaction.performTransaction(totalBillAmount)
    }
}