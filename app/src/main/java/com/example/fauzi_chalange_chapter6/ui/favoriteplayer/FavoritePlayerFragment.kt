package com.example.fauzi_chalange_chapter6.ui.favoriteplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fauzi_chalange_chapter6.databinding.FragmentListFavBinding
import com.example.fauzi_chalange_chapter6.ui.listplayer.adapter.PlayerAdapter
import com.example.fauzi_chalange_chapter6.ui.listplayer.adapter.PlayerAdapterListener
import com.example.fauzi_chalange_chapter6.domain.model.Player
import com.example.fauzi_chalange_chapter6.MyApplication

class FavoritePlayerFragment : Fragment(), PlayerAdapterListener{

    private val viewModel: FavoritePlayerViewModel by viewModel<FavoritePlayerViewModel>()

    private val playerAdapter: PlayerAdapter by lazy {
        PlayerAdapter(this)
    }

    private lateinit var binding: FragmentListFavBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListFavBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = playerAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(
            context,
            2
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        viewModel.clubs.observe(viewLifecycleOwner) { club ->
            playerAdapter.submitList(club)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { throwable ->
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }
        viewModel.getClubFromLocal()
    }

    private fun goToThirdFragment(data: Player) {
        val actionToDetailFragment = FavoritePlayerFragmentDirections.actionFavoriteClubFragmentToListDetailFragment()
        actionToDetailFragment.nama = data.nama
        actionToDetailFragment.image = data.image
        actionToDetailFragment.id = data.id ?: -1
        findNavController().navigate(actionToDetailFragment)
    }

    override fun onClickClub(data: Player) {
        goToThirdFragment(data)
    }

}