package com.ord.orderscenter.screens_ui.screen_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ord.orderscenter.screens_ui.StatItem

@Composable
fun StatsBox() {

    val stats = listOf(
        "Total Orders" to "50",
        "Total Sales" to "100",
        "Total Requests" to "2003",
        "Total Amount" to "10,000",
        "Unpaid No" to "30"
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

