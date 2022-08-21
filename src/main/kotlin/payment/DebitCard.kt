package payment

import interfaces.CardServices

class DebitCard: CardServices {
    override fun transaction() {
        println("Amount paid with debit card")
    }
}