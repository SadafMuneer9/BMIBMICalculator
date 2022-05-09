package android.example.bmicalculator

import android.util.Log
import android.util.Log.v
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.core.internal.logger.Logger.setTag
import com.moengage.core.internal.logger.Logger.v
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper
import java.util.logging.Logger

class MyFirebaseMsg : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("FirebaseMessageService", token)
        MoEFireBaseHelper.getInstance().passPushToken(this,token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val payload=message.data
        if(MoEPushHelper.getInstance().isFromMoEngagePlatform(payload)){
            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext,payload)
        }else{
            //
        }
    }
}