package com.ord.orderscenter.screens_ui.screen_components


import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.Cogs
import compose.icons.fontawesomeicons.solid.ExclamationTriangle
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.ShoppingCart




import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ord.orderscenter.R
import compose.icons.FontAwesomeIcons

/* ---------------------------------------------------
   ACTION BUTTONS BOX
---------------------------------------------------- */

@Composable
fun ActionButtonsBox(
    onAddOrder: () -> Unit,
    onAllOrders: () -> Unit,
    onViewPaid: () -> Unit,
    onViewUnpaid: () -> Unit,
    onSettings: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.lavender_grey),
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ---------- Row 1 ----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionOutlinedButton(
                    text = "Add Order",
                    icon = FontAwesomeIcons.Solid.Plus,
                    modifier = Modifier.weight(1f),
                    onClick = onAddOrder
                )

                ActionOutlinedButton(
                    text = "All Orders",
                    icon = FontAwesomeIcons.Solid.ShoppingCart,
                    modifier = Modifier.weight(1f),
                    onClick = onAllOrders
                )
            }

            // ---------- Row 2 ----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionOutlinedButton(
                    text = "Paid Orders",
                    icon = FontAwesomeIcons.Solid.CheckCircle,
                    modifier = Modifier.weight(1f),
                    onClick = onViewPaid
                )

                ActionOutlinedButton(
                    text = "Unpaid Orders",
                    icon = FontAwesomeIcons.Solid.ExclamationTriangle,
                    modifier = Modifier.weight(1f),
                    onClick = onViewUnpaid
                )
            }

            // ---------- Row 3 ----------
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionOutlinedButton(
                    text = "Settings",
                    icon = FontAwesomeIcons.Solid.Cogs,
                    modifier = Modifier.weight(1f),
                    onClick = onSettings
                )
            }
        }
    }
}

/* ---------------------------------------------------
   OUTLINED BUTTON (ICON TOP, TEXT BOTTOM)
---------------------------------------------------- */

@Composable
fun ActionOutlinedButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val borderColor = colorResource(id = R.color.punch_red)
    val contentColor = colorResource(id = R.color.space_indigo)

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = contentColor,
                modifier = Modifier.size(26.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                fontSize = 12.sp,
                color = contentColor
            )
        }
    }
}
