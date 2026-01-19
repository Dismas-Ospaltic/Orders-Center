package com.ord.orderscenter.repository



import com.ord.orderscenter.data.localdatabase.IndividualDao
import com.ord.orderscenter.model.IndividualItemEntity
import kotlinx.coroutines.flow.Flow

class IndividualItemRepository(private val individualDao: IndividualDao) {

    // 🔹 Get all gen Order (Flow for real-time UI updates)
    fun getAllListItemOrders(orderNumber: String): Flow<List<IndividualItemEntity>> = individualDao.getAllListItemOrders(orderNumber)


    suspend fun deleteSingleOrderByNumber(orderNumber: String): Boolean {
        val rowsDeleted = individualDao.deleteSingleOrderByNumber(orderNumber)
        return rowsDeleted > 0
    }


    suspend fun deleteSingleOrderItemByNumber(orderNumber: String,itemId: String): Boolean {
        val rowsDeleted = individualDao.deleteSingleOrderItemByNumber(orderNumber,itemId)
        return rowsDeleted > 0
    }


    // 🔹 Insert new order
    suspend fun insertIndividualOrder(singleOrder: IndividualItemEntity) = individualDao.insertIndividualOrder(singleOrder)

    // 🔹 Update existing order
    suspend fun updateIndividualOrder(singleOrder: IndividualItemEntity) = individualDao.updateIndividualOrder(singleOrder)


    suspend fun updateSingleOrderById(
        itemName: String,
        price: Float,
        quantity: Int,
        qtyDescription: String? = null,
        orderNumber: String,
        itemId: String
    ): Boolean {
        val rowsUpdated = individualDao.updateSingleOrderById(
            itemName,
            price,
            quantity,
            qtyDescription,
        orderNumber,
        itemId
        ) ?: 0
        return rowsUpdated > 0
    }

}