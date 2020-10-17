package com.example.coroutinedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.models.Crypto
import kotlinx.android.synthetic.main.crypto_item.view.*

class CryptoAdapter :
    ListAdapter<Crypto, CryptoAdapter.CryptoViewHolder>(CryptoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CryptoViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Crypto) = with(itemView) {
            this.tv_crypto_name.text = item.name
            this.tv_crypto_symbol.text = item.symbol
            this.tv_crypto_id.text = item.id
        }

        companion object {
            fun from(parent: ViewGroup): CryptoViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.crypto_item, parent, false)
                return CryptoViewHolder(view)
            }
        }
    }
}

class CryptoDiffCallback : DiffUtil.ItemCallback<Crypto>() {
    override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return oldItem == newItem
    }
}
