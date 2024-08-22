package com.example.fauzi_chalange_chapter6.ui.listplayer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fauzi_chalange_chapter6.databinding.FragmentListBinding
import com.example.fauzi_chalange_chapter6.ui.listplayer.adapter.PlayerAdapter
import com.example.fauzi_chalange_chapter6.ui.listplayer.adapter.PlayerAdapterListener
import com.example.fauzi_chalange_chapter6.domain.model.Player
import com.example.fauzi_chalange_chapter6.R
import com.example.fauzi_chalange_chapter6.MyApplication
import com.example.fauzi_chalange_chapter6.ui.imageblur.ImageBlurActivity

class ListPlayerFragment : Fragment(), PlayerAdapterListener {

    private lateinit var binding: FragmentListBinding
    private val playerAdapter = PlayerAdapter(this)

    private val listPlayerFragmentViewModel by viewModel<ListPlayerFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view.context)
        refreshData()
        listPlayerFragmentViewModel.clubs.observe(viewLifecycleOwner) { clubs->
            playerAdapter.submitList(clubs)
        }
        listPlayerFragmentViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.btnToFav.setOnClickListener { goToFavoriteFragment() }
        binding.btnToFoto.setOnClickListener {openActivityFoto()}
    }


    private fun setupRecyclerView(context: Context) {
        binding.recyclerView.adapter = playerAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(
            context,
            2
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun goToThirdFragment(data: Player) {
        val actionToPemainFragment = ListPlayerFragmentDirections.actionListClubFragmentToListPemainFragment()
        actionToPemainFragment.nama = data.nama
        actionToPemainFragment.image = data.image
        findNavController().navigate(actionToPemainFragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    private fun goToFavoriteFragment() {
        findNavController().navigate(R.id.action_listClubFragment_to_favoriteClubFragment)
    }

    override fun onClickClub(data: Player) {
        goToThirdFragment(data)
    }

    private fun refreshData() {
        listPlayerFragmentViewModel.retriveClubData()
    }

    private fun openActivityFoto() {
        startActivity(Intent(this.context, ImageBlurActivity::class.java))
    }


}