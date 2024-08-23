package com.example.fauzi_chalange_chapter5.ui.favoriteclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fauzi_chalange_chapter5.databinding.FragmentListFavBinding
import com.example.fauzi_chalange_chapter5.ui.listclub.adapter.ClubAdapter
import com.example.fauzi_chalange_chapter5.ui.listclub.adapter.ClubAdapterListener
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

class FavoriteClubFragment : Fragment(), ClubAdapterListener{

    private val viewModel: FavoriteClubViewModel by viewModels{
        FavoriteClubViewModel.provideFactory(this, requireActivity().applicationContext)
    }

    private val clubAdapter: ClubAdapter by lazy {
        ClubAdapter(this)
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
        binding.recyclerView.adapter = clubAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(
            context,
            2
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        viewModel.clubs.observe(viewLifecycleOwner) { club ->
            clubAdapter.submitList(club)
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

    private fun goToThirdFragment(data: Club) {
        val actionToDetailFragment = FavoriteClubFragmentDirections.actionFavoriteClubFragmentToListDetailFragment()
        actionToDetailFragment.nama = data.nama
        actionToDetailFragment.image = data.image
        actionToDetailFragment.id = data.id ?: -1
        findNavController().navigate(actionToDetailFragment)
    }

    override fun onClickClub(data: Club) {
        goToThirdFragment(data)
    }

}