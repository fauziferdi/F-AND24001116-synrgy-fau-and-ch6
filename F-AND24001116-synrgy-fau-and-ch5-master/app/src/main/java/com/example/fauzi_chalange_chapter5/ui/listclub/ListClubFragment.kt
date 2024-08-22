package com.example.fauzi_chalange_chapter5.ui.listclub
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fauzi_chalange_chapter5.databinding.FragmentListBinding
import com.example.fauzi_chalange_chapter5.ui.listclub.adapter.ClubAdapter
import com.example.fauzi_chalange_chapter5.ui.listclub.adapter.ClubAdapterListener
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club
import com.example.fauzi_chalange_chapter5.R

class ListClubFragment : Fragment(), ClubAdapterListener {

    private lateinit var binding: FragmentListBinding
    private val clubAdapter = ClubAdapter(this)

    private val listClubFragmentViewModel by viewModels<com.example.fauzi_chalange_chapter5.ui.listclub.ListClubFragmentViewModel>(
        factoryProducer = {
            com.example.fauzi_chalange_chapter5.ui.listclub.ListClubFragmentViewModel.provideFactory(
                owner = this,
                context = requireActivity().applicationContext,
            )
        },
    )


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
        listClubFragmentViewModel.clubs.observe(viewLifecycleOwner) { clubs->
            clubAdapter.submitList(clubs)
        }
        listClubFragmentViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.btnToFav.setOnClickListener { goToFavoriteFragment() }
    }


    private fun setupRecyclerView(context: Context) {
        binding.recyclerView.adapter = clubAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(
            context,
            2
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun goToThirdFragment(data: Club) {
        val actionToPemainFragment = ListClubFragmentDirections.actionListClubFragmentToListPemainFragment()
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

    override fun onClickClub(data: Club) {
        goToThirdFragment(data)
    }

    private fun refreshData() {
        listClubFragmentViewModel.retriveClubData()
    }



}