package payment

import interfaces.CardServices

class CardTransaction(private var card: CardServices) {
    fun processTransaction(totalBillAmount: Int){
        println("Paying $totalBillAmount .... .... ....")
        card.transaction()
    }
}