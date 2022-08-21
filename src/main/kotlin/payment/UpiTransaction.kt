package payment

import interfaces.UpiServices

class UpiTransaction(private var upi: UpiServices) {
    fun processTransaction(totalBillAmount: Int) {
        println("Paying $totalBillAmount .... .... ....")
        upi.transaction()
    }
}