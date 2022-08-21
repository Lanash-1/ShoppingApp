package payment

import interfaces.UpiServices

class Gpay: UpiServices {
    override fun transaction() {
        println("Payment done using Gpay ")
    }
}