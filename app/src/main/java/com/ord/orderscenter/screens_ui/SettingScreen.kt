package com.ord.orderscenter.screens_ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ord.orderscenter.navgraph.Screen
import com.ord.orderscenter.screens_ui.screen_components.IPPopUp
import com.ord.orderscenter.screens_ui.screen_components.OrderDetailPopup
import com.ord.orderscenter.utils.StatusBarColor
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Brain
import compose.icons.fontawesomeicons.solid.FileContract
import compose.icons.fontawesomeicons.solid.InfoCircle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.punch_red)
    StatusBarColor(backgroundColor)
    val context = LocalContext.current

    var showIPDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", color = Color.White) },
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
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    SettingsOutlinedItem(
                        icon = FontAwesomeIcons.Solid.InfoCircle,
                        title = "About the App",
                        onClick = {
                            navController.navigate(Screen.AboutApp.route)
                        }
                    )

                    SettingsOutlinedItem(
                        icon = FontAwesomeIcons.Solid.FileContract,
                        title = "Privacy Policy",
                        onClick = {
                            // Navigate to Privacy Policy screen
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                "https://tajmilleniumapps.github.io/orders-center-app/index.html".toUri()
                            )
                            context.startActivity(intent)
                        }
                    )

                    SettingsOutlinedItem(
                        icon = FontAwesomeIcons.Solid.Brain,
                        title = "Intellectual Property Notice",
                        onClick = {
                            showIPDialog = true
                        }
                    )
                }



            }

        }
    }

    if (showIPDialog) {
        IPPopUp(
            onDismiss = {  showIPDialog = false }
        )
    }




}






@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}




@Composable
fun SettingsOutlinedItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.lavender_grey)),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = colorResource(id = R.color.space_indigo),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.space_indigo)
            )
        }
    }
}


