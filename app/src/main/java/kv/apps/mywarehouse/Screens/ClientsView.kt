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
import kv.apps.mywarehouse.ClientsDB.Client
import kv.apps.mywarehouse.R
import kv.apps.mywarehouse.ViewModels.ClientViewModel

@Composable
fun ClientView(
    viewModel: ClientViewModel,
    onBackNavClicked: () -> Unit,
    navController: NavHostController
) {
    val clients by viewModel.allClients.observeAsState(emptyList())

    var isEditing by remember { mutableStateOf(false) }
    var clientToEditIndex by remember { mutableStateOf(-1) }
    var newClientName by remember { mutableStateOf("") }
    var newClientEmail by remember { mutableStateOf("") }
    var newClientPhone by remember { mutableStateOf("") }
    var isAdding by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBarView(
                title = "Clients",
                onBackNavClicked = onBackNavClicked
            )
        },
        containerColor = colorResource(id = R.color.background),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAdding = true
                    newClientName = ""
                    newClientEmail = ""
                    newClientPhone = ""
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
            items(clients) { client ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text = "Client Name:", fontSize = 16.sp)
                        Text(text = client.name, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(text = "Email:", fontSize = 16.sp)
                        Text(text = client.email, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(text = "Phone:", fontSize = 16.sp)
                        Text(text = client.phoneNumber, fontSize = 20.sp)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                isEditing = true
                                clientToEditIndex = clients.indexOf(client)
                                newClientName = client.name
                                newClientEmail = client.email
                                newClientPhone = client.phoneNumber
                            }) {
                                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit", tint = Color(0xFF323133))
                            }
                            IconButton(onClick = { viewModel.delete(client) }) {
                                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete", tint = Color(0xFF323133))
                            }
                        }
                    }
                }
            }

            if (clients.isEmpty()) {
                item {
                    Text(
                        text = "No clients available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    if (isAdding) {
        AlertDialog(
            onDismissRequest = { isAdding = false },
            title = { Text("Add Client") },
            text = {
                Column {
                    TextField(value = newClientName, onValueChange = { newClientName = it.trim() }, label = { Text("Client Name") })
                    TextField(value = newClientEmail, onValueChange = { newClientEmail = it.trim() }, label = { Text("Email") })
                    TextField(value = newClientPhone, onValueChange = { newClientPhone = it.trim() }, label = { Text("Phone") })
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newClientName.isNotBlank()) {
                            viewModel.insert(Client(name = newClientName, email = newClientEmail, phoneNumber = newClientPhone))
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
            title = { Text("Edit Client") },
            text = {
                Column {
                    TextField(value = newClientName, onValueChange = { newClientName = it.trim() }, label = { Text("Client Name") })
                    TextField(value = newClientEmail, onValueChange = { newClientEmail = it.trim() }, label = { Text("Email") })
                    TextField(value = newClientPhone, onValueChange = { newClientPhone = it.trim() }, label = { Text("Phone") })
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (clientToEditIndex in clients.indices && newClientName.isNotBlank()) {
                            viewModel.update(Client(id = clients[clientToEditIndex].id, name = newClientName, email = newClientEmail, phoneNumber = newClientPhone))
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
