package android.example.bmicalculator

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper

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