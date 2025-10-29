package com.example.evaluationp1.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evaluationp1.core.ui.theme.Pink40
import com.example.evaluationp1.core.ui.theme.Pink80
import com.example.evaluationp1.core.ui.theme.PurpleGrey80

/***
 * Classe contenant les composantes de la barre de navigation
 */
sealed class NavigationBarItem(val route: String, val label: String, val icon: ImageVector) {
    object Agents : NavigationBarItem("agents", "Agents", Icons.Default.Person)
    object Maps : NavigationBarItem("maps", "Maps", Icons.Default.Place)
}

/***
 * Composant de la barre de navigation, appelée dans les screens
 */
@Composable
fun NavigationBar(navController: NavController, currentRoute: String?) {
    val items = listOf(
        NavigationBarItem.Agents,
        NavigationBarItem.Maps
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Pink40, Pink80)
                )
            )
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = currentRoute == item.route

                if (index == 2) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .offset(y = (-8).dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .clickable {
                                    if (currentRoute != item.route) {
                                        navController.navigate(item.route) {
                                            launchSingleTop = true
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            restoreState = true
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = PurpleGrey80,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                                        restoreState = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        NavigationItem(
                            label = item.label,
                            icon = item.icon,
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Color.White
    val unselectedColor = Pink80

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) selectedColor else unselectedColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            color = if (selected) selectedColor else unselectedColor,
            fontSize = 11.sp
        )
    }
}
