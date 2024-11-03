package kv.apps.mywarehouse

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kv.apps.mywarehouse.ClientsDB.ClientRepository
import kv.apps.mywarehouse.Screens.ClientView
import kv.apps.mywarehouse.Screens.HomePage
import kv.apps.mywarehouse.Screens.ProductsView
import kv.apps.mywarehouse.ViewModels.ClientViewModel
import kv.apps.mywarehouse.ViewModels.ProductViewModel
import kv.apps.mywarehouse.productsDB.AppDatabase
import kv.apps.mywarehouse.productsDB.ProductRepository

@Composable
fun MyApp(navController: NavHostController) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)

    val productRepository = ProductRepository(database.productDao())
    val clientRepository = ClientRepository(database.clientDao())

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }

        composable("products") { backStackEntry ->
            val productsViewModel: ProductViewModel = viewModel { ProductViewModel(productRepository) }

            ProductsView(
                navController = navController,
                onBackNavClicked = { navController.popBackStack() },
                viewModel = productsViewModel
            )
        }

        composable("clients") {
            val clientsViewModel: ClientViewModel = viewModel { ClientViewModel(clientRepository) }
            ClientView(
                navController = navController,
                viewModel = clientsViewModel,
                onBackNavClicked = { navController.popBackStack() }
            )
        }
    }
}
