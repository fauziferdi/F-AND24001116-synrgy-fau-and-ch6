package com.example.fauzi_chalange_chapter6.ui.detailplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import coil.load
import com.example.fauzi_chalange_chapter6.databinding.FragmentDetailBinding
import com.example.fauzi_chalange_chapter6.MyApplication

class DetailPlayerFragment : Fragment(){
    private lateinit var binding: FragmentDetailBinding

    private val logic by viewModel<DetailPlayerLogicViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logic.error.observe(viewLifecycleOwner){ error ->
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }

        logic.playerLocal.observe(viewLifecycleOwner){ club ->
            if(club != null){
                binding.btnFav.text = "Hapus Pemain Dari Favorit"
                binding.btnFav.setOnClickListener { logic.deleteClubFromFavorite(club) }
            }else{
                binding.btnFav.text = "Tambah Pemain Ke Favorit"
                binding.btnFav.setOnClickListener{
                    logic.saveClubToFavorite(
                        nama = getName(),
                        image = getImage(),
                    )
                }
            }
        }

        logic.insertClub.observe(viewLifecycleOwner){
            Toast.makeText(context, "Pemian Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            binding.btnFav.text = "Hapus Pemain Dari Favorit"
        }

        logic.deleteClub.observe(viewLifecycleOwner){
            Toast.makeText(context, "Pemian Berhasil Dihapus", Toast.LENGTH_SHORT).show()
            binding.btnFav.text = "Tambah Pemain Ke Favorit"
        }

        binding.titleDetail.text= getName()
        binding.imgDetail.load(getImage())
        logic.title = getName()
        binding.btnLink.setOnClickListener {searchClub(getName()) }
        logic.loadClubFromFavorite(getArgId())

    }

    private fun searchClub(title: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(logic.getUrlInternet(title)))
        startActivity(intent)
    }

    private fun getName(): String {
        return getArgs().nama
    }

    private fun getImage(): String {
        return getArgs().image
    }

    private fun getArgId(): Int {
        return getArgs().id
    }

    private fun getArgs(): DetailPlayerFragmentArgs {
        return DetailPlayerFragmentArgs.fromBundle(
            arguments as Bundle
        )
    }



}