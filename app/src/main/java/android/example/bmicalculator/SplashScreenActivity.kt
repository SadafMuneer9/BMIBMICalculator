package android.example.bmicalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            val email=getSavedUserData()
            if(email!=null&& email.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        },2000)

    }
    private fun getSavedUserData():String?{
        val preference=getSharedPreferences("pref", Context.MODE_PRIVATE)
        return preference.getString("userEmail",null)
    }
}

