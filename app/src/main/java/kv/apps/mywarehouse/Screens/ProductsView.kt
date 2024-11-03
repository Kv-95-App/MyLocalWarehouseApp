package kv.apps.mywarehouse.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kv.apps.mywarehouse.AppBarView
import kv.apps.mywarehouse.R
import kv.apps.mywarehouse.ViewModels.ProductViewModel
import kv.apps.mywarehouse.productsDB.Product

@Composable
fun ProductsView(
    navController: NavHostController,
    viewModel: ProductViewModel,
    onBackNavClicked: () -> Unit
) {
    val products by viewModel.allProducts.observeAsState(emptyList())
    var isEditing by remember { mutableStateOf(false) }
    var productToEditIndex by remember { mutableStateOf(-1) }
    var newProductName by remember { mutableStateOf("") }
    var newProductPrice by remember { mutableStateOf(0.0) }
    var newProductQuantity by remember { mutableStateOf(0) }
    var isAdding by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBarView(
                title = "Products",
                onBackNavClicked = onBackNavClicked
            )
        },
        containerColor = colorResource(id = R.color.background),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAdding = true
                    newProductName = ""
                    newProductPrice = 0.00
                    newProductQuantity = 0
                },
                containerColor = Color.Gray,
                contentColor = Color.White
            ) {
                Text(text = "+", fontSize = 32.sp, textAlign = TextAlign.Center)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Product Name:", fontSize = 16.sp)
                        Text(text = product.name, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "Price:", fontSize = 16.sp)
                        Text(text = "\$${product.price}", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "Quantity:", fontSize = 16.sp)
                        Text(text = "${product.quantity}", fontSize = 20.sp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                isEditing = true
                                productToEditIndex = products.indexOf(product)
                                newProductName = product.name
                                newProductPrice = product.price
                                newProductQuantity = product.quantity
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Edit",
                                    tint = Color(0xFF323133)
                                )
                            }
                            IconButton(onClick = { viewModel.delete(product) }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = Color(0xFF323133)
                                )
                            }
                        }
                    }
                }
            }
            if (products.isEmpty()) {
                item {
                    Text(
                        text = "No products available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        if (isAdding) {
            AlertDialog(
                onDismissRequest = { isAdding = false },
                title = { Text("Add Product") },
                text = {
                    Column {
                        Text(text = "Product Name", fontSize = 16.sp)
                        TextField(
                            value = newProductName,
                            onValueChange = { newProductName = it.trim() },
                            label = { Text("") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Price", fontSize = 16.sp)
                        TextField(
                            value = newProductPrice.toString(),
                            onValueChange = { newProductPrice = it.toDoubleOrNull() ?: 0.0 },
                            label = { Text("") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Quantity", fontSize = 16.sp)
                        TextField(
                            value = newProductQuantity.toString(),
                            onValueChange = { newProductQuantity = it.toIntOrNull() ?: 0 },
                            label = { Text("") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newProductName.isNotBlank()) {
                                viewModel.insert(
                                    Product(
                                        name = newProductName,
                                        price = newProductPrice,
                                        quantity = newProductQuantity
                                    )
                                )
                                isAdding = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Add", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { isAdding = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            )
        }
        if (isEditing) {
            AlertDialog(
                onDismissRequest = { isEditing = false },
                title = { Text("Edit Product") },
                text = {
                    Column {
                        Text(text = "Product Name", fontSize = 16.sp)
                        TextField(
                            value = newProductName,
                            onValueChange = { newProductName = it.trim() },
                            label = { Text("") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Price", fontSize = 16.sp)
                        TextField(
                            value = newProductPrice.toString(),
                            onValueChange = { newProductPrice = it.toDoubleOrNull() ?: 0.0 },
                            label = { Text("") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Quantity", fontSize = 16.sp)
                        TextField(
                            value = newProductQuantity.toString(),
                            onValueChange = { newProductQuantity = it.toIntOrNull() ?: 0 },
                            label = { Text("") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (productToEditIndex in products.indices && newProductName.isNotBlank()) {
                                viewModel.update(
                                    Product(
                                        id = products[productToEditIndex].id,
                                        name = newProductName,
                                        price = newProductPrice,
                                        quantity = newProductQuantity
                                    )
                                )
                            }
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Save", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { isEditing = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            )
        }
    }
}
