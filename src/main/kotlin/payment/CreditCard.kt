package payment

import interfaces.CardServices

class CreditCard: CardServices {
    override fun transaction() {
        println("Amount paid with credit card")
    }
}