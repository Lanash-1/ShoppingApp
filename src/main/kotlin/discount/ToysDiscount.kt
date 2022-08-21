package discount

class ToysDiscount: ProductsDiscount(){
    override fun applyDiscount(totalAmount: Int): Int {
        return totalAmount * 95 / 100
    }
}