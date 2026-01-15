//package com.ord.orderscenter.screens_ui
//
//
//import android.content.Intent
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLayoutDirection
//import androidx.compose.ui.res.colorResource
//import com.ord.orderscenter.R
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.ord.orderscenter.navgraph.Screen
//import com.ord.orderscenter.utils.StatusBarColor
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Regular
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.regular.ThumbsUp
//import compose.icons.fontawesomeicons.regular.TrashAlt
//import compose.icons.fontawesomeicons.solid.AngleLeft
//import compose.icons.fontawesomeicons.solid.CircleNotch
//import compose.icons.fontawesomeicons.solid.Cog
//import compose.icons.fontawesomeicons.solid.InfoCircle
//import compose.icons.fontawesomeicons.solid.Pen
//import compose.icons.fontawesomeicons.solid.Plus
//import compose.icons.fontawesomeicons.solid.ShareAlt
//import compose.icons.fontawesomeicons.solid.Times
//import org.koin.androidx.compose.koinViewModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddOrderScreen(navController: NavController) {
//    val backgroundColor = colorResource(id = R.color.punch_red)
//    StatusBarColor(backgroundColor)
//
//
//    var customerName by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var addressDesc by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("Add Order", color = Color.White) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = FontAwesomeIcons.Solid.AngleLeft,
//                            contentDescription = "Back",
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = backgroundColor
//                )
//            )
//        },
//
//        ) { paddingValues ->
//        // Scrollable content
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
//                    bottom = paddingValues.calculateBottomPadding()
//                )
//                .verticalScroll(rememberScrollState())
//                .background(colorResource(id = R.color.white))
//        ) {
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(
//                        start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
//                        end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
//                    )
//            ) {
//
//
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(
//                            width = 1.dp,
//                            color = colorResource(id = R.color.lavender_grey),
//                            shape = MaterialTheme.shapes.medium
//                        )
//                        .padding(12.dp)
//                ) {
//
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(12.dp)
//                    ) {
//                        // 🧾 Other Fields
//                        OutlinedTextField(
//                            value = customerName,
//                            onValueChange = { customerName = it },
//                            label = { Text("Customer/Business name *") },
//                            modifier = Modifier.fillMaxWidth(),
//                            singleLine = true,
//                            colors = outlinedFieldColors()
//                        )
//
//                        OutlinedTextField(
//                            value = phone,
//                            onValueChange = { phone = it },
//                            label = { Text("customer contact*") },
//                            modifier = Modifier.fillMaxWidth(),
//                            singleLine = true,
//                            colors = outlinedFieldColors()
//                        )
//
//
//                        OutlinedTextField(
//                            value = addressDesc,
//                            onValueChange = { addressDesc = it },
//                            label = { Text("Customer address") },
//                            modifier = Modifier.fillMaxWidth(),
//                            singleLine = true,
//                            colors = outlinedFieldColors()
//                        )
//                    }
//
//                    }
//
//
//
//                var items by remember { mutableStateOf(listOf(NamePriceItem())) }
//
//
//                // Card 1
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
//                        .padding(16.dp)
//                ) {
//                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//
//
//                        items.forEachIndexed { index, item ->
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 8.dp)
//                                    .background(
//                                        color = colorResource(id = R.color.space_indigo),
//                                        shape = RoundedCornerShape(12.dp)
//                                    )
//                                    .padding(16.dp)
//                            ) {
//                                // Remove Button
//
//                                IconButton(
//                                    onClick = {
//                                        items = items.toMutableList().also { it.removeAt(index) }
//                                    },
//                                    modifier = Modifier
//                                        .padding(16.dp)
//                                        .size(40.dp)
//                                        .background(
//                                            color = colorResource(id = R.color.flag_red),
//                                            shape = CircleShape
//                                        )
//                                        .align(Alignment.End)
//                                ) {
//                                    Icon(
//                                        imageVector = FontAwesomeIcons.Solid.Times,
//                                        contentDescription = "times delete",
//                                        tint = Color.White,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                }
//
//
//                                // Item Name
//                                OutlinedTextField(
//                                    value = item.name,
//                                    onValueChange = { newValue ->
//                                        items = items.toMutableList().also {
//                                            it[index] = it[index].copy(name = newValue)
//                                        }
//                                    },
//                                    label = { Text("Item name *") },
//                                    modifier = Modifier.fillMaxWidth(),
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                        focusedBorderColor = backgroundColor,
//                                        unfocusedBorderColor = Color.Gray,
//                                        focusedLabelColor = backgroundColor,
//                                        cursorColor = backgroundColor
//                                    ),
//                                    isError = item.name.isBlank(),
//                                    singleLine = true,
//                                )
//                                if (item.name.isBlank()) {
//                                    Text(
//                                        text = "Name cannot be empty",
//                                        color = Color.Red,
//                                        fontSize = 12.sp
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.height(8.dp))
//
//                                // Price
//                                OutlinedTextField(
//                                    value = item.price,
//                                    onValueChange = { newValue ->
//                                        val updatedList = items.toMutableList()
//                                        val quantityValue = updatedList[index].quantity.toIntOrNull() ?: 0
//                                        val priceValue = newValue.toDoubleOrNull() ?: 0.0
//                                        val newSubtotal = (priceValue * quantityValue).toString()
//                                        updatedList[index] = updatedList[index].copy(
//                                            price = newValue,
//                                            subTotal = newSubtotal
//                                        )
//                                        items = updatedList
//                                    },
//                                    label = { Text("Price") },
//                                    modifier = Modifier.fillMaxWidth(),
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                        focusedBorderColor = backgroundColor,
//                                        unfocusedBorderColor = Color.Gray,
//                                        focusedLabelColor = backgroundColor,
//                                        cursorColor = backgroundColor
//                                    ),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                                    isError = item.price.isBlank() || item.price.toDoubleOrNull() == null,
//                                    singleLine = true,
//                                )
//
//                                Spacer(modifier = Modifier.height(8.dp))
//
//                                // Quantity
//                                OutlinedTextField(
//                                    value = item.quantity,
//                                    onValueChange = { newValue ->
//                                        val updatedList = items.toMutableList()
//                                        val priceValue = updatedList[index].price.toDoubleOrNull() ?: 0.0
//                                        val quantityValue = newValue.toIntOrNull() ?: 0
//                                        val newSubtotal = (priceValue * quantityValue).toString()
//                                        updatedList[index] = updatedList[index].copy(
//                                            quantity = newValue,
//                                            subTotal = newSubtotal
//                                        )
//                                        items = updatedList
//                                    },
//                                    label = { Text("Quantity") },
//                                    modifier = Modifier.fillMaxWidth(),
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                        focusedBorderColor = backgroundColor,
//                                        unfocusedBorderColor = Color.Gray,
//                                        focusedLabelColor = backgroundColor,
//                                        cursorColor = backgroundColor
//                                    ),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                                    isError = item.quantity.isBlank() || item.quantity.toIntOrNull() == null,
//                                    singleLine = true,
//                                )
//
//                                Spacer(modifier = Modifier.height(8.dp))
//
//                                // Subtotal (Read-Only)
//                                OutlinedTextField(
//                                    value = item.subTotal,
//                                    onValueChange = {}, // No editing allowed
//                                    label = { Text("Subtotal") },
//                                    modifier = Modifier.fillMaxWidth(),
//                                    readOnly = true,
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                        focusedBorderColor = backgroundColor,
//                                        unfocusedBorderColor = Color.Gray,
//                                        focusedLabelColor = backgroundColor,
//                                        cursorColor = backgroundColor
//                                    ),
//                                    singleLine = true,
//                                )
//                            }
//                        }
//
//
//                        Button(
//                            onClick = {
//                                items = items + NamePriceItem() // Add a new empty field set
//                            },
//                            modifier = Modifier
//                                .padding(start = 16.dp)
//                                .align(Alignment.Start),
//                            shape = RoundedCornerShape(12.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = colorResource(id= R.color.lavender_grey), // Green background
//                                contentColor = Color.White          // White text
//                            )
//                        ) {
//                            Text("Add Item")
//                        }
//
//                    }
//
//
//                }
//
//
//                Button(
//                    onClick = {
//
//                        // ✅ Ensure at least one item exists
//                        if (items.isEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "You must add at least one item before proceeding.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            return@Button
//                        }
//
//
////                            showPopup = true
//                        // ✅ Validation
//                        // Validation: Ensure all fields are filled
//                        val hasEmptyFields = items.any {
//                            it.name.isBlank() ||
//                                    it.price.isBlank() || it.price.toDoubleOrNull() == null ||
//                                    it.quantity.isBlank() || it.quantity.toIntOrNull() == null
//                        }
//
//
//
//                        if (hasEmptyFields) {
//                            Toast.makeText(
//                                context,
//                                "Please fill all required fields (Name, Price, Quantity) before proceeding.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
//                            //save logic here
//
//                        }
//
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
//                ) {
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Plus,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Save Order",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//
//            }
//
//        }
//    }
//
//
//
//
//}
//
//
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun AddOrderScreenPreview() {
//    AddOrderScreen(navController = rememberNavController())
//}
//
//@Composable
//fun outlinedFieldColors() = OutlinedTextFieldDefaults.colors(
//    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//    focusedContainerColor = Color.White.copy(alpha = 0.95f),
//    focusedBorderColor = colorResource(id = R.color.punch_red),
//    unfocusedBorderColor = Color.Gray,
//    focusedLabelColor = colorResource(id = R.color.punch_red),
//    cursorColor = colorResource(id = R.color.punch_red)
//)
//
//
//data class NamePriceItem(
//    val name: String = "",
//    val price: String = "",
//    val subTotal: String = "0",
//    val quantity: String = "1"
//)
//
package com.ord.orderscenter.screens_ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ord.orderscenter.R
//import com.ord.orderscenter.screens_ui.outlinedFieldColors
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.Trash

// ----------------------------------------------------
// DATA MODEL
// ----------------------------------------------------
data class NamePriceItem(
    val name: String = "",
    val price: String = "",
    val quantity: String = ""
)

// ----------------------------------------------------
// MAIN SCREEN
// ----------------------------------------------------
@Composable
fun AddOrderScreen(
    navController: NavController,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var items by remember { mutableStateOf(listOf(NamePriceItem())) }
    var showValidationErrors by remember { mutableStateOf(false) }

    var customerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var addressDesc by remember { mutableStateOf("") }


    val hasErrors =
        customerName.isBlank() ||
                phone.length < 9 ||
                items.any {
                    it.name.isBlank() ||
                            it.price.toDoubleOrNull() == null ||
                            it.quantity.toIntOrNull() == null
                }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // ---------------- HEADER ----------------
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF2E2E6D), CircleShape)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.AngleLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp) // 👈 thinner look
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Add Order",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

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
        // ---------------- ITEMS ----------------
        items.forEachIndexed { index, item ->
            ItemCard(
                item = item,
                showErrors = showValidationErrors,
                onUpdate = { updated ->
                    items = items.toMutableList().also { it[index] = updated }
                },
                onDelete = {
                    items = items.toMutableList().also { it.removeAt(index) }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        // ---------------- ADD ITEM BUTTON ----------------
        Button(
            onClick = {
                items = items + NamePriceItem()
            },
            modifier = Modifier.align(Alignment.End),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E2E6D)
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Plus,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // ---------------- SAVE BUTTON ----------------
        Button(
            onClick = {
                showValidationErrors = true

                val hasErrors = items.any {
                    it.name.isBlank() ||
                            it.price.toDoubleOrNull() == null ||
                            it.quantity.toIntOrNull() == null
                }

                if (hasErrors) {
                    Toast.makeText(
                        context,
                        "Fix errors before saving",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Order saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E2E6D)
            )
        ) {
            Text("Save Order", color = Color.White, fontSize = 16.sp)
        }
    }
}

// ----------------------------------------------------
// ITEM CARD
// ----------------------------------------------------
@Composable
fun ItemCard(
    item: NamePriceItem,
    showErrors: Boolean,
    onUpdate: (NamePriceItem) -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Item",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.Red.copy(alpha = 0.85f), CircleShape)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Trash,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // NAME
        OutlinedTextField(
            value = item.name,
            onValueChange = { onUpdate(item.copy(name = it)) },
            label = { Text("Item Name") },
            isError = showErrors && item.name.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // PRICE & QUANTITY
        Row {
            OutlinedTextField(
                value = item.price,
                onValueChange = { onUpdate(item.copy(price = it)) },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = showErrors && item.price.toDoubleOrNull() == null,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = item.quantity,
                onValueChange = { onUpdate(item.copy(quantity = it)) },
                label = { Text("Qty") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = showErrors && item.quantity.toIntOrNull() == null,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun CustomerInfoSection(
    customerName: String,
    phone: String,
    addressDesc: String,
    showErrors: Boolean,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onAddressChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

        Text(
            text = "Customer Information",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = onNameChange,
            label = { Text("Customer Name") },
            isError = showErrors && customerName.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = showErrors && phone.length < 9,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = addressDesc,
            onValueChange = onAddressChange,
            label = { Text("Address / Description") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}



