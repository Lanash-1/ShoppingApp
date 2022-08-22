package payment

import interfaces.CardServices
import interfaces.TransactionServices

class CardTransaction(private var card: CardServices): TransactionServices {
    override fun processTransaction(totalBillAmount: Int){
        println("Paying $totalBillAmount .... .... ....")
        card.transaction()
    }
}