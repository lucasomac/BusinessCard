package br.com.lucolimac.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.lucolimac.businesscard.App
import br.com.lucolimac.businesscard.databinding.ActivityMainBinding
import br.com.lucolimac.businesscard.util.Image

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val businessAdapter by lazy { BusinessCardAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvBusinessCards.adapter = businessAdapter
        getAllBusinessCards()
        binding.fbAddBusinessCard.setOnClickListener {
            startActivity(Intent(this, RegisterBusinessCardActivity::class.java))
        }
        businessAdapter.listenerShare = {
            Image.share(this, it)
        }
    }

    private fun getAllBusinessCards() {
        viewModel.getAll().observe(this) {
            businessAdapter.submitList(it)
        }
    }
}