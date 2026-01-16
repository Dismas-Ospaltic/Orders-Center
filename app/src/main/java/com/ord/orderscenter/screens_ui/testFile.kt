//package com.inv.e_inventoryupdate.ui_screen
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.platform.LocalLayoutDirection
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.sp
//import com.inv.e_inventoryupdate.R
//import com.inv.e_inventoryupdate.model.StockEntity
//import com.inv.e_inventoryupdate.repository.StockRepository
//import com.inv.e_inventoryupdate.ui_screen.ui_components.AddStockPopUp
//import com.inv.e_inventoryupdate.ui_screen.ui_components.AppStatusBarDynamicColor
//import com.inv.e_inventoryupdate.ui_screen.ui_components.InvActionPop
//import com.inv.e_inventoryupdate.viewmodel.StockViewModel
//import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.solid.ExclamationCircle
//import compose.icons.fontawesomeicons.solid.Search
//import compose.icons.fontawesomeicons.solid.ShieldAlt
//import org.koin.androidx.compose.koinViewModel
//import kotlin.math.ceil
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InventoryScreen(navController: NavController) {
//
//    val backgroundColor = colorResource(id = R.color.baby_powder)
//    AppStatusBarDynamicColor(backgroundColor)
//
//    val searchQuery = remember { mutableStateOf("") }
//    var showStockDialog by remember { mutableStateOf(false) }
//
//    val stockViewmodel: StockViewModel = koinViewModel()
//    val supplierViewModel: SupplierViewModel = koinViewModel()
//    val suppliers by supplierViewModel.suppliers.collectAsState(initial = emptyList())
//    val stockUpdates by stockViewmodel.stockList.collectAsState(initial = emptyList())
//
//
//
//
////    // 🔹 Step 1: Mock Data
////    val mockData = remember {
////        (1..10_000).map {
////            StockItem(
////                date = when {
////                    it <= 3000 -> "2025-08-08"
////                    it <= 6000 -> "2025-08-09"
////                    else -> "2025-08-10"
////                },
////                productCode = "CODE${1000 + it}",
////                productName = "Product $it",
////                buyPrice = 50 + (it % 100),
////                sellPrice = 70 + (it % 100),
////                category = when {
////                    it % 3 == 0 -> "Dairy"
////                    it % 3 == 1 -> "Snacks"
////                    else -> "Beverages"
////                },
////                quantity = 10 + (it % 20)
////            )
////        }
////    }
//
//
//
////    // 🔹 Step 2: Group by Date
////    val groupedData = mockData.groupBy { it.date }
////
////    // 🔹 Step 3: Pagination setup
////    val itemsPerPage = 10
////    val totalPages = ceil(mockData.size / itemsPerPage.toFloat()).toInt()
////    var currentPage by remember { mutableStateOf(1) }
////
////    val paginatedData = mockData
////        .drop((currentPage - 1) * itemsPerPage)
////        .take(itemsPerPage)
////        .groupBy { it.date }
//
//
//
//    // 🔹 Group stock data by date (like before)
//    val groupedData = stockUpdates.groupBy { it.date }
//
//// 🔹 Pagination setup
//    val itemsPerPage = 10
//    val totalPages = ceil(stockUpdates.size / itemsPerPage.toFloat()).toInt().coerceAtLeast(1)
//    var currentPage by remember { mutableStateOf(1) }
//
//    val paginatedData = stockUpdates
//        .drop((currentPage - 1) * itemsPerPage)
//        .take(itemsPerPage)
//        .groupBy { it.date }
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Stock Update", color = colorResource(id = R.color.raisin_black)) },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = backgroundColor,
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
//        }
//
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
////                 paddingValues
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
//                    bottom = paddingValues.calculateBottomPadding() + 80.dp
//                )
//                .verticalScroll(rememberScrollState())
//                .background(colorResource(id = R.color.teal_700))
//        ) {
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
////                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Search an Update",
//                        color = colorResource(id = R.color.gray01),
//                        fontSize = 16.sp
//                    )
//                }
//
//                // Search Field
//                TextField(
//                    value = searchQuery.value,
//                    onValueChange = { searchQuery.value = it },
//                    placeholder = { Text(text = "Search...") },
//                    leadingIcon = {
//
//                        Icon(
//                            imageVector = FontAwesomeIcons.Solid.Search,
//                            contentDescription = "Search Icon",
//                            tint = Color.Gray,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp)
//                        .clip(RoundedCornerShape(4.dp))
//                        .background(Color.White),
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        disabledContainerColor = Color.Transparent,
//                        cursorColor = Color.Black,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//                    ),
//                    singleLine = true
//                )
//
////                Column(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                ) {
////
////                    PaginationControls(
////                        totalPages = totalPages,
////                        currentPage = currentPage,
////                        onPageChange = { newPage -> currentPage = newPage }
////                    )
//////                    paginatedData.forEach { (date, items) ->
//////                        Text(
//////                            text = date,
//////                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
//////                            fontSize = 18.sp,
//////                            fontWeight = FontWeight.Bold,
//////                            color = colorResource(id = R.color.white)
//////                        )
//////
//////                        items.forEach { stock ->
//////                            StockCard(stock)
//////                        }
//////                    }
////
////                    paginatedData.forEach { (date, items) ->
////                        Text(
////                            text = date,
////                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
////                            fontSize = 18.sp,
////                            fontWeight = FontWeight.Bold,
////                            color = colorResource(id = R.color.white)
////                        )
////
////                        items.forEach { stockEntity ->
////                            StockCard(stockEntity)
////                        }
////                    }
////
////
////
////                }
//
//// 🔹 Filter stock updates based on search text
//                val filteredStock = stockUpdates.filter { stock ->
//                    val query = searchQuery.value.trim().lowercase()
//                    query.isEmpty() || stock.productName.lowercase().contains(query) || stock.productCode.lowercase().contains(query)
//                }
//
//                val groupedFilteredData = filteredStock.groupBy { it.date }
//                val filteredTotalPages = ceil(filteredStock.size / itemsPerPage.toFloat()).toInt().coerceAtLeast(1)
//                val paginatedFilteredData = filteredStock
//                    .drop((currentPage - 1) * itemsPerPage)
//                    .take(itemsPerPage)
//                    .groupBy { it.date }
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//
//                    PaginationControls(
//                        totalPages = filteredTotalPages,
//                        currentPage = currentPage,
//                        onPageChange = { newPage -> currentPage = newPage }
//                    )
//
//                    if (filteredStock.isEmpty()) {
//                        // 🔹 Show message if no data found or list is empty
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 40.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = "No data available",
//                                fontSize = 16.sp,
//                                color = colorResource(id = R.color.gray01)
//                            )
//                        }
//                    } else {
//                        // 🔹 Show filtered paginated data
//                        paginatedFilteredData.forEach { (date, items) ->
//                            Text(
//                                text = date,
//                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = colorResource(id = R.color.white)
//                            )
//
//                            items.forEach { stockEntity ->
//                                StockCard(stockEntity, navController)
//                            }
//                        }
//                    }
//                }
//
//
//            }
//
//        }
//
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
//fun InventoryScreenPreview() {
//    InventoryScreen(navController = rememberNavController())
//}
//
//
//data class StockItem(
//    val date: String,
//    val productCode: String,
//    val productName: String,
//    val buyPrice: Int,
//    val sellPrice: Int,
//    val category: String,
//    val quantity: Int
//)
//
//
//@Composable
//fun StockCard(stock: StockEntity, navController: NavController) {
//
//    var selectedStockId by remember { mutableStateOf<String?>(null) }
//    var selectedISupplierId by remember { mutableStateOf<String?>(null) }
//    var selectedIDate by remember { mutableStateOf<String?>(null) }
//    var selectedIStatus by remember { mutableStateOf<String?>(null) }
//    var showActionDialog by remember { mutableStateOf(false) }
//
//    val expectedProfit = (stock.sellPrice - stock.buyPrice) * stock.quantity
//
////    Card(
////        modifier = Modifier
////            .fillMaxWidth()
////            .padding(horizontal = 4.dp, vertical = 2.dp)
////            .clickable {},
////        shape = RoundedCornerShape(8.dp),
////        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
////        colors = CardDefaults.cardColors(containerColor = Color.White)
////    ) {
////        Column(modifier = Modifier.padding(16.dp)) {
////
////            Box(
////                modifier = Modifier
////                    .clip(RoundedCornerShape(20.dp))
////                    .background(colorResource(id=R.color.yellow_green))
////                    .padding(horizontal = 10.dp, vertical = 4.dp)
////                    .align(Alignment.End)
////            ) {
////                Text(
////                    text = "status: ${stock.status}",
////                    fontSize = 12.sp,
////                    color = Color.White,
////                    fontWeight = FontWeight.Medium
////                )
////            }
////
////            Row(
////                modifier = Modifier.fillMaxWidth(),
////                horizontalArrangement = Arrangement.SpaceBetween,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Column {
////                    Text(
////                        text = "Stock Id: ${stock.stockId}",
////                        fontSize = 13.sp,
////                        fontWeight = FontWeight.Medium,
////                        color = colorResource(id = R.color.gray01)
////                    )
////                    Text(
////                        text = "Product Code: ${stock.productCode}",
////                        fontSize = 13.sp,
////                        fontWeight = FontWeight.Medium,
////                        color = colorResource(id = R.color.gray01)
////                    )
////                    Spacer(modifier = Modifier.height(4.dp))
////                    Text(
////                        text = stock.productName,
////                        fontSize = 18.sp,
////                        fontWeight = FontWeight.SemiBold,
////                        color = colorResource(id = R.color.coral)
////                    )
////                }
////            }
////
////            Spacer(modifier = Modifier.height(12.dp))
////
////            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
////                Column {
////                    Text("Buy Price", fontSize = 12.sp, color = Color.Gray)
////                    Text(
////                        text = stock.buyPrice.toString(),
////                        fontSize = 16.sp,
////                        fontWeight = FontWeight.Bold,
////                        color = colorResource(id = R.color.bleu_de_france)
////                    )
////                }
////                Column {
////                    Text("Sell Price", fontSize = 12.sp, color = Color.Gray)
////                    Text(
////                        text = stock.sellPrice.toString(),
////                        fontSize = 16.sp,
////                        fontWeight = FontWeight.Bold,
////                        color = colorResource(id = R.color.coral)
////                    )
////                }
////            }
////
////            Spacer(modifier = Modifier.height(12.dp))
////
////            Row(
////                modifier = Modifier.fillMaxWidth(),
////                horizontalArrangement = Arrangement.SpaceBetween
////            ) {
////                Column {
////                    Text("Category", fontSize = 12.sp, color = Color.Gray)
////                    Text(
////                        text = stock.category,
////                        fontSize = 14.sp,
////                        color = colorResource(id = R.color.coral)
////                    )
////                }
////                Column {
////                    Text("Quantity", fontSize = 12.sp, color = Color.Gray)
////                    Text(
////                        text = stock.quantity.toString(),
////                        fontSize = 14.sp,
////                        color = colorResource(id = R.color.yellow_green)
////                    )
////                }
////                Column {
////                    Text("Expected Profit", fontSize = 12.sp, color = Color.Gray)
////                    Text(
////                        text = expectedProfit.toString(),
////                        fontSize = 14.sp,
////                        color = colorResource(id = R.color.avocado)
////                    )
////                }
////            }
////            Spacer(modifier = Modifier.height(12.dp))
////            if(stock.notes?.isEmpty() ?: ){
////                Text(
////                    text = "Notes: ${stock.stockId}",
////                    fontSize = 13.sp,
////                    fontWeight = FontWeight.Medium,
////                    color = colorResource(id = R.color.gray01)
////                )
////            }
////
////        }
////    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 4.dp, vertical = 2.dp)
//            .clickable {
//                selectedStockId = stock.stockId
//                selectedISupplierId =stock.supplierId
//                selectedIDate =stock.date
//                selectedIStatus=stock.status
//                showActionDialog=true
//            },
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//
//            // ✅ Status color based on type
//            val statusColor = when (stock.status.lowercase()) {
//                "inward" -> colorResource(id = R.color.yellow_green)
//                "return" -> colorResource(id = R.color.coral)
//                else -> colorResource(id = R.color.gray01)
//            }
//
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(20.dp))
//                    .background(statusColor)
//                    .padding(horizontal = 10.dp, vertical = 4.dp)
//                    .align(Alignment.End)
//            ) {
//                Text(
//                    text = "Status: ${stock.status}",
//                    fontSize = 12.sp,
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column {
//                    Text(
//                        text = "Stock Id: ${stock.stockId}",
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.gray01)
//                    )
//                    Text(
//                        text = "Product Code: ${stock.productCode}",
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.gray01)
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = stock.productName,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = colorResource(id = R.color.coral)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                Column {
//                    Text("Buy Price", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = stock.buyPrice.toString(),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(id = R.color.bleu_de_france)
//                    )
//                }
//                Column {
//                    Text("Sell Price", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = stock.sellPrice.toString(),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(id = R.color.coral)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Column {
//                    Text("Category", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = stock.category,
//                        fontSize = 14.sp,
//                        color = colorResource(id = R.color.coral)
//                    )
//                }
//                Column {
//                    Text("Quantity", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = stock.quantity.toString(),
//                        fontSize = 14.sp,
//                        color = colorResource(id = R.color.yellow_green)
//                    )
//                }
//                Column {
//                    Text("Expected Profit", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = expectedProfit.toString(),
//                        fontSize = 14.sp,
//                        color = colorResource(id = R.color.avocado)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // ✅ Show notes only if not null or empty
//            if (!stock.notes.isNullOrEmpty()) {
//                Text(
//                    text = "Notes: ${stock.notes}",
//                    fontSize = 13.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = colorResource(id = R.color.gray01)
//                )
//            }
//        }
//    }
//
//
//
//    if (showActionDialog) {
//        InvActionPop(
//            navController,
//            onDismiss = {  showActionDialog = false },
//            stockId = selectedStockId.toString(),
//            supplierId = selectedISupplierId.toString(),
//            date= selectedIDate.toString(),
//            status = selectedIStatus.toString()
//        )
//
//    }
//
//}
//
//
//@Composable
//fun PaginationControls(
//    totalPages: Int,
//    currentPage: Int,
//    onPageChange: (Int) -> Unit
//) {
//    val activeColor = colorResource(id = R.color.bleu_de_france)     // selected page
//    val inactiveColor = colorResource(id = R.color.gray01)           // unselected page
//    val buttonColor = colorResource(id = R.color.coral)              // prev/next text
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        // 🔹 Previous Button
//        TextButton(
//            onClick = { if (currentPage > 1) onPageChange(currentPage - 1) },
//            enabled = currentPage > 1
//        ) {
//            Text(
//                text = "Prev",
//                color = if (currentPage > 1) buttonColor else inactiveColor
//            )
//        }
//
//        // 🔹 Pagination numbers with ellipsis (...)
//        val pagesToShow = when {
//            totalPages <= 5 -> (1..totalPages).toList()
//            currentPage <= 3 -> listOf(1, 2, 3, 4, -1, totalPages)
//            currentPage >= totalPages - 2 -> listOf(1, -1, totalPages - 3, totalPages - 2, totalPages - 1, totalPages)
//            else -> listOf(1, -1, currentPage - 1, currentPage, currentPage + 1, -1, totalPages)
//        }
//
//        pagesToShow.forEach { page ->
//            when (page) {
//                -1 -> Text(
//                    text = "...",
//                    color = inactiveColor,
//                    modifier = Modifier.padding(horizontal = 4.dp)
//                )
//                else -> TextButton(
//                    onClick = { onPageChange(page) },
//                    enabled = page != currentPage
//                ) {
//                    Text(
//                        text = page.toString(),
//                        color = if (page == currentPage) activeColor else inactiveColor,
//                        fontWeight = if (page == currentPage) FontWeight.Bold else FontWeight.Normal
//                    )
//                }
//            }
//        }
//
//        // 🔹 Next Button
//        TextButton(
//            onClick = { if (currentPage < totalPages) onPageChange(currentPage + 1) },
//            enabled = currentPage < totalPages
//        ) {
//            Text(
//                text = "Next",
//                color = if (currentPage < totalPages) buttonColor else inactiveColor
//            )
//        }
//    }
//}
//
//
//
