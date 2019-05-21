package us.koller.cameraroll.kotlin.themes

import us.koller.cameraroll.kotlin.R

open class DarkTheme : Theme() {

    override val baseTheme = BASE_DARK

    override val backgroundColorRes = R.color.dark_bg

    override val toolbarColorRes = R.color.black_translucent2

    override val textColorPrimaryRes = R.color.white

    override val textColorSecondaryRes = R.color.white_translucent2

    override val accentColorRes = R.color.colorAccent

    override val accentColorLightRes = R.color.colorAccentLight

    override val accentTextColorRes = R.color.colorAccent_text

    override val dialogThemeRes = R.style.CameraRoll_Theme_Dialog

    override fun darkStatusBarIcons(): Boolean {
        return false
    }

    override fun elevatedToolbar(): Boolean {
        return false
    }

    override fun statusBarOverlay(): Boolean {
        return false
    }

    override fun darkStatusBarIconsInSelectorMode(): Boolean {
        return true
    }
}
