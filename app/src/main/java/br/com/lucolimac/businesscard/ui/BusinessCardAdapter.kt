package br.com.lucolimac.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.businesscard.data.BusinessCard
import br.com.lucolimac.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallBack()) {

    var listenerShare: (View) -> Unit = {}

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            with(binding) {
                tvName.text = item.name
                tvPhoneNumber.text = item.phoneNumber
                tvEmailAddress.text = item.emailAddress
                tvCompany.text = item.company
                mcBusinessCard.setBackgroundColor(Color.parseColor(item.backgroundColor))
                mcBusinessCard.setOnClickListener {
                    listenerShare(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBusinessCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallBack() : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

}