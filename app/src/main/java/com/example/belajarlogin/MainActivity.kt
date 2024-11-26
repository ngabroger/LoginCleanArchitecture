package com.example.belajarlogin

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.lifecycleScope
import com.example.belajarlogin.databinding.ActivityMainBinding
import com.example.belajarlogin.presentation.viewmodel.AuthViewModelImpl
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AuthViewModelImpl by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.userName.collect { result ->
                Log.d("MainActivity", "userName collected: $result")
                binding.tvHome.text = result ?: "No User"
            }
            viewModel.token.collect{result ->
                if (result != null){
                    Log.d("MainActivity", "Token collected: $result")
                }else{
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.fetchUserName()
            viewModel.getToken()
        }


    }
}