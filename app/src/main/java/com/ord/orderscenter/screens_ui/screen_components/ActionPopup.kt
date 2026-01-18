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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ord.orderscenter.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Edit
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.Edit
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Truck
import compose.icons.fontawesomeicons.solid.TruckLoading
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionPopup(
    navController: NavController,
    onDismiss: () -> Unit,
    orderNumber: String,
    status: String
) {

//    val backgroundColor = colorResource(id = R.color.coral)

    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDetailPopUp by remember { mutableStateOf(false) }
    var showReturnActionDialog by remember { mutableStateOf(false)}
    var showSupplierDetDialog by remember { mutableStateOf(false) }
    var showReturnReasonDialog by remember { mutableStateOf(false) }


//    val supplierViewModel: SupplierViewModel = koinViewModel()
//    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
//    val stockViewmodel: StockViewModel = koinViewModel()
//    val stockUpdates by stockViewmodel.stockList.collectAsState(initial = emptyList())
//    val returnViewModel: ReturnViewModel = koinViewModel()
//
//    val currentDate =  System.currentTimeMillis()
//    val todayDate = standardDateFormat(currentDate)


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
                Text(text = "Manage An Order", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDetailPopUp = false
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.InfoCircle,
                        contentDescription = "info",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "View Details", fontSize = 16.sp)
                }

                if (status == "unpaid") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CheckCircle,
                            contentDescription = "Mark Paid",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(id=R.color.lavender_grey)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Mark as Paid", fontSize = 16.sp)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showReturnActionDialog = true
//                            showReturnDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Edit,
                            contentDescription = "edit",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit Order", fontSize = 16.sp)
                    }
                }

                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDeleteDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "Delete update",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete update", fontSize = 16.sp)
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

//                    stockViewmodel.deleteStock(stockId)
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



    if (showDetailPopUp) {
        OrderDetailPopup(
            onDismiss = {  showDetailPopUp = false ;
                onDismiss()},
            orderNumber = orderNumber,
            status = status
        )
    }
//
//    if (showSupplierDetDialog) {
//        SupplierDetailPop(
//            onDismiss = {  showSupplierDetDialog = false ;
//                onDismiss()},
//            supplierId = supplierId
//        )
//
//    }
//
//    if (showReturnReasonDialog) {
//        ReturnReasonPop(
//            onDismiss = {  showReturnReasonDialog = false ;
//                onDismiss()},
//            stockId = stockId
//        )
//
//    }




}