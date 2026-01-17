package com.ord.orderscenter.data.localdatabase



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ord.orderscenter.model.IndividualItemEntity
import com.ord.orderscenter.utils.standardizedDateFormat
import kotlinx.coroutines.flow.Flow

//This interface defines all the database operations.
@Dao
interface IndividualDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndividualOrder(singleOrder: IndividualItemEntity)

    @Update
    suspend fun updateIndividualOrder(singleOrder: IndividualItemEntity)


    @Query("SELECT * FROM individual_orders WHERE orderNumber =:orderNumber")
    fun getAllGenOrders(orderNumber: String): Flow<List<IndividualItemEntity>>

    // 🔹 Delete an order by unique ID
    @Query("DELETE FROM individual_orders WHERE orderNumber = :orderNumber")
    suspend fun deleteSingleOrderByNumber(orderNumber: String): Int

    // 🔹 Delete an item order by unique ID
    @Query("DELETE FROM individual_orders WHERE orderNumber = :orderNumber AND itemId=:itemId")
    suspend fun deleteSingleOrderItemByNumber(orderNumber: String,itemId: String): Int
//    @Query("""
//        UPDATE general_orders
//        SET status= :newStatus
//        WHERE orderNumber = :orderNumber
//    """)
//    suspend fun updateOrderStatusById(
//        newStatus: String,
//        orderNumber: String
//    ): Int?



    @Query("""
        UPDATE individual_orders 
        SET  itemName = :itemName, 
            price = :price, 
            quantity = :quantity,
            qtyDescription = :qtyDescription
        WHERE orderNumber = :orderNumber 
        AND itemId =:itemId
    """)
    suspend fun updateSingleOrderById(
      itemName: String,
       price: Float,
      quantity: Int,
       qtyDescription: String? = null,
      orderNumber: String,
      itemId: String
    ): Int?

//    // 🔹 Count all orders
//    @Query("SELECT COUNT(*) FROM general_orders")
//    suspend fun getAllGenOrderCount(): Int
//
//    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'unpaid' AND date =:date")
//    suspend fun getAllUnpaidOrderCount(date: String): Int
//
//    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'paid' AND date =:date")
//    fun getAllPaidOrderCount(date: String): Flow<Int?>
//
//    @Query("SELECT SUM(totalOrder) FROM general_orders WHERE date=:date")
//    fun getTodayTotalOrders(date: String): Flow<Float?>
//
//
//    @Query("SELECT SUM(totalOrder) FROM general_orders")
//    fun getTotalOrders(): Flow<Float?>

}