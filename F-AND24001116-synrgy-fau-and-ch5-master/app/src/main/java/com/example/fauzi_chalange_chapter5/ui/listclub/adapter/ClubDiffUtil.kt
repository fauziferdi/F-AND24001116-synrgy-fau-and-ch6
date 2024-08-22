package com.example.fauzi_chalange_chapter5.ui.listclub.adapter
import androidx.recyclerview.widget.DiffUtil
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

class ClubDiffUtil : DiffUtil.ItemCallback<Club>() {
    override fun areItemsTheSame(oldItem: Club, newItem: Club): Boolean {
        return oldItem.nama == newItem.nama
    }
    override fun areContentsTheSame(oldItem: Club, newItem: Club): Boolean {
        return oldItem.nama == newItem.nama
    }
}