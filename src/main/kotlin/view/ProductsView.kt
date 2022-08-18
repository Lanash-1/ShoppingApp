package view

import controller.ProductsController
import enums.Category
import enums.ProductsViewOption
import model.Product
import utility.Helper

class ProductsView {

    private val productsController = ProductsController()
    private val helper = Helper()

    fun showOptions(){
        while(true){
            for (option: ProductsViewOption in ProductsViewOption.values()) println("${option.ordinal + 1}. $option")
            print("\nEnter your choice: ")
            try{
                val option = readLine()?.toInt()
                val helper = Helper()
                option?.let {
                    if(helper.checkValidRecord(option, ProductsViewOption.values().size)){
                        val entry: ProductsViewOption = ProductsViewOption.values()[option-1]
                        if(productViewOperations(entry)){
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

    private fun productViewOperations(entry: ProductsViewOption): Boolean {
        return when(entry){
            ProductsViewOption.VIEW_ALL_PRODUCTS -> {
                println("\nAll Products\n")
                val allProducts: ArrayList<Product> = productsController.getAllProducts()
                viewProducts(allProducts)
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
                            viewProducts(productByCategory)

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

    private fun viewProducts(allProducts: ArrayList<Product>) {
        for(product: Product in allProducts)
            println("${product.productId}. ${product.category} - ${product.productName} - ${product.productPrice}")
    }

}