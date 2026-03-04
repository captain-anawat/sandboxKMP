package org.example.sbapp.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "KMP"
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* Open Drawer */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle Logout */ }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Logout"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}