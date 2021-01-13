package com.example.mywallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.register_body.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            var email = edUserEmailRegister.text.toString()
            var password = edUserPasswordRegister.text.toString()

            var rEdUser = email.isNotEmpty()
            var rPassUser = password.isNotEmpty()

            if (rEdUser && rPassUser)
                registerFirebase(email, password)
            else
                showMsg("Preencha todos os campos")

        }
    }

    fun registerFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    showMsg("usuário registrado com sucesso")
                    var idUser = firebaseUser.uid
                    var email = firebaseUser.email
                    callMain(idUser.toString(), email.toString())
                } else {
                    showMsg(task.exception?.message.toString())
                }

            }
    }
    //Chama LoginActivity
    fun callLogin() {
        var intent = Intent(this, Loginctivity::class.java)
        startActivity(intent)
    }

    fun callMain(id: String, email: String){
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user_id", id)
        intent.putExtra("user_email", email)
        startActivity(intent)
        finish()
    }

    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}