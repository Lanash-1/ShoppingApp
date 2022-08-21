package payment

import interfaces.UpiServices

class Paytm: UpiServices {
    override fun transaction() {
        println("Received from paytm")
    }
}