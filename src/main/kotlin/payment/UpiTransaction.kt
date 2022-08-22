package payment

import interfaces.TransactionServices
import interfaces.UpiServices

class UpiTransaction(private var upi: UpiServices): TransactionServices {
    override fun processTransaction(totalBillAmount: Int) {
        println("Paying $totalBillAmount .... .... ....")
        upi.transaction()
    }
}