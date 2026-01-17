package com.ord.orderscenter.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ord.orderscenter.utils.standardizedDateFormat


@Entity(tableName = "individual_orders")
data class IndividualItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = standardizedDateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val itemName: String,
    val price: Float,
    val quantity: Int,
    val qtyDescription: String? = null,
    val orderNumber: String,
    val timestamp: Long = System.currentTimeMillis()
)
