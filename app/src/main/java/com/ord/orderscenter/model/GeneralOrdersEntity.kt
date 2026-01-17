package com.ord.orderscenter.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ord.orderscenter.utils.standardizedDateFormat


@Entity(tableName = "general_orders")
data class GeneralOrdersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = standardizedDateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val customerName: String,
    val phone: String,
    val addressDescription: String? = null,
    val status: String = "unpaid",
    val totalOrder: Float,
    val datePaid: String? = null,
    val dateDue: String? = null,
    val orderNumber: String,
    val timestamp: Long = System.currentTimeMillis()
)
