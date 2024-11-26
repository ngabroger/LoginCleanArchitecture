package com.example.belajarlogin.presentation.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.belajarlogin.MainActivity
import com.example.belajarlogin.R
import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.databinding.ActivityRegisterBinding
import com.example.belajarlogin.presentation.viewmodel.AuthViewModelImpl
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModelImpl by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
       setupListener()
        observeViewModel()
    }

    private fun setupListener() {
        binding.navLogin.setOnClickListener {
            finish()
        }
        val email = binding.edEmailRegister
        val password = binding.edPasswordRegister
        val username = binding.edUsernameRegister

        binding.btnRegister.setOnClickListener{
            when{
                email.text.toString().isEmpty()->{
                    email.error = "Email is required"
                }
                password.text.toString().isEmpty()->{
                    password.error = "Password is required"
                }
                username.text.toString().isEmpty()->{
                    username.error = "Username is required"
                }
                else ->{
                    lifecycleScope.launch {
                        viewModel.register(email.text.toString(), password.text.toString(), username.text.toString())
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch{
            viewModel.register.collect{result ->
                when(result){
                    is ResultUtil.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, result.exception, Toast.LENGTH_SHORT).show()
                    }
                    ResultUtil.Idle -> {
                        binding.progressBar.visibility = View.GONE

                    }
                    ResultUtil.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ResultUtil.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, result.data.toString() , Toast.LENGTH_SHORT).show()
                        Intent(this@RegisterActivity, MainActivity::class.java).also {
                            startActivity(it)
                            finish()}
                    }
                }
            }
        }

    }

}