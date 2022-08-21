package utility

import interfaces.CartDataServices
import model.Product
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.util.*

class CartData(
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ProductData"
): CartDataServices {

    private fun getConnection() {
        val connectionProps = Properties()
        connectionProps["user"] = username
        connectionProps["password"] = password
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(url, connectionProps)
        } catch (ex: Exception) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    init{
        getConnection()
    }

    override fun addProductToCart(product: Product, quantity: Int) {
        query = "insert into Cart values(${product.productId},$quantity)"
        st = conn?.createStatement()
        st?.executeUpdate(query)
    }


}