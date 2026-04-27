package com.example.albumxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.albumxml.data.AlbumData
import com.example.albumxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = arguments?.getInt("albumId") ?: 0
        val album = AlbumData.albums.find { it.id == id }

        album?.let {
            binding.imgDetail.setImageResource(it.image)
            binding.tvTitle.text = it.title
            binding.tvArtist.text = it.artist
            binding.tvYear.text = it.year
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}