
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ord.orderscenter.R
import com.ord.orderscenter.model.GeneralOrdersEntity
import com.ord.orderscenter.model.IndividualItemEntity
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.Trash
import org.koin.androidx.compose.koinViewModel

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrderScreen(
    navController: NavController
) {

    val backgroundColor = colorResource(id = R.color.punch_red)
//    StatusBarColor(backgroundColor)
    val context = LocalContext.current
    var items by remember { mutableStateOf(listOf(NamePriceItem())) }
    var showValidationErrors by remember { mutableStateOf(false) }

    var customerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var addressDesc by remember { mutableStateOf("") }
    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Record New Orders", color = Color.White) },
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
                        containerColor = colorResource(id=R.color.space_indigo)
                    )
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Plus,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Add an Item")
                }

                Spacer(modifier = Modifier.height(32.dp))

                // ---------------- SAVE BUTTON ----------------
                Button(
                    onClick = {
                        showValidationErrors = true

//
                        //  Ensure at least one item exists in the list
                        if (items.isEmpty()) {
                            Toast.makeText(
                                context,
                                "You must add at least one item for the order to continue.",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val hasErrors =
                            customerName.isBlank() ||
                                    phone.length < 9 ||
                                    items.any {
                                        it.name.isBlank() ||
                                                it.price.toDoubleOrNull() == null ||
                                                it.quantity.toIntOrNull() == null
                                    }

                        if (hasErrors) {
                            Toast.makeText(
                                context,
                                "make sure to fill all fields highlighted in red",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {




                            val orderNumber = generateOrderNumber()

                     // Calculate totalOrder while inserting items
                            var totalOrder = 0f

                            items.forEachIndexed { index, item ->
                                val itemTotal = item.price.toFloat() * item.quantity.toInt()
                                totalOrder += itemTotal

                                individualOrderViewModel.insertIndividualOrder(
                                    IndividualItemEntity(
                                        itemName = item.name,
                                        price = item.price.toFloat(),
                                        quantity = item.quantity.toInt(),
                                        qtyDescription = item.quantity.toString(),
                                        orderNumber = orderNumber,
                                        itemId = generateItemId(orderNumber, index)
                                    )
                                )
                            }


                            // Insert general order with the total calculated
                            generalOrderViewModel.insertGenOrder(
                                GeneralOrdersEntity(
                                    customerName = customerName,
                                    phone = phone,
                                    addressDescription = addressDesc,
                                    totalOrder = totalOrder,
                                    orderNumber = orderNumber
                                )
                            )

                            navController.popBackStack()

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
    }
}





    @Preview(showBackground = true)
    @Composable
    fun AddOrderScreenPreview() {
        AddOrderScreen(navController = rememberNavController())
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

//Order Number Generator
fun generateOrderNumber(): String {
    val randomPart = (100..999).random()
    val timePart = System.currentTimeMillis()
    return "ORD-$randomPart-$timePart"
}


//Item ID Generator (per item)
fun generateItemId(
    orderNumber: String,
    index: Int
): String {
    val randomPart = (10..99).random()
    val timePart = System.currentTimeMillis()
    return "ITEM-$orderNumber-$randomPart-$index-$timePart"
}





