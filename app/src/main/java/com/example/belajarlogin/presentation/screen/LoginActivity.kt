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
import com.example.belajarlogin.databinding.ActivityLoginBinding
import com.example.belajarlogin.presentation.viewmodel.AuthViewModelImpl
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityLoginBinding
    private val viewModel : AuthViewModelImpl by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        observeViewModel()

        lifecycleScope.launch{
            viewModel.getToken().collect{
                if(it!=null){
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }


    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.user.collect { result ->
                when (result) {
                    is ResultUtil.Error -> {
                        binding.clError.visibility = View.VISIBLE
                        binding.tvError.text = result.exception
                        binding.progressBar.visibility = View.GONE
                    }
                    ResultUtil.Loading -> {
                        binding.clError.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ResultUtil.Success -> {
                        binding.clError.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Intent(this@LoginActivity, MainActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }

                    ResultUtil.Idle -> {
                        binding.progressBar.visibility = View.GONE
                        binding.clError.visibility = View.GONE
                    }
                }
            }


        }

    }

    private fun setupListeners() {
        binding.progressBar.visibility = View.GONE
        binding.btnDismiss.setOnClickListener {
            binding.clError.visibility = View.GONE
        }

        binding.navRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmailTxt.text.toString()
            val password = binding.edPasswordTxt.text.toString()

            when {
                email.isEmpty() -> {

                    binding.edEmailTxt.error = "Email is required"
                }
                password.isEmpty() -> {

                    binding.edPasswordTxt.error = "Password is required"
                }
                else -> {
                    binding.clError.visibility = View.GONE
                    handleLogin(email, password)
                }
            }
        }
    }


    private fun handleLogin(email: String, password: String) {
        lifecycleScope.launch {
            viewModel.login(email, password)
        }
    }

}
