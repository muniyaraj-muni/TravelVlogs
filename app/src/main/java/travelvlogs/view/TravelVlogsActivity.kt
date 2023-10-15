package travelvlogs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelvlogs.R
import com.example.travelvlogs.databinding.ActivityTravelVlogsBinding

class TravelVlogsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTravelVlogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelVlogsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val transaction = supportFragmentManager.beginTransaction()
        val fragment = LoginFragment()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}