package kv.apps.mywarehouse

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            onBackNavClicked?.let {
                IconButton(onClick = it) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.topappbar),
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
