package discount

class FashionDiscount: ProductsDiscount() {
    override fun applyDiscount(totalAmount: Int): Int{
        return totalAmount * 90 / 100
    }
}