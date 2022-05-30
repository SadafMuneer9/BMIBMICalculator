package android.example.bmicalculator

import android.content.Context
import com.moengage.mi.listener.MiPushEventListener
import com.xiaomi.mipush.sdk.MiPushMessage

class MiPushEventCallbackListener:MiPushEventListener() {
    override fun onNonMoEngageNotificationClicked(context: Context, message: MiPushMessage) {
        super.onNonMoEngageNotificationClicked(context, message)
    }

    override fun onNonMoEngagePassThroughMessage(context: Context, message: MiPushMessage) {
        super.onNonMoEngagePassThroughMessage(context, message)
    }

    override fun onTokenAvailable(token: String) {
        super.onTokenAvailable(token)
    }
}