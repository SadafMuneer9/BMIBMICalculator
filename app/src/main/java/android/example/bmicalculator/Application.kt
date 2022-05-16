package android.example.bmicalculator

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.InAppConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.model.AppStatus
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inapp.listeners.InAppMessageListener
import com.moengage.inbox.core.MoEInboxHelper
import com.moengage.pushbase.MoEPushHelper

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        val moEngage = MoEngage.Builder(this, "B9GXHXTQGUAI8B3DV42K84I8")
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureNotificationMetaData(
                NotificationConfig(
                    smallIcon = R.drawable.ic_baseline_email_24,
                    largeIcon = R.drawable.bmi,
                    notificationColor = R.color.black,
                    isLargeIconDisplayEnabled = true,
                    tone= "tone_new",
                    isMultipleNotificationInDrawerEnabled = true,
                    isBuildingBackStackEnabled = true
                )
            ).configureInApps(InAppConfig(true, emptySet()))
            .build()
        MoEngage.initialise(moEngage)
        MoEPushHelper.getInstance().messageListener = CustomPushMessageListener()
        MoEInAppHelper.getInstance().addInAppLifeCycleListener(InAppLifeCycle())
        MoEInAppHelper.getInstance().registerListener(android.example.bmicalculator.InAppMessageListener())

        trackInstallOrUpdate()
        createNotificationChannel()
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
    private fun createNotificationChannel() {
        val nm =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "hello_world",
                "my_channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Created by Vipin"
                enableVibration(true)
            }
            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            channel.setSound(
                Uri.parse("android.resource://${packageName}/" + R.raw.tone_new),
                attributes
            )
            nm.createNotificationChannel(channel)
        }
    }
}