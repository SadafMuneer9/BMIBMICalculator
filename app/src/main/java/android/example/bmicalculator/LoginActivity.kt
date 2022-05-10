package android.example.bmicalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.model.AppStatus

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val email = findViewById<View>(R.id.email) as TextView
        val password = findViewById<View>(R.id.password) as TextView
        val loginbtn = findViewById<View>(R.id.loginbtn) as Button


        loginbtn.setOnClickListener {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                    .matches() && (password.text.toString().isNotEmpty())
            ) {
                saveEmail(email = email.text.toString())
                MoEHelper.getInstance(this).setUniqueId(email.text.toString())

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "LOGIN FAILED!", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun saveEmail(email:String){
        val preference = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor=preference.edit()
        editor.putString("userEmail",email)
            .apply()

    }

}
