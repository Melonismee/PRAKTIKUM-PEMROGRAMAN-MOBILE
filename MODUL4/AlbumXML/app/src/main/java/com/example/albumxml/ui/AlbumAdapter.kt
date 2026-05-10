package com.example.albumxml.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.albumxml.databinding.ItemAlbumBinding
import com.example.albumxml.model.Album
import com.example.albumxml.viewmodel.AlbumViewModel

class AlbumAdapter(
    private val list: List<Album>,
    private val viewModel: AlbumViewModel,
    private val onDetailClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val album = list[position]

        holder.binding.tvTitle.text = album.title
        holder.binding.tvArtist.text = album.artist
        holder.binding.tvYear.text = album.year

        holder.binding.imgAlbum
            .setImageResource(album.image)

        holder.binding.btnOpen.setOnClickListener {

            viewModel.explicitIntentClicked(album)

            val intent = Intent(
                Intent.ACTION_VIEW,
                album.link.toUri()
            )

            holder.itemView.context
                .startActivity(intent)
        }

        holder.binding.btnDetail.setOnClickListener {

            viewModel.detailButtonClicked(album)

            onDetailClick(album)
        }
    }
}