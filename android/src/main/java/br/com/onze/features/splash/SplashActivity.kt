package br.com.onze.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.jeancsanchez.onze.databinding.ActivitySplashBinding
import br.com.onze.features.account.LoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)
    }
}