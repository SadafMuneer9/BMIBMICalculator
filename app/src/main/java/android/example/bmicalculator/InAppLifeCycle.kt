package android.example.bmicalculator

import android.util.Log
import com.moengage.inapp.listeners.InAppLifeCycleListener
import com.moengage.inapp.model.InAppData

class InAppLifeCycle: InAppLifeCycleListener {
    override fun onDismiss(inAppData: InAppData) {
        Log.i("inAppLifeCycle","onDismiss")
    }

    override fun onShown(inAppData: InAppData) {
        Log.i("inAppLifeCycle","onShown")
    }
}