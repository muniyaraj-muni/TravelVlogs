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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import travelvlogs.model.User

class TravelVlogsActivity : AppCompatActivity() {

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val data = it.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            storeData(task)
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

    private fun storeData(task: Task<GoogleSignInAccount>) {
        if(task.result != null){
            val user = User(task.result.displayName,task.result.email,task.result.photoUrl)
            val database  = FirebaseFirestore.getInstance()
            if(task.result.email != null){
                database.collection("users").document(task.result.email!!).set(user).addOnSuccessListener {
                    Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"Error occurred",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}