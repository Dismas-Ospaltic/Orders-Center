package com.ord.orderscenter.screens_ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.ord.orderscenter.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ord.orderscenter.utils.StatusBarColor
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayOrdersScreen(navController: NavController, dateId:String) {

    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)

    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
    val genOrderToday by generalOrderViewModel.genOrderToday.collectAsState(initial = emptyList())



    LaunchedEffect(dateId) {
        generalOrderViewModel.getAllGenOrdersToday(dateId)
    }

//    val allOrders = remember { mockOrders() }
    val allOrders = genOrderToday

    var searchQuery by remember { mutableStateOf("") }
    var currentPage by remember { mutableStateOf(1) }

    val itemsPerPage = 4


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
                title = {
                    //Text("Today's Orders", color = Color.White)

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Daily Sales",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = dateId,
                            color = colorResource(id=R.color.white),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                        },
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
                            tint = colorResource(id=R.color.space_indigo),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Gray),
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


                    }
                }


                Spacer(modifier = Modifier.height(16.dp))


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


