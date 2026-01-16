package com.ord.orderscenter.screens_ui



import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import com.ord.orderscenter.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import compose.icons.fontawesomeicons.solid.Cogs
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.ShareAlt
import org.koin.androidx.compose.koinViewModel

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AllOrdersScreen(navController: NavController) {
//    val backgroundColor = colorResource(id = R.color.punch_red)
//    StatusBarColor(backgroundColor)
//    val searchQuery = remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("All Orders", color = Color.White) },
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
//
//
//
//
//
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
//fun AllOrdersScreenPreview() {
//    AllOrdersScreen(navController = rememberNavController())
//}
//
//
//
//data class OrderItem(
//    val date: String,
//    val customerName: String,
//    val phone: String,
//    val status: String,
//    val total: Int,
//)
//
//@Composable
//fun OrderCard( navController: NavController) {
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 4.dp, vertical = 2.dp)
//            .clickable {
//
//            },
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//
//            // ✅ Status color based on type
//            val statusColor = when (OrderItem.status.lowercase()) {
//                "paid" -> colorResource(id = R.color.space_indigo)
//                "unpaid" -> colorResource(id = R.color.punch_red)
//                else -> colorResource(id = R.color.lavender_grey)
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
//                    text = "Status: ${OrderItem.status}",
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
//                        text = "order Id: ${OrderItem.stockId}",
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.gray01)
//                    )
//                    Text(
//                        text = "customer contact: ${OrderItem.phone}",
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = colorResource(id = R.color.gray01)
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = OrderItem.customerName,
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
//                    Text("Total", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = OrderItem.total.toString(),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(id = R.color.bleu_de_france)
//                    )
//                }
//                Column {
//                    Text("payment date", fontSize = 12.sp, color = Color.Gray)
//                    Text(
//                        text = OrderItem.date.toString(),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(id = R.color.coral)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//        }
//    }
//
//
//
//}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllOrdersScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)

    val allOrders = remember { mockOrders() }

    var searchQuery by remember { mutableStateOf("") }
    var currentPage by remember { mutableStateOf(1) }

    val itemsPerPage = 4

    // 🔍 Filter logic
    val filteredOrders = remember(searchQuery) {
        allOrders.filter {
            it.customerName.contains(searchQuery, ignoreCase = true) ||
                    it.phone.contains(searchQuery) ||
                    it.id.contains(searchQuery)
        }
    }

    val totalPages = maxOf(
        1,
        (filteredOrders.size + itemsPerPage - 1) / itemsPerPage
    )

    // Reset page when searching
    LaunchedEffect(searchQuery) {
        currentPage = 1
    }

    val paginatedOrders = filteredOrders
        .drop((currentPage - 1) * itemsPerPage)
        .take(itemsPerPage)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("All Orders", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.AngleLeft,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        }
    ) { paddingValues ->

//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(horizontal = 12.dp)
//        ) {
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

//            // 🔍 SEARCH FIELD
//            TextField(
//                value = searchQuery,
//                onValueChange = { searchQuery = it },
//                placeholder = { Text("Search orders...") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Search,
//                        contentDescription = null,
//                        tint = Color.Gray
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(Color.White),
//                singleLine = true,
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.Transparent,
//                    unfocusedContainerColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent
//                )
//            )

                // Search Field
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(text = "Search...") },
                    leadingIcon = {

                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )

            Spacer(modifier = Modifier.height(12.dp))

            // 🧱 ORDER CARDS
            paginatedOrders.forEach { order ->
                OrderCard(order)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔢 PAGINATION
            PaginationBar(
                currentPage = currentPage,
                totalPages = totalPages,
                onPageChange = { currentPage = it }
            )
        }
    }
    }
}




@Composable
fun PaginationBar(
    currentPage: Int,
    totalPages: Int,
    onPageChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(30.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextButton(
            onClick = { if (currentPage > 1) onPageChange(currentPage - 1) },
            enabled = currentPage > 1
        ) { Text("Previous") }

        Spacer(modifier = Modifier.width(6.dp))

        for (page in 1..totalPages) {
            if (page <= 4 || page == totalPages) {
                TextButton(
                    onClick = { onPageChange(page) },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (page == currentPage) Color.White else Color.Black
                    ),
                    modifier = Modifier
                        .background(
                            if (page == currentPage) Color(0xFF3F51B5) else Color.Transparent,
                            RoundedCornerShape(50)
                        )
                ) {
                    Text(page.toString())
                }
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        TextButton(
            onClick = { if (currentPage < totalPages) onPageChange(currentPage + 1) },
            enabled = currentPage < totalPages
        ) { Text("Next") }
    }
}




@Composable
fun OrderCard(order: OrderItem) {

    val statusColor = when (order.status.lowercase()) {
        "paid" -> Color(0xFF4CAF50)
        "unpaid" -> Color(0xFFE53935)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(order.id, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .background(statusColor, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(order.status, color = Color.White, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(order.customerName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(order.phone, fontSize = 13.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Total", fontSize = 12.sp, color = Color.Gray)
                    Text("KES ${order.total}", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Date", fontSize = 12.sp, color = Color.Gray)
                    Text(order.date, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}




data class OrderItem(
    val id: String,
    val date: String,
    val customerName: String,
    val phone: String,
    val status: String,
    val total: Int
)

fun mockOrders(): List<OrderItem> =
    List(100) {
        OrderItem(
            id = "ORD-${1000 + it}",
            date = "2026-01-${10 + it}",
            customerName = "Customer ${it + 1}",
            phone = "07${(10000000..99999999).random()}",
            status = if (it % 2 == 0) "Paid" else "Unpaid",
            total = (1000..15000).random()
        )
    }



