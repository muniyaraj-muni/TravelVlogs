package travelvlogs.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.travelvlogs.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activty)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,TravelVlogsActivity::class.java))
            finish()
        },2000)

    }
}