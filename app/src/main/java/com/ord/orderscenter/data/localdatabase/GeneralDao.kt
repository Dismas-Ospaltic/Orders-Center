package com.ord.orderscenter.data.localdatabase


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ord.orderscenter.model.GeneralOrdersEntity
import com.ord.orderscenter.utils.standardizedDateFormat
import kotlinx.coroutines.flow.Flow

//This interface defines all the database operations.
@Dao
interface GeneralDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenOrder(genOrder: GeneralOrdersEntity)

    @Update
    suspend fun updateGenOrder(genOrder: GeneralOrdersEntity)


    @Query("SELECT * FROM general_orders ORDER BY timestamp DESC")
    fun getAllGenOrders(): Flow<List<GeneralOrdersEntity>>

    // 🔹 Delete an order by unique ID
    @Query("DELETE FROM general_orders WHERE orderNumber = :orderNumber")
    suspend fun deleteOrderByNumber(orderNumber: String): Int


    @Query("""
        UPDATE general_orders 
        SET status= :newStatus
        WHERE orderNumber = :orderNumber
    """)
    suspend fun updateOrderStatusById(
        newStatus: String,
        orderNumber: String
    ): Int?



    @Query("""
        UPDATE general_orders 
        SET customerName = :customerName, 
            phone = :phone, 
            addressDescription = :addressDescription,
            totalOrder = :totalOrder
        WHERE orderNumber = :orderNumber
    """)
    suspend fun updateGenOrderById(
        customerName: String,
        phone: String,
        addressDescription: String? = null,
        totalOrder: Float,
         orderNumber: String
    ): Int?

    // 🔹 Count all orders
    @Query("SELECT COUNT(*) FROM general_orders")
    suspend fun getAllGenOrderCount(): Int

    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'unpaid' AND date =:date")
     fun getAllUnpaidOrderCount(date: String): Flow<Int?>

    @Query("SELECT COUNT(*) FROM general_orders WHERE status = 'paid' AND date =:date")
    fun getAllPaidOrderCount(date: String): Flow<Int?>

    @Query("SELECT SUM(totalOrder) FROM general_orders WHERE date=:date")
    fun getTodayTotalOrders(date: String): Flow<Float?>


    @Query("SELECT SUM(totalOrder) FROM general_orders")
    fun getTotalOrders(): Flow<Float?>

}