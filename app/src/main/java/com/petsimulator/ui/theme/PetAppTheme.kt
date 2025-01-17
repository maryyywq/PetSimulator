package com.petsimulator.ui.theme

import androidx.compose.ui.graphics.Color

data class PetAppTheme(
    val backgroundColor : Color,
    val topBarColor : Color,
    val bottomBarColor : Color,
    val buttonBackgroundColor : Color,
    val textColor : Color,
    val textInputColor : Color
)

fun getAppTheme(isNight : Boolean) : PetAppTheme {
    if (isNight) {
        return PetAppTheme(
            backgroundColor =  Color(0xFF7677C0),
            topBarColor = Color(0xFF3949AB),
            bottomBarColor = Color(0xFF3F51B5),
            buttonBackgroundColor = Color(0xFF5C6BC0),
            textColor = Color(0xFFACB2C4),
            textInputColor = Color(0xFF8B99E8)
        )
    }
    else {
        return PetAppTheme(
            backgroundColor = Color(0xFFFCEEC0),
            topBarColor = Color(0xFFF9D276),
            bottomBarColor = Color(0xFFE8C14D),
            buttonBackgroundColor = Color(0xFFEAC490),
            textColor = Color(0xFF4E342E),
            textInputColor = Color(0xFFD9C1A3)
        )
    }
}