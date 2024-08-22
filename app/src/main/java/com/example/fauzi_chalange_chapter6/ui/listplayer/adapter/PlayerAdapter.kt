package com.example.fauzi_chalange_chapter6.ui.listplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fauzi_chalange_chapter6.databinding.PlayerListBinding
import com.example.fauzi_chalange_chapter6.domain.model.Player
import com.example.fauzi_chalange_chapter6.ui.listplayer.viewholder.PlayerViewHolder


class PlayerAdapter(
    private val playerAdapterListener: PlayerAdapterListener,
    ) : ListAdapter<Player, PlayerViewHolder>(PlayerDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            clubViewBinding = PlayerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            playerAdapterListener = playerAdapterListener,
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    }
