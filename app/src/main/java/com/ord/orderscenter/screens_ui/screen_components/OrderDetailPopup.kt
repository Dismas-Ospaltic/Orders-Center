package com.ord.orderscenter.screens_ui.screen_components




import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ord.orderscenter.R
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailPopup(
    onDismiss: () -> Unit,
    orderNumber: String,
    status: String
) {



    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }


    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())



    val orderItems by individualOrderViewModel.orderItems.collectAsState()

    LaunchedEffect(orderNumber) {
        individualOrderViewModel.getAllListItemOrders(orderNumber)
    }



    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()), // Adjust for keyboard
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                Text(text = "Order Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                if (orderItems.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No items for this order",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                } else {
                    orderItems.forEach { productItem ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {

                                // 🔝 Top row (Name + Delete)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {

                                    Text(
                                        text = productItem.itemName,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(end = 8.dp),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )

//                                    IconButton(
//                                        onClick = { showDeleteDialog = true },
//                                        modifier = Modifier
//                                            .size(34.dp)
//                                            .background(
//                                                color = Color.Red.copy(alpha = 0.85f),
//                                                shape = CircleShape
//                                            )
//                                    ) {
//                                        Icon(
//                                            imageVector = FontAwesomeIcons.Solid.Trash,
//                                            contentDescription = "Delete",
//                                            tint = Color.White,
//                                            modifier = Modifier.size(16.dp)
//                                        )
//                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // 📦 Details row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Column {
                                        Text(
                                            text = "Qty",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.quantity.toString(),
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "Price",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = "${productItem.price}",
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Column(horizontalAlignment = Alignment.End) {
                                        Text(
                                            text = "Total",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = "${productItem.price * productItem.quantity}",
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }



            }
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Order Item") },
            text = { Text("Do you Want to permanently delete from the list?") },
            confirmButton = {
                TextButton(onClick = {
                    Toast.makeText(context, "Item Order has been deleted", Toast.LENGTH_SHORT).show()
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Delete", color = colorResource(id = R.color.punch_red))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Cancel", color = colorResource(id = R.color.lavender_grey))
                }
            }
        )
    }



}