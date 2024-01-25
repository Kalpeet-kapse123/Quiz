package com.kalpeet.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.quiz.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    val auth:FirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Sign Up"
        binding.buttonSignUp.setOnClickListener {
            val userEmail=binding.editTextEmail.text.toString()
            val userPassword=binding.editTextPassword.text.toString()
            signUpWithFirebase(userEmail,userPassword)
        }
    }

    private fun signUpWithFirebase(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Your account has been created.",Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Log.d("Hello","auth")

                Toast.makeText(applicationContext,task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }
    }
}