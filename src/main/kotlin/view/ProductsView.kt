package view

import controller.CartController
import controller.ProductsController
import enums.Category
import enums.ProductsViewOption
import model.Product
import utility.Helper

class ProductsView {

    private val productsController = ProductsController()
    private val helper = Helper()

    fun showOptions(cartController: CartController) {
        while(true){
            for (option: ProductsViewOption in ProductsViewOption.values()) println("${option.ordinal + 1}. $option")
            print("\nEnter your choice: ")
            try{
                val option = readLine()?.toInt()
                val helper = Helper()
                option?.let {
                    if(helper.checkValidRecord(option, ProductsViewOption.values().size)){
                        val entry: ProductsViewOption = ProductsViewOption.values()[option-1]
                        if(productViewOperations(entry, cartController)){
                            return
                        }
                    }else{
                        println("Select from available options")
                    }
                }
            }catch (error: Exception){
                println("Enter only valid options")
            }
        }
    }

    private fun productViewOperations(entry: ProductsViewOption, cartController: CartController): Boolean {
        return when(entry){
            ProductsViewOption.VIEW_ALL_PRODUCTS -> {
                println("\nAll Products\n")
                val allProducts: ArrayList<Product> = productsController.getAllProducts()
                selectProduct(allProducts, cartController)
                false
            }
            ProductsViewOption.VIEW_BY_CATEGORY -> {
                println("Select category")
                while(true){
                    displayCategory()
                    val selectedCategoryOption = selectCategory()
                    selectedCategoryOption?.let {
                        if(helper.checkValidRecord(selectedCategoryOption, Category.values().size)){
                            val selectedCategory = Category.values()[selectedCategoryOption-1]
                            val productByCategory = productsController.getProductsByCategory(selectedCategory)
                            selectProduct(productByCategory, cartController)
                        }else{
                            println("Select from available ")
                        }
                    }
                    break
                }
                false
            }
            ProductsViewOption.BACK -> {
                println("\nBack to home page\n")
                true
            }
        }
    }

    private fun selectProduct(products: ArrayList<Product>, cartController: CartController){
        while(true) {
            viewProducts(products)
            print("\nSelect a product: ")
            try {
                val selectedProduct = readLine()?.toInt()
                selectedProduct?.let {
                    if (helper.checkValidRecord(selectedProduct, products.size)) {
                        val quantity: Int? = getQuantity()
                        cartController.addProductToCart(products[selectedProduct-1],quantity!!)
                        println("\nProduct Added to your cart\n")
                    }else{
                        println("Select from available options")
                    }
                }
                break
            }catch (error: Exception){
                println("Enter only valid option")
            }
        }
    }

    private fun getQuantity(): Int? {
        while(true){
            print("\nEnter quantity: ")
            try {
                val quantity = readLine()?.toInt()
                if(quantity!! > 0){
                    return quantity
                }
                println("Should only be greater than zero")
            } catch (error: Exception) {
                println("Enter only numbers")
            }
        }
    }

    private fun selectCategory(): Int? {
        return try {
            readLine()?.toInt()
        }catch (error: Exception){
            0
        }
    }

    private fun displayCategory() {
        for(category: Category in Category.values()) println("${category.ordinal+1}. $category")
    }

    private fun viewProducts(products: ArrayList<Product>) {
        for(i in 0 until products.size){
            println("${i+1}. ${products[i].category} - ${products[i].productName} - ${products[i].productPrice}")
        }
    }

}