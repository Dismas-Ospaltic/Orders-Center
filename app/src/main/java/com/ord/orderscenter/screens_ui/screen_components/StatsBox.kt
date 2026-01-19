package com.ord.orderscenter.screens_ui.screen_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ord.orderscenter.screens_ui.StatItem
import com.ord.orderscenter.utils.standardizedDateFormat
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsBox() {
    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())
    val todayDate: String = standardizedDateFormat(System.currentTimeMillis())


    val allOrderCount by generalOrderViewModel.allOrderCount.collectAsState()
    val totalUnpaidOrderCount by generalOrderViewModel.totalUnpaidOrderCount.collectAsState()
    val totalPaidOrderCount by generalOrderViewModel.totalPaidOrderCount.collectAsState()
    val totalAmountAllOrder by generalOrderViewModel.totalAmountAllOrder.collectAsState()
    val totalAmountAllOrderToday by generalOrderViewModel.totalAmountAllOrderToday.collectAsState()


    LaunchedEffect(Unit) {
        generalOrderViewModel.getAllUnpaidOrderCount(todayDate)
        generalOrderViewModel.getAllPaidOrderCount(todayDate)
        generalOrderViewModel.getTodayTotalOrders(todayDate)
    }

    val stats = listOf(
        "Total Orders" to allOrderCount.toString(),
        "Total Order Amount" to totalAmountAllOrder.toString(),
        "Unpaid Orders Today" to totalUnpaidOrderCount.toString(),
        "Paid Orders Today" to totalPaidOrderCount.toString(),
        "Total Order Amount Today" to totalAmountAllOrderToday.toString()
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Split list into rows of 2
            stats.chunked(2).forEach { rowItems ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    rowItems.forEach { stat ->
                        StatItem(
                            title = stat.first,
                            value = stat.second,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // If odd number, fill empty space
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

