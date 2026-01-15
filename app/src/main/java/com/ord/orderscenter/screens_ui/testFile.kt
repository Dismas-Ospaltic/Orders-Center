//package com.ord.orderscenter.screens_ui
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.ord.orderscenter.R
//import com.ord.orderscenter.screens_ui.outlinedFieldColors
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.solid.AngleLeft
//import compose.icons.fontawesomeicons.solid.Plus
//import compose.icons.fontawesomeicons.solid.Trash
//
//// ----------------------------------------------------
//// DATA MODEL
//// ----------------------------------------------------
//data class NamePriceItem(
//    val name: String = "",
//    val price: String = "",
//    val quantity: String = ""
//)
//
//// ----------------------------------------------------
//// MAIN SCREEN
//// ----------------------------------------------------
//@Composable
//fun AddOrderScreen(
//    onBack: () -> Unit = {}
//) {
//    val context = LocalContext.current
//    var items by remember { mutableStateOf(listOf(NamePriceItem())) }
//    var showValidationErrors by remember { mutableStateOf(false) }
//
//    var customerName by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var addressDesc by remember { mutableStateOf("") }
//
//
//    val hasErrors =
//        customerName.isBlank() ||
//                phone.length < 9 ||
//                items.any {
//                    it.name.isBlank() ||
//                            it.price.toDoubleOrNull() == null ||
//                            it.quantity.toIntOrNull() == null
//                }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF6F6F6))
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp)
//    ) {
//
//        // ---------------- HEADER ----------------
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = onBack,
//                modifier = Modifier
//                    .size(36.dp)
//                    .background(Color(0xFF2E2E6D), CircleShape)
//            ) {
//                Icon(
//                    imageVector = FontAwesomeIcons.Solid.AngleLeft,
//                    contentDescription = "Back",
//                    tint = Color.White,
//                    modifier = Modifier.size(18.dp) // 👈 thinner look
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Text(
//                text = "Add Order",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        CustomerInfoSection(
//            customerName = customerName,
//            phone = phone,
//            addressDesc = addressDesc,
//            showErrors = showValidationErrors,
//            onNameChange = { customerName = it },
//            onPhoneChange = { phone = it },
//            onAddressChange = { addressDesc = it }
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        // ---------------- ITEMS ----------------
//        items.forEachIndexed { index, item ->
//            ItemCard(
//                item = item,
//                showErrors = showValidationErrors,
//                onUpdate = { updated ->
//                    items = items.toMutableList().also { it[index] = updated }
//                },
//                onDelete = {
//                    items = items.toMutableList().also { it.removeAt(index) }
//                }
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        // ---------------- ADD ITEM BUTTON ----------------
//        Button(
//            onClick = {
//                items = items + NamePriceItem()
//            },
//            modifier = Modifier.align(Alignment.End),
//            shape = RoundedCornerShape(50),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF2E2E6D)
//            )
//        ) {
//            Icon(
//                imageVector = FontAwesomeIcons.Solid.Plus,
//                contentDescription = null,
//                modifier = Modifier.size(18.dp)
//            )
//            Spacer(modifier = Modifier.width(6.dp))
//            Text("Add Item")
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // ---------------- SAVE BUTTON ----------------
//        Button(
//            onClick = {
//                showValidationErrors = true
//
//                val hasErrors = items.any {
//                    it.name.isBlank() ||
//                            it.price.toDoubleOrNull() == null ||
//                            it.quantity.toIntOrNull() == null
//                }
//
//                if (hasErrors) {
//                    Toast.makeText(
//                        context,
//                        "Fix errors before saving",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        context,
//                        "Order saved successfully",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(56.dp),
//            shape = RoundedCornerShape(14.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF2E2E6D)
//            )
//        ) {
//            Text("Save Order", color = Color.White, fontSize = 16.sp)
//        }
//    }
//}
//
//// ----------------------------------------------------
//// ITEM CARD
//// ----------------------------------------------------
//@Composable
//fun ItemCard(
//    item: NamePriceItem,
//    showErrors: Boolean,
//    onUpdate: (NamePriceItem) -> Unit,
//    onDelete: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White, RoundedCornerShape(12.dp))
//            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
//            .padding(16.dp)
//    ) {
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Item",
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.weight(1f)
//            )
//
//            IconButton(
//                onClick = onDelete,
//                modifier = Modifier
//                    .size(36.dp)
//                    .background(Color.Red.copy(alpha = 0.85f), CircleShape)
//            ) {
//                Icon(
//                    imageVector = FontAwesomeIcons.Solid.Trash,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.size(16.dp)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // NAME
//        OutlinedTextField(
//            value = item.name,
//            onValueChange = { onUpdate(item.copy(name = it)) },
//            label = { Text("Item Name") },
//            isError = showErrors && item.name.isBlank(),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // PRICE & QUANTITY
//        Row {
//            OutlinedTextField(
//                value = item.price,
//                onValueChange = { onUpdate(item.copy(price = it)) },
//                label = { Text("Price") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                isError = showErrors && item.price.toDoubleOrNull() == null,
//                modifier = Modifier.weight(1f)
//            )
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            OutlinedTextField(
//                value = item.quantity,
//                onValueChange = { onUpdate(item.copy(quantity = it)) },
//                label = { Text("Qty") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                isError = showErrors && item.quantity.toIntOrNull() == null,
//                modifier = Modifier.weight(1f)
//            )
//        }
//    }
//}
//
//
//@Composable
//fun CustomerInfoSection(
//    customerName: String,
//    phone: String,
//    addressDesc: String,
//    showErrors: Boolean,
//    onNameChange: (String) -> Unit,
//    onPhoneChange: (String) -> Unit,
//    onAddressChange: (String) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White, RoundedCornerShape(12.dp))
//            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
//            .padding(16.dp)
//    ) {
//
//        Text(
//            text = "Customer Information",
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 16.sp
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = customerName,
//            onValueChange = onNameChange,
//            label = { Text("Customer Name") },
//            isError = showErrors && customerName.isBlank(),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = phone,
//            onValueChange = onPhoneChange,
//            label = { Text("Phone Number") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//            isError = showErrors && phone.length < 9,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = addressDesc,
//            onValueChange = onAddressChange,
//            label = { Text("Address / Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}
//
//
//
