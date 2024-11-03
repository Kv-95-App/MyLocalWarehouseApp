package kv.apps.mywarehouse.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kv.apps.mywarehouse.AppBarView
import kv.apps.mywarehouse.R

@Composable
fun HomePage(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBarView(
                title = "My Local Warehouse",
                onBackNavClicked = null
            )
        },
        containerColor = colorResource(id = R.color.background)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.features),
                        contentDescription = "Products Icon",
                        modifier = Modifier
                            .size(122.dp)
                            .clickable { navController.navigate("products") }
                    )
                    Text(
                        text = "Products",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.customer),
                        contentDescription = "Clients Icon",
                        modifier = Modifier
                            .size(122.dp)
                            .clickable { navController.navigate("clients") }
                    )
                    Text(
                        text = "Clients",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
