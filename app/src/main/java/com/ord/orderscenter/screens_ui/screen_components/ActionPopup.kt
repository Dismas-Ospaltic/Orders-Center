package com.ord.orderscenter.screens_ui.screen_components


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ord.orderscenter.R
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.Edit
import compose.icons.fontawesomeicons.solid.InfoCircle
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionPopup(
    navController: NavController,
    onDismiss: () -> Unit,
    orderNumber: String,
    status: String,
    total: Float,
    customerName: String,
    phone: String,
    address: String,
) {



    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showMarkOrderDialog by remember { mutableStateOf(false) }
    var showDetailPopUp by remember { mutableStateOf(false) }
    var showEditDetailPopUp by remember { mutableStateOf(false) }

    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())



    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = colorResource(id = R.color.lavender_grey),
            tonalElevation = 12.dp,
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
                Text(text = "Manage An Order", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            showDetailPopUp = true
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.InfoCircle,
                        contentDescription = "info",
                        modifier = Modifier.size(20.dp),
                                tint = colorResource(id=R.color.space_indigo)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "View Details", fontSize = 16.sp)
                }

                if (status == "unpaid") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                showMarkOrderDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CheckCircle,
                            contentDescription = "Mark Paid",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(id=R.color.teal_200)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Mark as Paid", fontSize = 16.sp)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                showEditDetailPopUp = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Edit,
                            contentDescription = "edit",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(id=R.color.platinum)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit Order", fontSize = 16.sp)
                    }
                }

                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                showDeleteDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "Delete order",
                            modifier = Modifier.size(20.dp),
                                    tint = colorResource(id=R.color.flag_red)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete Order", fontSize = 16.sp)
                    }


            }
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Order") },
            text = { Text("Do you Want to permanently delete from the list?") },
            confirmButton = {
                TextButton(onClick = {
                     generalOrderViewModel.deleteOrderByNumber(orderNumber)
                    individualOrderViewModel.deleteSingleOrderByNumber(orderNumber)
                    Toast.makeText(context, "Order has been deleted", Toast.LENGTH_SHORT).show()
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Delete Order", color = colorResource(id = R.color.punch_red))
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


    if (showMarkOrderDialog) {
        AlertDialog(
            onDismissRequest = { showMarkOrderDialog = false },
            title = { Text("Mark Paid Orders") },
            text = { Text("Mark this Order as Paid? total Order is: $total ") },
            confirmButton = {
                TextButton(onClick = {
                    generalOrderViewModel.updateOrderStatusById("paid",orderNumber)

                    Toast.makeText(context, "Order no. $orderNumber marked as paid Successfully", Toast.LENGTH_SHORT).show()
                    showMarkOrderDialog = false
                    onDismiss()
                }) {
                    Text("Mark Order", color = colorResource(id = R.color.punch_red))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showMarkOrderDialog = false
                    onDismiss()
                }) {
                    Text("Cancel", color = colorResource(id = R.color.lavender_grey))
                }
            }
        )
    }


    if (showDetailPopUp) {
        OrderDetailPopup(
            onDismiss = {  showDetailPopUp = false ;
                onDismiss()},
            orderNumber = orderNumber,
            status = status
        )
    }


    if (showEditDetailPopUp) {
        EditCustomerInfoPop(
            onDismiss = {  showEditDetailPopUp = false ;
                onDismiss()},
            orderNumber = orderNumber,
            status = status,
            total=total,
            customerName = customerName,
            phone = phone,
            address = address
        )
    }




}