package android.example.bmicalculator

import android.app.Application
import android.content.Context
import android.util.Log
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.model.AppStatus

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        val moEngage = MoEngage.Builder(this, "B9GXHXTQGUAI8B3DV42K84I8")
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureNotificationMetaData(
                NotificationConfig(
                    smallIcon = R.drawable.ic_baseline_email_24,
                    largeIcon = R.drawable.ic_baseline_email_24,
                    notificationColor = R.color.black,
                    isLargeIconDisplayEnabled = true,
                    tone = null,
                    isMultipleNotificationInDrawerEnabled = true,
                    isBuildingBackStackEnabled = true
                )
            )
            .build()
        MoEngage.initialise(moEngage)

        trackInstallOrUpdate()
    }

    private fun trackInstallOrUpdate() {

        val versionCode = "version_code"

        //keys are just sample keys, use suitable keys for the apps
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE) ?: return

        //Fresh Install
        if (!preferences.contains(versionCode)) {
            preferences.edit().putInt(versionCode, BuildConfig.VERSION_CODE).apply()
            MoEHelper.getInstance(this).setAppStatus(AppStatus.INSTALL)
            Log.v("MoEngage", "Fresh Install")
            return
        }

        //Update
        val currentVersion = preferences.getInt(versionCode, -1)

        if (currentVersion < BuildConfig.VERSION_CODE) {
            preferences.edit().putInt(versionCode, BuildConfig.VERSION_CODE).apply()
            MoEHelper.getInstance(this).setAppStatus(AppStatus.UPDATE)
            Log.v("MoEngage", "App Updated")
        }
    }
}