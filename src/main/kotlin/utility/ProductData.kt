package utility

import enums.Category
import interfaces.ProductDataServices
import model.Product
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

class ProductData (
    private var conn: Connection? = null,
    private var st: Statement? = null,
    private var rs: ResultSet? = null,
    private var query: String? = null,
    private val username: String = "root",
    private val password: String = "password",
    private val url: String = "jdbc:mysql://localhost:3306/ProductData"
): ProductDataServices{
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


    override fun getAllProducts(): ArrayList<Product> {
        query = "select * from Product"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val products = ArrayList<Product>()
        while(rs!!.next()){
            products.add(Product(rs!!.getInt(1), rs!!.getString(2), Category.valueOf(rs!!.getString(3)), rs!!.getInt(4)))
        }
        return products
    }

    override fun getProductsByCategory(category: Category): ArrayList<Product> {
        query = "select * from Product where Category = '$category'"
        st = conn?.createStatement()
        rs = st?.executeQuery(query)
        val products = ArrayList<Product>()
        while(rs!!.next()){
            products.add(Product(rs!!.getInt(1), rs!!.getString(2), Category.valueOf(rs!!.getString(3)), rs!!.getInt(4)))
        }
        return products
    }


}