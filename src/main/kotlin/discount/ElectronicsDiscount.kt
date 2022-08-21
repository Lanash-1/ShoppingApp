package discount

class ElectronicsDiscount: ProductsDiscount() {
    override fun applyDiscount(totalAmount: Int): Int {
        return totalAmount * 80 / 100
    }
}