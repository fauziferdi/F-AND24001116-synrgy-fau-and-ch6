package com.example.fauzi_chalange_chapter5.ui.listclub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fauzi_chalange_chapter5.databinding.ClubListBinding
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club
import com.example.fauzi_chalange_chapter5.ui.listclub.viewholder.ClubViewHolder


class ClubAdapter(
    private val clubAdapterListener: ClubAdapterListener,
    ) : ListAdapter<Club, ClubViewHolder>(ClubDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(
            clubViewBinding = ClubListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            clubAdapterListener = clubAdapterListener,
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    }
