package org.example.sbapp.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeLayout(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar() },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { /* ... */ },
                modifier = Modifier.size(100.dp).offset(y = 90.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
            ) {
                LargeFloatingActionButton(
                    onClick = { /* ... */ },
                    modifier = Modifier.padding(10.dp).border(
                        border = BorderStroke(5.dp, MaterialTheme.colorScheme.onPrimary), // Border color
                        shape = CircleShape,
                    ),
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                ) {
                    Icon(Icons.Default.QrCode, contentDescription = null, modifier = Modifier.size(40.dp))
                }
            }
        },
        // This tells the Scaffold to dock the FAB right in the middle
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}