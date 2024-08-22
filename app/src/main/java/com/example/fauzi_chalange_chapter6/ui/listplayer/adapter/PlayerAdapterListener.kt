package com.example.fauzi_chalange_chapter6.ui.listplayer.adapter
import com.example.fauzi_chalange_chapter6.domain.model.Player

interface PlayerAdapterListener {
    fun onClickClub(data: Player)
}