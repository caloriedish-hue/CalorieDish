package uk.ac.tees.mad.caloriedish.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uk.ac.tees.mad.caloriedish.presentation.theme.Dimens

@Composable
fun BottomBar(
    navController: NavController,
    currentRoute: String?,
    modifier: Modifier
){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Recipes,
        BottomNavItem.Settings
    )

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.bottomBarHeight) ,
        containerColor = MaterialTheme.colorScheme.background ,
        contentColor = MaterialTheme.colorScheme.onBackground
    ){
        items.forEach {item ->
            NavigationBarItem(
                selected = item.route.route == currentRoute ,
                onClick = {
                    navController.navigate(item.route.route) {
                        popUpTo(item.route.route)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon ,
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(
                        text = item.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),

                    unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )

            )
        }
    }
}