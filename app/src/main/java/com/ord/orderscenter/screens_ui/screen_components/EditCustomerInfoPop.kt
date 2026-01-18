package com.ord.orderscenter.screens_ui.screen_components


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.ord.orderscenter.screens_ui.CustomerInfoSection
import com.ord.orderscenter.screens_ui.NamePriceItem
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
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
fun EditCustomerInfoPop(
//    navController: NavController,
    onDismiss: () -> Unit,
    orderNumber: String,
    status: String,
    total: Float,
    customerName: String,
    phone: String,
    address: String
) {

    val backgroundColor = colorResource(id = R.color.punch_red)
//    StatusBarColor(backgroundColor)
    val context = LocalContext.current
    var showValidationErrors by remember { mutableStateOf(false) }

    var customerName by remember { mutableStateOf(customerName) }
    var phone by remember { mutableStateOf(phone) }
    var addressDesc by remember { mutableStateOf(address) }
    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())



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
                Text(text = "Edit Customer Info", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                Spacer(modifier = Modifier.height(24.dp))

                CustomerInfoSection(
                    customerName = customerName,
                    phone = phone,
                    addressDesc = addressDesc,
                    showErrors = showValidationErrors,
                    onNameChange = { customerName = it },
                    onPhoneChange = { phone = it },
                    onAddressChange = { addressDesc = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // CANCEL BUTTON
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Cancel")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // UPDATE BUTTON
                    Button(
                        onClick = {
                            showValidationErrors = true

                            if (customerName.isNotBlank() && phone.isNotBlank()) {

                                generalOrderViewModel.updateGenOrderById(
                                  customerName = customerName,
                                    phone = phone,
                                    addressDescription = addressDesc,
                                    totalOrder = total,
                                    orderNumber = orderNumber
                                )
                                Toast.makeText(context, "Customer info updated", Toast.LENGTH_SHORT).show()
                                onDismiss()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.punch_red),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Update")
                    }
                }

            }
        }
    }

}