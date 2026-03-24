package uk.ac.tees.mad.caloriedish.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF7A00),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFFFFD6A5),
    onSecondary = Color(0xFF2B2B2B),

    tertiary = Color(0xFF4CAF50),
    onTertiary = Color(0xFFFFFFFF),

    background = Color(0xFFFFF8F2),
    surface = Color(0xFFFFFFFF),

    onBackground = Color(0xFF1A1A1A),
    onSurface = Color(0xFF1A1A1A)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF8C32),
    onPrimary = Color(0xFF000000),

    secondary = Color(0xFFE6A96B),
    onSecondary = Color(0xFF000000),

    tertiary = Color(0xFF66BB6A),
    onTertiary = Color(0xFF000000),

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),

    onBackground = Color(0xFFEDEDED),
    onSurface = Color(0xFFEDEDED)
)


@Composable
fun CalorieDishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content ,
        shapes = Shapes
    )
}