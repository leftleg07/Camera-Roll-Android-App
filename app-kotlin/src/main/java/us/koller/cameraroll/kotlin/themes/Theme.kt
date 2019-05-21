package us.koller.cameraroll.kotlin.themes

import android.content.Context

import androidx.core.content.ContextCompat

abstract class Theme {


    abstract val baseTheme: Int

    fun isBaseLight() = baseTheme == BASE_LIGHT

    /*colors*/
    abstract val backgroundColorRes: Int

    abstract val toolbarColorRes: Int

    abstract val textColorPrimaryRes: Int

    abstract val textColorSecondaryRes: Int

    abstract val accentColorRes: Int

    abstract val accentColorLightRes: Int

    abstract val accentTextColorRes: Int


    /*Dialog theme*/
    abstract val dialogThemeRes: Int

    /*flags*/
    abstract fun darkStatusBarIcons(): Boolean

    abstract fun elevatedToolbar(): Boolean

    abstract fun statusBarOverlay(): Boolean

    abstract fun darkStatusBarIconsInSelectorMode(): Boolean


    fun getBackgroundColor(context: Context): Int {
        return getColor(context, backgroundColorRes)
    }

    fun getToolbarColor(context: Context): Int {
        return getColor(context, toolbarColorRes)
    }

    fun getTextColorPrimary(context: Context): Int {
        return getColor(context, textColorPrimaryRes)
    }

    fun getTextColorSecondary(context: Context): Int {
        return getColor(context, textColorSecondaryRes)
    }

    fun getAccentColor(context: Context): Int {
        return getColor(context, accentColorRes)
    }

    fun getAccentColorLight(context: Context): Int {
        return getColor(context, accentColorLightRes)
    }

    fun getAccentTextColor(context: Context): Int {
        return getColor(context, accentTextColorRes)
    }

    override fun equals(other: Any?): Boolean {
        val c = this.javaClass
        return c.isInstance(other)
    }

    private fun getColor(context: Context, res: Int): Int {
        return ContextCompat.getColor(context, res)
    }

    companion object {
        const val BASE_DARK = 0
        const val BASE_LIGHT = 1
    }
}
