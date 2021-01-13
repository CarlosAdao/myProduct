package com.example.mywallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login_body.*

class Loginctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginctivity)

        tvRegister.setOnClickListener{
            callRegister()
        }
    }

    //Chama a activity para registrar um novo usuário
    fun callRegister(){
        var intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}

