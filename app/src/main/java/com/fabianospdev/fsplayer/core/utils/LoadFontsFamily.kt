package com.fabianospdev.fsplayer.core.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.fabianospdev.fsplayer.R


object LoadFontsFamily {

    val montserratFamily = loadFontFamily(
        R.font.montserrat_light to FontWeight.Light,
        R.font.montserrat_regular to FontWeight.Normal,
        R.font.montserrat_medium to FontWeight.Medium,
        R.font.montserrat_semibold to FontWeight.SemiBold
    )

    val domineFamily = loadFontFamily(
        R.font.domine_regular to FontWeight.Normal,
        R.font.domine_bold to FontWeight.Bold
    )

    val karlaFamily = loadFontFamily(
        R.font.karla_regular to FontWeight.Normal,
        R.font.karla_bold to FontWeight.Bold
    )

    val sourGummyFamily = loadFontFamily(
        R.font.sourgummy_black to FontWeight.Normal,
        R.font.sourgummy_blackitalic to FontWeight.Normal,
        R.font.sourgummy_bold to FontWeight.Bold,
        R.font.sourgummy_bolditalic to FontWeight.Bold,
        R.font.sourgummy_expanded_black to FontWeight.Black,
        R.font.sourgummy_expanded_blackitalic to FontWeight.Black,
        R.font.sourgummy_expanded_bold to FontWeight.Bold,
        R.font.sourgummy_expanded_bolditalic to FontWeight.Bold,
        R.font.sourgummy_expanded_extrabold to FontWeight.ExtraBold,
        R.font.sourgummy_expanded_extrabolditalic to FontWeight.ExtraBold,
        R.font.sourgummy_expanded_extralight to FontWeight.ExtraLight,
        R.font.sourgummy_expanded_extralightitalic to FontWeight.ExtraLight,
        R.font.sourgummy_expanded_italic to FontWeight.Normal,
        R.font.sourgummy_expanded_light to FontWeight.Light,
        R.font.sourgummy_expanded_lightitalic to FontWeight.Light,
        R.font.sourgummy_expanded_medium to FontWeight.Medium,
        R.font.sourgummy_expanded_mediumitalic to FontWeight.Medium,
        R.font.sourgummy_expanded_regular to FontWeight.Normal,
        R.font.sourgummy_expanded_semibold to FontWeight.SemiBold,
        R.font.sourgummy_expanded_semibolditalic to FontWeight.SemiBold,
        R.font.sourgummy_expanded_thin to FontWeight.Thin,
        R.font.sourgummy_expanded_thinitalic to FontWeight.Thin,
        R.font.sourgummy_extrabold to FontWeight.ExtraBold,
        R.font.sourgummy_extrabolditalic to FontWeight.ExtraBold,
        R.font.sourgummy_extralight to FontWeight.ExtraLight,
        R.font.sourgummy_extralightitalic to FontWeight.ExtraLight,
        R.font.sourgummy_italic to FontWeight.Normal,
        R.font.sourgummy_light to FontWeight.Light,
        R.font.sourgummy_lightitalic to FontWeight.Light,
        R.font.sourgummy_medium to FontWeight.Medium,
        R.font.sourgummy_mediumitalic to FontWeight.Medium,
        R.font.sourgummy_regular to FontWeight.Normal,
        R.font.sourgummy_semibold to FontWeight.SemiBold,
        R.font.sourgummy_semibolditalic to FontWeight.SemiBold,
        R.font.sourgummy_semiexpanded_black to FontWeight.Black,
        R.font.sourgummy_semiexpanded_blackitalic to FontWeight.Black,
        R.font.sourgummy_semiexpanded_bold to FontWeight.Bold,
        R.font.sourgummy_semiexpanded_bolditalic to FontWeight.Bold,
        R.font.sourgummy_semiexpanded_extrabold to FontWeight.ExtraBold,
        R.font.sourgummy_semiexpanded_extrabolditalic to FontWeight.ExtraBold,
        R.font.sourgummy_semiexpanded_extralight to FontWeight.ExtraLight,
        R.font.sourgummy_semiexpanded_extralightitalic to FontWeight.ExtraLight,
        R.font.sourgummy_semiexpanded_italic to FontWeight.Normal,
        R.font.sourgummy_semiexpanded_light to FontWeight.Light,
        R.font.sourgummy_semiexpanded_lightitalic to FontWeight.Light,
        R.font.sourgummy_semiexpanded_medium to FontWeight.Medium,
        R.font.sourgummy_semiexpanded_mediumitalic to FontWeight.Medium,
        R.font.sourgummy_semiexpanded_regular to FontWeight.Normal,
        R.font.sourgummy_semiexpanded_semibold to FontWeight.SemiBold,
        R.font.sourgummy_semiexpanded_semibolditalic to FontWeight.SemiBold,
        R.font.sourgummy_semiexpanded_thin to FontWeight.Thin,
        R.font.sourgummy_semiexpanded_thinitalic to FontWeight.Thin,
        R.font.sourgummy_thin to FontWeight.Thin,
        R.font.sourgummy_thinitalic to FontWeight.Thin
    )


    private fun loadFontFamily(vararg fonts: Pair<Int, FontWeight>): FontFamily {
        return FontFamily(*fonts.map { Font(it.first, it.second) }.toTypedArray())
    }
}
