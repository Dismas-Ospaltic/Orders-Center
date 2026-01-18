package com.ord.orderscenter.screens_ui





import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import com.ord.orderscenter.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ord.orderscenter.navgraph.Screen
import com.ord.orderscenter.screens_ui.screen_components.ActionButtonsBox
import com.ord.orderscenter.screens_ui.screen_components.StatsBox
import com.ord.orderscenter.utils.StatusBarColor
import com.ord.orderscenter.utils.standardizedDateFormat
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ClipboardList
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)
//
//    val generalOrderViewModel: GeneralOrderViewModel = koinViewModel()
//    val individualOrderViewModel: IndividualOrderViewModel = koinViewModel()
//    val genOrder by generalOrderViewModel.genOrder.collectAsState(initial = emptyList())
//      val todayDate: String = standardizedDateFormat(System.currentTimeMillis())


//    val allOrderCount by generalOrderViewModel.allOrderCount.collectAsState()
//val totalUnpaidOrderCount by generalOrderViewModel.totalUnpaidOrderCount.collectAsState()
//     val totalPaidOrderCount by generalOrderViewModel.totalPaidOrderCount.collectAsState()
//    val totalAmountAllOrder by generalOrderViewModel.totalAmountAllOrder.collectAsState()
//    val totalAmountAllOrderToday by generalOrderViewModel.totalAmountAllOrderToday.collectAsState()
//
//
//    LaunchedEffect(Unit) {
//        generalOrderViewModel.getAllUnpaidOrderCount(todayDate)
//        generalOrderViewModel.getAllPaidOrderCount(todayDate)
//        generalOrderViewModel.getTodayTotalOrders(todayDate)
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                        Text("Home", color = Color.White)

                },
                actions = {

                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id=R.color.space_indigo))
                    ) {
//                        Text("Today's Orders", color = Color.White)


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ClipboardList,
                                contentDescription = "Today's Orders",
                                tint = colorResource(id=R.color.white),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Today's Orders",
                                color = colorResource(id=R.color.white),
                                fontSize = 16.sp
                            )
                        }
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor, // dark green
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
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
                Spacer(modifier = Modifier.height(16.dp))
                StatsBox()

                Spacer(modifier = Modifier.height(16.dp))

                ActionButtonsBox(
                    onAddOrder = {
                    navController.navigate(Screen.AddOrder.route)
                    /* navigate */ },
                    onAllOrders = { /* navigate */
                        navController.navigate(Screen.AllOrders.route)
                    },
                    onViewPaid = { /* navigate */ },
                    onViewUnpaid = { /* navigate */ },
                    onSettings = { navController.navigate(Screen.Settings.route) }
                )


            }

        }
    }




}






@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}

@Composable
fun StatItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}



///buttons for navigations
//@Composable
//fun ActionOutlinedButton(
//    text: String,
//    icon: ImageVector,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit
//) {
//    OutlinedButton(
//        onClick = onClick,
//        modifier = modifier.height(100.dp),
//        colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = Color.Transparent
//        )
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = text,
//                modifier = Modifier.size(28.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = text,
//                fontSize = 12.sp
//            )
//        }
//    }
//}

//@Composable
//fun ActionOutlinedButton(
//    text: String,
//    icon: ImageVector,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit
//) {
//
//    val borderColor = colorResource(id = R.color.punch_red)
//    val contentColor = colorResource(id = R.color.space_indigo)
//
//    OutlinedButton(
//        onClick = onClick,
//        modifier = modifier.height(100.dp),
//        colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = Color.Transparent,
//            contentColor = contentColor
//        ),
//        border = BorderStroke(1.dp, borderColor)
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = text,
//                tint = contentColor,
//                modifier = Modifier.size(28.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = text,
//                fontSize = 12.sp,
//                color = contentColor
//            )
//        }
//    }
//}

