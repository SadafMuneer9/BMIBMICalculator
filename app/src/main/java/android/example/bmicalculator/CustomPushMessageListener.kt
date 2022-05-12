package android.example.bmicalculator

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.moengage.pushbase.model.NotificationPayload
import com.moengage.pushbase.push.PushMessageListener

class CustomPushMessageListener : PushMessageListener() {

//    override fun isNotificationRequired(context: Context, payload: Bundle): Boolean {
//
//        val shouldShowNotification = payload.getBoolean("shouldShowNotification")
//
//        return shouldShowNotification;
//    }
//
//    override fun onCreateNotification(
//        context: Context,
//        notificationPayload: NotificationPayload
//    ): NotificationCompat.Builder {
//        val isCustom = notificationPayload.payload.getBoolean("isCustom", false)
//        if (isCustom) {
//            val builder = super.onCreateNotification(context, notificationPayload)
//            builder.setContentTitle("This is a custom notification")
//            return builder
//        } else
//            return super.onCreateNotification(context, notificationPayload)
//
//    }
    override fun onHandleRedirection(activity: Activity, payload: Bundle) {
        super.onHandleRedirection(activity, payload)
    }
}
