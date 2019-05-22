package us.koller.cameraroll.kotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.transaction
import androidx.preference.*
import com.f2prateek.rx.preferences2.RxSharedPreferences
import us.koller.cameraroll.kotlin.R
import us.koller.cameraroll.kotlin.data.Settings
import us.koller.cameraroll.kotlin.preferences.ColumnCountPreference
import us.koller.cameraroll.kotlin.preferences.ColumnCountPreferenceDialogFragment
import us.koller.cameraroll.kotlin.preferences.StylePreference
import us.koller.cameraroll.kotlin.preferences.StylePreferenceDialogFragment
import us.koller.cameraroll.kotlin.themes.Theme
import us.koller.cameraroll.kotlin.util.Util





class SettingsActivity : ThemeableActivity() {

    companion object {
        private var recreated = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        //setting window insets manually
        val rootView = findViewById<View>(R.id.root_view)
        val container = findViewById<View>(R.id.preference_fragment_container)

        val fragment = SettingsFragment()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val rxPreferences = RxSharedPreferences.create(preferences)

        supportFragmentManager.transaction {
            replace(R.id.preference_fragment_container, fragment)
        }

        fragment.callback = object : SettingsFragment.OnSettingChangedCallback {
            override fun onSettingChanged() {
                setResult(Activity.RESULT_OK)
            }
        }

        setSystemUiFlags()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun recreate() {
        recreated = true
        super.recreate()
    }

    override fun onBackPressed() {
        if (recreated) {
            setResult(Activity.RESULT_OK)
        }
        super.onBackPressed()
    }

    override fun getDarkThemeRes(): Int {
        return R.style.CameraRoll_Theme_Settings
    }

    override fun getLightThemeRes(): Int {
        return R.style.CameraRoll_Theme_Light_Settings
    }

    override fun onThemeApplied(theme: Theme) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setBackgroundColor(toolbarColor)
        toolbar.setTitleTextColor(textColorPrimary)

        if (theme.darkStatusBarIcons() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Util.setDarkStatusBarIcons(findViewById<View>(R.id.root_view))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val statusBarColor = statusBarColor
            window.statusBarColor = statusBarColor
        }
    }

    class SettingsFragment: PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

        companion object {
            private const val DIALOG_FRAGMENT_TAG = "android.support.v7.preference.PreferenceFragment.DIALOG"
            private const val SHOWN_DIALOG_FRAGMENT = "SHOWN_DIALOG_FRAGMENT"
            private const val NONE = 0
            private const val STYLE_DIALOG_FRAGMENT = 1
            private const val COLUMN_COUNT_DIALOG_FRAGMENT = 2
        }


        private var shownDialogFragment = NONE
        var callback: OnSettingChangedCallback? = null

        interface OnSettingChangedCallback {
            fun onSettingChanged()
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preferences)

            val settings = Settings.getInstance(context)

            initExcludedPathsPref()
            initVirtualDirectoriesPref()
            initThemePref(settings.theme)
            initStylePref(settings.getStyle(context, false))
            initColumnCountPref(settings.realColumnCount)
            initShowVideos(settings.showVideos())
            initMediaRetrieverPref(settings.useStorageRetriever())
            init8BitColorPref(settings.use8BitColor())
            initCameraShortcutPref(settings.cameraShortcut)
            initAnimationsPref(settings.showAnimations())
            initMaxBrightnessPref(settings.isMaxBrightness)

            if (savedInstanceState != null && savedInstanceState.containsKey(SHOWN_DIALOG_FRAGMENT)) {
                val shownDialogFragment = savedInstanceState.getInt(SHOWN_DIALOG_FRAGMENT)
                var preference: Preference? = null
                if (shownDialogFragment == STYLE_DIALOG_FRAGMENT) {
                    preference = findPreference(getString(R.string.pref_key_style))
                } else if (shownDialogFragment == COLUMN_COUNT_DIALOG_FRAGMENT) {
                    preference = findPreference(getString(R.string.pref_key_column_count))
                }

                if (preference != null) {
                    onDisplayPreferenceDialog(preference)
                }
            }
        }

        private fun initExcludedPathsPref() {
            val pref = findPreference(getString(R.string.pref_key_excluded_paths))
            pref.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                callback?.onSettingChanged()
//                val intent = Intent(context, ExcludePathsActivity::class.java)
//                context!!.startActivity(intent)
                false
            }
        }

        private fun initVirtualDirectoriesPref() {
            val pref = findPreference(getString(R.string.pref_key_virtual_directories))
            pref.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                callback?.onSettingChanged()
                val intent = Intent(context, VirtualAlbumsActivity::class.java)
                context?.startActivity(intent)
                false
            }
        }

        private fun initThemePref(theme: String) {
            val themePref = findPreference(getString(R.string.pref_key_theme)) as ListPreference

            val themeName = Settings.Utils.getThemeName(activity, theme)
            themePref.summary = themeName
            themePref.onPreferenceChangeListener = this
        }

        private fun initStylePref(style: Int) {
            val stylePref = findPreference(getString(R.string.pref_key_style)) as StylePreference

            val styleName = Settings.Utils.getStyleName(activity, style)
            stylePref.summary = styleName
            stylePref.onPreferenceChangeListener = this
        }

        private fun initColumnCountPref(column_count: Int) {
            val columnCountPref = findPreference(getString(R.string.pref_key_column_count)) as ColumnCountPreference

            columnCountPref.summary = column_count.toString()
            columnCountPref.onPreferenceChangeListener = this
        }

        private fun initShowVideos(hide: Boolean) {
            val prefs = findPreference(getString(R.string.pref_key_show_videos)) as SwitchPreference
            prefs.isChecked = hide
            prefs.onPreferenceChangeListener = this
        }

        private fun initMediaRetrieverPref(storageRetriever: Boolean) {
            val mediaRetrieverPref = findPreference(getString(R.string.pref_key_media_retriever)) as TwoStatePreference

            mediaRetrieverPref.isChecked = storageRetriever
            mediaRetrieverPref.onPreferenceChangeListener = this
        }

        private fun init8BitColorPref(use8BitColor: Boolean) {
            val use8BitColorPref = findPreference(getString(R.string.pref_key_8_bit_color)) as TwoStatePreference

            use8BitColorPref.isChecked = use8BitColor
            use8BitColorPref.onPreferenceChangeListener = this
        }

        private fun initCameraShortcutPref(cameraShortcut: Boolean) {
            val cameraShortcutPref = findPreference(getString(R.string.pref_key_camera_shortcut)) as TwoStatePreference

            cameraShortcutPref.isChecked = cameraShortcut
            cameraShortcutPref.onPreferenceChangeListener = this
        }

        private fun initAnimationsPref(showAnimations: Boolean) {
            val animationsPref = findPreference(getString(R.string.pref_key_animations)) as TwoStatePreference

            animationsPref.isChecked = showAnimations
            animationsPref.onPreferenceChangeListener = this
        }

        private fun initMaxBrightnessPref(maxBrightness: Boolean) {
            val animationsPref = findPreference(getString(R.string.pref_key_max_brightness)) as TwoStatePreference

            animationsPref.isChecked = maxBrightness
            animationsPref.onPreferenceChangeListener = this
        }

        override fun onDisplayPreferenceDialog(preference: Preference) {
            callback?.onSettingChanged()

            var dialogFragment: DialogFragment? = null
            if (preference is StylePreference) {
                dialogFragment = StylePreferenceDialogFragment
                        .newInstance(preference)
            } else if (preference is ColumnCountPreference) {
                dialogFragment = ColumnCountPreferenceDialogFragment
                        .newInstance(preference)
            }

            if (dialogFragment != null) {
                dialogFragment.setTargetFragment(this, 0)
                dialogFragment.show(this.fragmentManager!!, DIALOG_FRAGMENT_TAG)
                return
            }

            super.onDisplayPreferenceDialog(preference)
        }

        override fun onPause() {
            super.onPause()

            if (activity!!.isChangingConfigurations) {
                val fragment = fragmentManager!!.findFragmentByTag(DIALOG_FRAGMENT_TAG)
                if (fragment != null && fragment is DialogFragment) {
                    /*if (fragment instanceof StylePreferenceDialogFragment) {
                        shownDialogFragment = STYLE_DIALOG_FRAGMENT;
                    } else if (fragment instanceof ColumnCountPreferenceDialogFragment) {
                        shownDialogFragment = COLUMN_COUNT_DIALOG_FRAGMENT;
                    }*/

                    fragment.dismiss()
                }
            }
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putInt(SHOWN_DIALOG_FRAGMENT, shownDialogFragment)
        }

        override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
            Log.d("SettingsActivity", "onPreferenceChange() called with: preference = [$preference], newValue = [$newValue]")
            callback?.onSettingChanged()

            val settings = Settings.getInstance(activity)
            when(preference.key) {
                getString(R.string.pref_key_theme) -> {
                    val themeValue = newValue as String
                    settings.theme = themeValue

                    val themeName = Settings.Utils.getThemeName(activity, themeValue)
                    preference.summary = themeName

                    activity?.recreate()
                }
                getString(R.string.pref_key_style) -> {
                    settings.setStyle(newValue as Int)
                    val styleName = Settings.Utils.getStyleName(activity, newValue)
                    preference.summary = styleName

                }
                getString(R.string.pref_key_column_count) -> {
                    settings.setColumnCount(newValue as Int)
                    preference.summary = newValue.toString()
                }
                getString(R.string.pref_key_media_retriever) -> settings.useStorageRetriever(newValue as Boolean)
                getString(R.string.pref_key_8_bit_color) -> settings.use8BitColor(newValue as Boolean)
                getString(R.string.pref_key_camera_shortcut) -> settings.cameraShortcut = newValue as Boolean
                getString(R.string.pref_key_animations) -> settings.showAnimations(newValue as Boolean)
                getString(R.string.pref_key_show_videos) -> settings.showVideos(newValue as Boolean)
                getString(R.string.pref_key_max_brightness) -> settings.isMaxBrightness = newValue as Boolean
            }
            return true
        }

    }
}
