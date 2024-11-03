package kv.apps.mywarehouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import kv.apps.mywarehouse.ui.theme.MyLocalWarehouseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLocalWarehouseTheme {
                val navController = rememberNavController()
                MyApp(navController = navController)
            }
        }
    }
}
