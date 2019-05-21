package us.koller.cameraroll.kotlin.themes

import us.koller.cameraroll.kotlin.R

class LightTheme : Theme() {

    override val baseTheme: Int
        get() = BASE_LIGHT

    override val backgroundColorRes = R.color.light_bg

    override val toolbarColorRes = R.color.colorPrimary_light

    override val textColorPrimaryRes = R.color.grey_800

    override val textColorSecondaryRes = R.color.grey_900_translucent1

    override val accentColorRes = R.color.colorAccent_light

    override val accentColorLightRes = R.color.colorAccentLight_light

    override val accentTextColorRes = R.color.colorAccent_text_light

    override val dialogThemeRes = R.style.CameraRoll_Theme_Light_Dialog

    override fun darkStatusBarIcons(): Boolean {
        return true
    }

    override fun elevatedToolbar(): Boolean {
        return true
    }

    override fun statusBarOverlay(): Boolean {
        return false
    }

    override fun darkStatusBarIconsInSelectorMode(): Boolean {
        return true
    }
}
