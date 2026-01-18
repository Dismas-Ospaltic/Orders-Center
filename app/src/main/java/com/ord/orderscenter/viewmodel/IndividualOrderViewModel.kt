package com.ord.orderscenter.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ord.orderscenter.model.IndividualItemEntity
import com.ord.orderscenter.repository.IndividualItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IndividualOrderViewModel(private val individualItemRepository: IndividualItemRepository) : ViewModel() {

    private val _orderItems = MutableStateFlow<List<IndividualItemEntity>>(emptyList())
    val orderItems: StateFlow<List<IndividualItemEntity>> = _orderItems


//    private val _updateResult = MutableStateFlow<Boolean?>(null)
//    val updateResult: StateFlow<Boolean?> = _updateResult

    init {
//        getAllSuppliers()
//        refreshCounts()
//        getAllDormantSuppliers()
    }

    // ✅ Get all order item
    private fun getAllGenOrders(orderNumber: String) {
        viewModelScope.launch {
            individualItemRepository.getAllGenOrders(orderNumber).collectLatest { list ->
                _orderItems.value = list
            }
        }
    }

    // ✅ Insert new supplier
    fun insertIndividualOrder(singleOrder: IndividualItemEntity) {
        viewModelScope.launch { individualItemRepository.insertIndividualOrder(singleOrder) }
    }

    // ✅ Update entire oder entity
    fun updateIndividualOrder(singleOrder: IndividualItemEntity) {
        viewModelScope.launch { individualItemRepository.updateIndividualOrder(singleOrder) }
    }

    // ✅ Delete entire list by ID
    fun deleteSingleOrderByNumber(orderNumber: String) {
        viewModelScope.launch { individualItemRepository.deleteSingleOrderByNumber(orderNumber) }
    }


//    delete single orderd item from the list
    fun deleteSingleOrderItemByNumber(orderNumber: String,itemId: String) {
        viewModelScope.launch { individualItemRepository.deleteSingleOrderItemByNumber(orderNumber,itemId) }
    }




    // 🔹 Update status
    fun updateSingleOrderById(
        itemName: String,
        price: Float,
        quantity: Int,
        qtyDescription: String? = null,
        orderNumber: String,
        itemId: String) {
        viewModelScope.launch {
            individualItemRepository.updateSingleOrderById(
                itemName,
                price,
                quantity,
                qtyDescription,
            orderNumber,
            itemId)
        }
    }


}