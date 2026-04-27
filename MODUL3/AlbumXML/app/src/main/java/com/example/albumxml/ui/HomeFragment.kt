package com.example.albumxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumxml.R
import com.example.albumxml.data.AlbumData
import com.example.albumxml.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = AlbumData.albums

        val adapter = AlbumAdapter(data) { album ->
            val bundle = Bundle().apply {
                putInt("albumId", album.id)
            }

            findNavController().navigate(
                R.id.detailFragment,
                bundle
            )
        }

        binding.rvFeatured.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeatured.adapter = adapter

        binding.rvAll.layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvAll.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}