package payment

import interfaces.TransactionServices

class Transaction(private var transaction: TransactionServices) {

    fun performTransaction(totalBillAmount: Int){
        transaction.processTransaction(totalBillAmount)
    }

}