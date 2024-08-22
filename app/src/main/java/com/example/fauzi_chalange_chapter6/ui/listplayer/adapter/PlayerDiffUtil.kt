package com.example.fauzi_chalange_chapter6.ui.listplayer.adapter
import androidx.recyclerview.widget.DiffUtil
import com.example.fauzi_chalange_chapter6.domain.model.Player

class PlayerDiffUtil : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.nama == newItem.nama
    }
    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.nama == newItem.nama
    }
}