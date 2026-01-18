package com.ord.orderscenter.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ord.orderscenter.model.GeneralOrdersEntity
import com.ord.orderscenter.model.IndividualItemEntity
import com.ord.orderscenter.repository.GeneralOrdersRepository
import com.ord.orderscenter.repository.IndividualItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GeneralOrderViewModel(private val generalOrdersRepository: GeneralOrdersRepository) : ViewModel() {

    private val _genOrder = MutableStateFlow<List<GeneralOrdersEntity>>(emptyList())
    val genOrder: StateFlow<List<GeneralOrdersEntity>> = _genOrder


//    private val _updateResult = MutableStateFlow<Boolean?>(null)
//    val updateResult: StateFlow<Boolean?> = _updateResult

    init {

        getAllGenOrders()
        getAllGenOrderCount()
        getTotalOrders()
    }

    // ✅ Get all orders general
    private fun getAllGenOrders() {
        viewModelScope.launch {
           generalOrdersRepository.getAllGenOrders().collectLatest { list ->
                _genOrder.value = list
            }
        }
    }

    // ✅ Insert new order
    fun insertGenOrder(genOrder: GeneralOrdersEntity) {
        viewModelScope.launch { generalOrdersRepository.insertGenOrder(genOrder) }
    }

    // ✅ Update entire oder entity
    fun updateGenOrder(genOrder: GeneralOrdersEntity) {
        viewModelScope.launch { generalOrdersRepository.updateGenOrder(genOrder) }
    }

    // ✅ Delete entire order by ID
    fun deleteOrderByNumber(orderNumber: String) {
        viewModelScope.launch { generalOrdersRepository.deleteOrderByNumber(orderNumber) }
    }


    private val _allOrderCount = MutableStateFlow(0)
    val allOrderCount: StateFlow<Int> = _allOrderCount



    // ✅ Fetch total returned count (one-time)
    fun getAllGenOrderCount() {
        viewModelScope.launch {
            val count = generalOrdersRepository.getAllGenOrderCount()
            _allOrderCount.value = count
        }
    }


    private val _totalUnpaidOrderCount = MutableStateFlow(0)
    val totalUnpaidOrderCount: StateFlow<Int> = _totalUnpaidOrderCount.asStateFlow()


//    private val _totalUnpaidOrderCount = MutableStateFlow(0.0f)
//    val totalExpense: StateFlow<Float> = _totalExpense

    fun getAllUnpaidOrderCount(date: String) {
        viewModelScope.launch {
            generalOrdersRepository.getAllUnpaidOrderCount(date).collectLatest { total ->
                _totalUnpaidOrderCount.value = total
            }
        }
    }



    private val _totalPaidOrderCount = MutableStateFlow(0)
    val totalPaidOrderCount: StateFlow<Int> = _totalPaidOrderCount.asStateFlow()


//    private val _totalUnpaidOrderCount = MutableStateFlow(0.0f)
//    val totalExpense: StateFlow<Float> = _totalExpense

    fun getAllPaidOrderCount(date: String) {
        viewModelScope.launch {
            generalOrdersRepository.getAllPaidOrderCount(date).collectLatest { total ->
                _totalPaidOrderCount.value = total
            }
        }
    }



    private val _totalAmountAllOrder = MutableStateFlow(0.0f)
    val totalAmountAllOrder: StateFlow<Float> = _totalAmountAllOrder.asStateFlow()


    fun getTotalOrders() {
        viewModelScope.launch {
            generalOrdersRepository.getTotalOrders().collectLatest { total ->
                _totalAmountAllOrder.value = total
            }
        }
    }



    private val _totalAmountAllOrderToday = MutableStateFlow(0.0f)
    val totalAmountAllOrderToday: StateFlow<Float> = _totalAmountAllOrderToday.asStateFlow()


    fun getTodayTotalOrders(date: String) {
        viewModelScope.launch {
            generalOrdersRepository.getTodayTotalOrders(date).collectLatest { total ->
                _totalAmountAllOrderToday.value = total
            }
        }
    }


    // 🔹 Update status
    fun updateOrderStatusById(newStatus: String, orderNumber: String) {
        viewModelScope.launch {
            generalOrdersRepository.updateOrderStatusById(newStatus, orderNumber)
        }
    }

    // 🔹 Update status
    fun updateGenOrderById(
        customerName: String,
        phone: String,
        addressDescription: String? = null,
        totalOrder: Float,
        orderNumber: String
    ) {
        viewModelScope.launch {
            val success = generalOrdersRepository.updateGenOrderById(
                customerName,
                phone,
                addressDescription,
            totalOrder,
            orderNumber
            )

            if (success) {
                _genOrder.value = _genOrder.value.map { genOrder ->
                    if (genOrder.orderNumber == orderNumber) {
                        genOrder.copy(
                            customerName=customerName,
                            phone=phone,
                            addressDescription = addressDescription,
                            totalOrder =totalOrder,
                            orderNumber=orderNumber
                        )
                    } else genOrder
                }
            }
        }
    }






}