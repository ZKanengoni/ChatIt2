package com.example.chatit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: Toolbar = findViewById(R.id.toolbar_register)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this@RegisterActivity, SplashActivity::class.java)

//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username: String = username_register.text.toString()
        val email: String = email_register.text.toString()
        val password: String = password_register.text.toString()

        if(username == "")
        {
            Toast.makeText(this@RegisterActivity, "Please enter a username", Toast.LENGTH_LONG).show()
        } else  if(email == "")
        {
            Toast.makeText(this@RegisterActivity, "Please enter a email", Toast.LENGTH_LONG).show()
        }
         else  if(password == "")
        {
            Toast.makeText(this@RegisterActivity, "Please enter a password", Toast.LENGTH_LONG).show()
        } else
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                   if(task.isSuccessful)
                   {
                       firebaseUserID = mAuth.currentUser!!.uid
                       refUsers = FirebaseDatabase.getInstance("https://chatit-61581-default-rtdb.europe-west1.firebasedatabase.app/").reference.child("Users").child(firebaseUserID)

                       val userHashMap = HashMap<String, Any>()
                       userHashMap["uid"] = firebaseUserID
                       userHashMap["username"] = username
                       userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/chatit-61581.appspot.com/o/profile-user.png?alt=media&token=e40e5407-1dbb-408a-b69c-a24e8adf0d02"
                       userHashMap["cover"] = "https://firebasestorage.googleapis.com/v0/b/chatit-61581.appspot.com/o/andrew-ridley-jR4Zf-riEjI-unsplash.jpg?alt=media&token=f2641284-1882-4122-a674-ddf3ecc4b051"
                       userHashMap["status"] = "offline"
                       userHashMap["search"] = username.toLowerCase()

                       refUsers.updateChildren(userHashMap)
                           .addOnCompleteListener{ task ->
                               if(task.isSuccessful)
                               {
                                   val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                   startActivity(intent)
                                   finish()
                               }
                           }
                   } else {
                       Toast.makeText(this@RegisterActivity, "Error Message: ${task.exception?.message.toString()}", Toast.LENGTH_LONG).show()
                   }
                }
        }
    }
}