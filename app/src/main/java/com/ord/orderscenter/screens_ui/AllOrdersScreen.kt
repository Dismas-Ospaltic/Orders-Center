package com.ord.orderscenter.screens_ui



import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import com.ord.orderscenter.model.GeneralOrdersEntity
import com.ord.orderscenter.navgraph.Screen
import com.ord.orderscenter.screens_ui.screen_components.ActionPopup
import com.ord.orderscenter.utils.StatusBarColor
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllOrdersScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)

    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())



//    val allOrders = remember { mockOrders() }
    val allOrders = genOrder

    var searchQuery by remember { mutableStateOf("") }
    var currentPage by remember { mutableStateOf(1) }

    val itemsPerPage = 4

    // 🔍 Filter logic
//    val filteredOrders = remember(searchQuery) {
//        allOrders.filter {
//            it.customerName.contains(searchQuery, ignoreCase = true) ||
//                    it.phone.contains(searchQuery) ||
//                    it.id.contains(searchQuery)
//        }
//    }

    val filteredOrders = remember(searchQuery, allOrders) {
        allOrders.filter {
            it.customerName.contains(searchQuery, ignoreCase = true) ||
                    it.phone.contains(searchQuery) ||
                    it.orderNumber.contains(searchQuery, ignoreCase = true)
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
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        }
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
//            paginatedOrders.forEach { order ->
//                OrderCard(order)
//            }


                when {
                    allOrders.isEmpty() -> {
                        // No data in database
                        EmptyState("No data available")
                    }

                    filteredOrders.isEmpty() -> {
                        // Search returned nothing
                        EmptyState("No results found")
                    }

                    else -> {
                        paginatedOrders.forEach { order ->
                            OrderCard(order,navController)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        PaginationBar(
                            currentPage = currentPage,
                            totalPages = totalPages,
                            onPageChange = { currentPage = it }
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

            // 🔢 PAGINATION
//            PaginationBar(
//                currentPage = currentPage,
//                totalPages = totalPages,
//                onPageChange = { currentPage = it }
//            )

                if (filteredOrders.isNotEmpty() && filteredOrders.size > itemsPerPage) {
                    PaginationBar(
                        currentPage = currentPage,
                        totalPages = totalPages,
                        onPageChange = { currentPage = it }
                    )
                }

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
            .padding(vertical = 12.dp)
            .horizontalScroll(rememberScrollState())
            .background(
                color = Color(0xFFF4F4F4),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ◀ Previous
        PaginationAction(
            text = "‹",
            enabled = currentPage > 1
        ) { onPageChange(currentPage - 1) }

        Spacer(modifier = Modifier.width(8.dp))

        val visiblePages = buildList {
            add(1)

            if (currentPage > 4) add(-1) // ellipsis

            for (page in (currentPage - 2)..(currentPage + 2)) {
                if (page in 2 until totalPages) add(page)
            }

            if (currentPage < totalPages - 3) add(-1)

            if (totalPages > 1) add(totalPages)
        }.distinct()

        visiblePages.forEach { page ->
            when (page) {
                -1 -> {
                    Text(
                        text = "…",
                        modifier = Modifier.padding(horizontal = 6.dp),
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
                else -> {
                    PageButton(
                        page = page,
                        isActive = page == currentPage,
                        onClick = { onPageChange(page) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // ▶ Next
        PaginationAction(
            text = "›",
            enabled = currentPage < totalPages
        ) { onPageChange(currentPage + 1) }
    }
}

@Composable
private fun PageButton(
    page: Int,
    isActive: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .background(
                if (isActive) colorResource(id = R.color.punch_red)
                else Color.Transparent,
                RoundedCornerShape(50)
            ),
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (isActive) Color.White else Color.Black
        )
    ) {
        Text(
            text = page.toString(),
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}


@Composable
private fun PaginationAction(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



//@Composable
//fun OrderCard(order: OrderItem) {
//
//    val statusColor = when (order.status.lowercase()) {
//        "paid" -> Color(0xFF4CAF50)
//        "unpaid" -> Color(0xFFE53935)
//        else -> Color.Gray
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 6.dp),
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(6.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(order.id, fontWeight = FontWeight.Bold)
//                Box(
//                    modifier = Modifier
//                        .background(statusColor, RoundedCornerShape(20.dp))
//                        .padding(horizontal = 12.dp, vertical = 4.dp)
//                ) {
//                    Text(order.status, color = Color.White, fontSize = 12.sp)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Text(order.customerName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
//            Text(order.phone, fontSize = 13.sp, color = Color.Gray)
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
//                Column {
//                    Text("Total", fontSize = 12.sp, color = Color.Gray)
//                    Text("KES ${order.total}", fontWeight = FontWeight.Bold)
//                }
//                Column(horizontalAlignment = Alignment.End) {
//                    Text("Date", fontSize = 12.sp, color = Color.Gray)
//                    Text(order.date, fontWeight = FontWeight.Medium)
//                }
//            }
//        }
//    }
//}


@Composable
fun OrderCard(order: GeneralOrdersEntity, navController: NavController) {


    var selectedOrderNumber by remember { mutableStateOf<String?>(null) }
    var showActionPopUp by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf<String?>(null) }
    val statusColor = when (order.status.lowercase()) {
        "paid" -> Color(0xFF4CAF50)
        "unpaid" -> Color(0xFFE53935)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                selectedOrderNumber = order.orderNumber.toString()
                showActionPopUp=true
                selectedStatus = order.status
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(order.orderNumber, fontWeight = FontWeight.Bold)

                Box(
                    modifier = Modifier
                        .background(statusColor, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        order.status.uppercase(),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(order.customerName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(order.phone, fontSize = 13.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Total", fontSize = 12.sp, color = Color.Gray)
                    Text("${order.totalOrder}", fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("Date", fontSize = 12.sp, color = Color.Gray)
                    Text(order.date, fontWeight = FontWeight.Medium)
                }
            }
        }
    }


    if (showActionPopUp) {
        ActionPopup(
            navController,
            onDismiss = {  showActionPopUp = false },
            orderNumber = selectedOrderNumber.toString(),
            status = selectedStatus.toString()
        )

    }
}





//data class OrderItem(
//    val id: String,
//    val date: String,
//    val customerName: String,
//    val phone: String,
//    val status: String,
//    val total: Int
//)

//fun mockOrders(): List<OrderItem> =
//    List(100) {
//        OrderItem(
//            id = "ORD-${1000 + it}",
//            date = "2026-01-${10 + it}",
//            customerName = "Customer ${it + 1}",
//            phone = "07${(10000000..99999999).random()}",
//            status = if (it % 2 == 0) "Paid" else "Unpaid",
//            total = (1000..15000).random()
//        )
//    }

@Composable
fun EmptyState(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}







