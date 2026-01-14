package com.ord.orderscenter.screens_ui.screen_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ord.orderscenter.screens_ui.ActionOutlinedButton

@Composable
fun ActionButtonsBox(
    onAddOrder: () -> Unit,
    onAddSales: () -> Unit,
    onViewPaid: () -> Unit,
    onViewUnpaid: () -> Unit,
    onSettings: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Row 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionOutlinedButton(
                    text = "Add Order",
                    icon = Icons.Default.Add,
                    modifier = Modifier.weight(1f),
                    onClick = onAddOrder
                )

                ActionOutlinedButton(
                    text = "Add Sales",
                    icon = Icons.Default.ShoppingCart,
                    modifier = Modifier.weight(1f),
                    onClick = onAddSales
                )
            }

            // Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionOutlinedButton(
                    text = "Paid Orders",
                    icon = Icons.Default.CheckCircle,
                    modifier = Modifier.weight(1f),
                    onClick = onViewPaid
                )

                ActionOutlinedButton(
                    text = "Unpaid Orders",
                    icon = Icons.Default.Warning,
                    modifier = Modifier.weight(1f),
                    onClick = onViewUnpaid
                )
            }

            // Row 3 (single button)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionOutlinedButton(
                    text = "Settings",
                    icon = Icons.Default.Settings,
                    modifier = Modifier.weight(1f),
                    onClick = onSettings
                )
            }
        }
    }
}
