package br.com.lucolimac.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.lucolimac.businesscard.App
import br.com.lucolimac.businesscard.R
import br.com.lucolimac.businesscard.data.BusinessCard
import br.com.lucolimac.businesscard.databinding.ActivityRegisterBusinessCardBinding

class RegisterBusinessCardActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val binding by lazy { ActivityRegisterBusinessCardBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btClose.setOnClickListener { onBackPressed() }
        binding.btAddBusinessCard.setOnClickListener {
            viewModel.insert(getNewBusinessCardData())
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun getNewBusinessCardData(): BusinessCard {
        return BusinessCard(
            name = binding.tilName.editText?.text.toString(),
            phoneNumber = binding.tilPhoneNumber.editText?.text.toString(),
            emailAddress = binding.tilEmailAddress.editText?.text.toString(),
            company = binding.tilCompany.editText?.text.toString(),
            backgroundColor = binding.tilColor.editText?.text.toString()
        )
    }
}