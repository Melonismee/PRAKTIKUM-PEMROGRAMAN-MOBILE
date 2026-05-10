package com.example.albumxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.albumxml.databinding.FragmentDetailBinding
import com.example.albumxml.viewmodel.AlbumViewModel
import com.example.albumxml.viewmodel.AlbumViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumViewModel
            by activityViewModels {
                AlbumViewModelFactory("Ibnu")
            }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentDetailBinding.inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.selectedAlbum.collect { album ->

                album?.let {

                    Timber.d(
                        "Masuk ke halaman detail: ${it.title}"
                    )

                    binding.imgDetail
                        .setImageResource(it.image)

                    binding.tvTitle.text = it.title
                    binding.tvArtist.text = it.artist
                    binding.tvYear.text = it.year
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}