package com.example.fauzi_chalange_chapter6.ui.listplayer.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fauzi_chalange_chapter6.databinding.PlayerListBinding
import com.example.fauzi_chalange_chapter6.ui.listplayer.adapter.PlayerAdapterListener
import com.example.fauzi_chalange_chapter6.domain.model.Player

class PlayerViewHolder(
    private val clubViewBinding: PlayerListBinding,
    private val playerAdapterListener: PlayerAdapterListener,
) : RecyclerView.ViewHolder(clubViewBinding.root) {

    fun bind(data: Player) {
        clubViewBinding.textTitle.text = data.nama
        clubViewBinding.imagePoster.load(data.image)
        clubViewBinding.root.setOnClickListener { playerAdapterListener.onClickClub(data) }
    }

}