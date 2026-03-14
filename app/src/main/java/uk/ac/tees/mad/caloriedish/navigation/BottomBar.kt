package uk.ac.tees.mad.caloriedish.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
            .height(Dimens.bottomBarHeight)
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
            )
        }
    }
}