package discount

class GroceryDiscount: ProductsDiscount() {

    override fun applyDiscount(totalAmount: Int): Int {
        return totalAmount * 98 / 100
    }

}