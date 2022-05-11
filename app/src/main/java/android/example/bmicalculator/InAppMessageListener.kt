package android.example.bmicalculator

import android.util.Log
import com.moengage.inapp.listeners.InAppMessageListener
import com.moengage.inapp.model.MoEInAppCampaign

class InAppMessageListener: InAppMessageListener() {
    override fun onNavigation(inAppCampaign: MoEInAppCampaign): Boolean {
        Log.i("InAppNavigation","OnNavigation")
        return super.onNavigation(inAppCampaign)
    }

    override fun onCustomAction(inAppCampaign: MoEInAppCampaign) {
        Log.i("InAppNavigation","ONCustom")
        super.onCustomAction(inAppCampaign)
    }

    override fun onSelfHandledAvailable(inAppCampaign: MoEInAppCampaign) {
        super.onSelfHandledAvailable(inAppCampaign)
    }
}