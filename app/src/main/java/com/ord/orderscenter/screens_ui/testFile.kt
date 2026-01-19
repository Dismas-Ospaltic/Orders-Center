//import android.content.Intent
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.net.toUri
//import com.ord.orderscenter.R
//
//@Composable
//fun AboutDeveloperCard() {
//    val context = LocalContext.current
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(12.dp)),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        border = BorderStroke(1.dp, colorResource(id = R.color.punch_red)),
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//
//            Text(
//                text = "About Developer",
//                fontWeight = FontWeight.SemiBold,
//                color = colorResource(id = R.color.punch_red),
//                fontSize = 16.sp
//            )
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Text(
//                text = "Developed by Taj Apps.\nFor support or feedback, contact us below.",
//                fontSize = 14.sp,
//                lineHeight = 20.sp,
//                color = Color.Gray
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    val intent = Intent(Intent.ACTION_SENDTO).apply {
//                        data = "mailto:ict@tajmillenium.com".toUri()
//                        putExtra(Intent.EXTRA_SUBJECT, "Orders Center App Support")
//                    }
//                    context.startActivity(
//                        Intent.createChooser(intent, "Contact Support")
//                    )
//                },
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = colorResource(id = R.color.punch_red)
//                ),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Contact Support",
//                    color = Color.White,
//                    fontSize = 14.sp
//                )
//            }
//        }
//    }
//}
