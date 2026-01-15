package com.ord.orderscenter.screens_ui


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import com.ord.orderscenter.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ord.orderscenter.navgraph.Screen
import com.ord.orderscenter.utils.StatusBarColor
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.ShareAlt
import compose.icons.fontawesomeicons.solid.Times
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrderScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)


    var customerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var addressDesc by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Order", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.AngleLeft,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },

        ) { paddingValues ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.white))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                        end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
                    )
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
                        // 🧾 Other Fields
                        OutlinedTextField(
                            value = customerName,
                            onValueChange = { customerName = it },
                            label = { Text("Customer/Business name *") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = outlinedFieldColors()
                        )

                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("customer contact*") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = outlinedFieldColors()
                        )


                        OutlinedTextField(
                            value = addressDesc,
                            onValueChange = { addressDesc = it },
                            label = { Text("Customer address") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = outlinedFieldColors()
                        )
                    }

                    }



                var items by remember { mutableStateOf(listOf(NamePriceItem())) }


                // Card 1
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {


                        items.forEachIndexed { index, item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .background(
                                        color = colorResource(id = R.color.space_indigo),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                // Remove Button

                                IconButton(
                                    onClick = {
                                        items = items.toMutableList().also { it.removeAt(index) }
                                    },
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .size(40.dp)
                                        .background(
                                            color = colorResource(id = R.color.flag_red),
                                            shape = CircleShape
                                        )
                                        .align(Alignment.End)
                                ) {
                                    Icon(
                                        imageVector = FontAwesomeIcons.Solid.Times,
                                        contentDescription = "times delete",
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }


                                // Item Name
                                OutlinedTextField(
                                    value = item.name,
                                    onValueChange = { newValue ->
                                        items = items.toMutableList().also {
                                            it[index] = it[index].copy(name = newValue)
                                        }
                                    },
                                    label = { Text("Item name *") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                        focusedBorderColor = backgroundColor,
                                        unfocusedBorderColor = Color.Gray,
                                        focusedLabelColor = backgroundColor,
                                        cursorColor = backgroundColor
                                    ),
                                    isError = item.name.isBlank(),
                                    singleLine = true,
                                )
                                if (item.name.isBlank()) {
                                    Text(
                                        text = "Name cannot be empty",
                                        color = Color.Red,
                                        fontSize = 12.sp
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Price
                                OutlinedTextField(
                                    value = item.price,
                                    onValueChange = { newValue ->
                                        val updatedList = items.toMutableList()
                                        val quantityValue = updatedList[index].quantity.toIntOrNull() ?: 0
                                        val priceValue = newValue.toDoubleOrNull() ?: 0.0
                                        val newSubtotal = (priceValue * quantityValue).toString()
                                        updatedList[index] = updatedList[index].copy(
                                            price = newValue,
                                            subTotal = newSubtotal
                                        )
                                        items = updatedList
                                    },
                                    label = { Text("Price") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                        focusedBorderColor = backgroundColor,
                                        unfocusedBorderColor = Color.Gray,
                                        focusedLabelColor = backgroundColor,
                                        cursorColor = backgroundColor
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    isError = item.price.isBlank() || item.price.toDoubleOrNull() == null,
                                    singleLine = true,
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Quantity
                                OutlinedTextField(
                                    value = item.quantity,
                                    onValueChange = { newValue ->
                                        val updatedList = items.toMutableList()
                                        val priceValue = updatedList[index].price.toDoubleOrNull() ?: 0.0
                                        val quantityValue = newValue.toIntOrNull() ?: 0
                                        val newSubtotal = (priceValue * quantityValue).toString()
                                        updatedList[index] = updatedList[index].copy(
                                            quantity = newValue,
                                            subTotal = newSubtotal
                                        )
                                        items = updatedList
                                    },
                                    label = { Text("Quantity") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                        focusedBorderColor = backgroundColor,
                                        unfocusedBorderColor = Color.Gray,
                                        focusedLabelColor = backgroundColor,
                                        cursorColor = backgroundColor
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    isError = item.quantity.isBlank() || item.quantity.toIntOrNull() == null,
                                    singleLine = true,
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Subtotal (Read-Only)
                                OutlinedTextField(
                                    value = item.subTotal,
                                    onValueChange = {}, // No editing allowed
                                    label = { Text("Subtotal") },
                                    modifier = Modifier.fillMaxWidth(),
                                    readOnly = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                        focusedBorderColor = backgroundColor,
                                        unfocusedBorderColor = Color.Gray,
                                        focusedLabelColor = backgroundColor,
                                        cursorColor = backgroundColor
                                    ),
                                    singleLine = true,
                                )
                            }
                        }


                        Button(
                            onClick = {
                                items = items + NamePriceItem() // Add a new empty field set
                            },
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.Start),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id= R.color.lavender_grey), // Green background
                                contentColor = Color.White          // White text
                            )
                        ) {
                            Text("Add Item")
                        }

                    }


                }


                Button(
                    onClick = {

                        // ✅ Ensure at least one item exists
                        if (items.isEmpty()) {
                            Toast.makeText(
                                context,
                                "You must add at least one item before proceeding.",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }


//                            showPopup = true
                        // ✅ Validation
                        // Validation: Ensure all fields are filled
                        val hasEmptyFields = items.any {
                            it.name.isBlank() ||
                                    it.price.isBlank() || it.price.toDoubleOrNull() == null ||
                                    it.quantity.isBlank() || it.quantity.toIntOrNull() == null
                        }



                        if (hasEmptyFields) {
                            Toast.makeText(
                                context,
                                "Please fill all required fields (Name, Price, Quantity) before proceeding.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            //save logic here

                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Plus,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save Order",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

            }

        }
    }




}






@Preview(showBackground = true)
@Composable
fun AddOrderScreenPreview() {
    AddOrderScreen(navController = rememberNavController())
}

@Composable
fun outlinedFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
    focusedContainerColor = Color.White.copy(alpha = 0.95f),
    focusedBorderColor = colorResource(id = R.color.punch_red),
    unfocusedBorderColor = Color.Gray,
    focusedLabelColor = colorResource(id = R.color.punch_red),
    cursorColor = colorResource(id = R.color.punch_red)
)


data class NamePriceItem(
    val name: String = "",
    val price: String = "",
    val subTotal: String = "0",
    val quantity: String = "1"
)

