package travelvlogs.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.travelvlogs.R
import com.example.travelvlogs.databinding.ActivityTravelVlogsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class TravelVlogsActivity : AppCompatActivity() {

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val data = it.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val intent = Intent(this,MapViewActivity ::class.java)
            startActivity(intent)
        }
    }



    private lateinit var binding: ActivityTravelVlogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelVlogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            activityResultLauncher.launch(GoogleSignIn.getClient(this,GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).signInIntent)
        }
    }
}