package com.ord.orderscenter.repository




import com.ord.orderscenter.data.localdatabase.GeneralDao
import com.ord.orderscenter.model.GeneralOrdersEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GeneralOrdersRepository(private val generalDao: GeneralDao) {

    // 🔹 Get all gen Order (Flow for real-time UI updates)
    fun getAllGenOrders(): Flow<List<GeneralOrdersEntity>> = generalDao.getAllGenOrders()

//    // 🔹 Get all stock updates once (for one-time fetch)
//    suspend fun getAllStockUpdatesOnce(): List<StockEntity> = stockDao.getAllInvUpdateOnce()

    // 🔹 Insert new order
    suspend fun insertGenOrder(genOrder: GeneralOrdersEntity) = generalDao.insertGenOrder(genOrder)

    // 🔹 Update existing order
    suspend fun updateGenOrder(genOrder: GeneralOrdersEntity) = generalDao.updateGenOrder(genOrder)

    // 🔹 Update order status by ID
    suspend fun updateOrderStatusById(newStatus: String, orderNumber: String): Boolean {
        val rows = generalDao.updateOrderStatusById(newStatus, orderNumber) ?: 0
        return rows > 0
    }

    // 🔹 Update general order by ID
    suspend fun updateGenOrderById(
        customerName: String,
        phone: String,
        addressDescription: String? = null,
        totalOrder: Float,
        orderNumber: String
    ): Boolean {
        val rowsUpdated = generalDao.updateGenOrderById(
            customerName,
            phone,
            addressDescription,
        totalOrder,
        orderNumber
        ) ?: 0
        return rowsUpdated > 0
    }

    // 🔹 Delete order record by ID
    suspend fun deleteOrderByNumber(orderNumber: String): Boolean {
        val rowsDeleted = generalDao.deleteOrderByNumber(orderNumber)
        return rowsDeleted > 0
    }

    // 🔹 Count all order
    suspend fun getAllGenOrderCount(): Int {
        return generalDao.getAllGenOrderCount()
    }


    suspend fun getAllUnpaidOrderCount(date: String): Flow<Int> {
        return generalDao.getAllUnpaidOrderCount(date)
            .map { total -> total ?: 0 }  // Convert NULL to 0
    }


    suspend fun getAllPaidOrderCount(date: String): Flow<Int> {
        return generalDao.getAllPaidOrderCount(date)
            .map { total -> total ?: 0 }  // Convert NULL to 0
    }

    suspend fun getTodayTotalOrders(date: String): Flow<Float> {
        return generalDao.getTodayTotalOrders(date)
            .map { total -> total ?: 0.0f }  // Convert NULL to 0.0
    }




}