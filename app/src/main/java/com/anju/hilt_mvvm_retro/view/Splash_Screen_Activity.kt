package com.anju.hilt_mvvm_retro.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.anju.hilt_mvvm_retro.R

@SuppressLint("CustomSplashScreen")
class Splash_Screen_Activity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_splash_screen)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2000)
        }
}