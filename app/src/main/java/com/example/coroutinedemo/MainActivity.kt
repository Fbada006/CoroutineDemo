package com.example.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinedemo.models.Crypto
import com.example.coroutinedemo.models.Loading
import com.example.coroutinedemo.models.Success
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: CryptoViewModel by viewModels()
    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cryptoAdapter = CryptoAdapter()

        initRecView()
        observeUiState()
        observeCryptoList()
    }

    private fun observeCryptoList() {
        viewModel.cryptos.observe(this, {
            cryptoAdapter.submitList(it)
        })
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this, {
            when (it) {
                is Loading -> showLoading()
                is Success -> showCryptoData()
                is Error -> showError()
            }
        })
    }

    private fun showLoading() {
        loading_spinner.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }

    private fun showError() {
        tv_error.visibility = View.VISIBLE
        loading_spinner.visibility = View.GONE
    }

    private fun showCryptoData() {
        loading_spinner.visibility = View.GONE
        tv_error.visibility = View.GONE
    }

    private fun initRecView() {
        crypto_list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = cryptoAdapter
        }
    }
}