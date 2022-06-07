package com.template

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.databinding.ImageLayoutBinding

class PicturesAdapter(private val context: Context) :
    RecyclerView.Adapter<PicturesAdapter.PicViewHolder>() {

    var wallPapers: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class PicViewHolder(val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PicViewHolder(
        ImageLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        val image = wallPapers[position]
        holder.binding.imageView.setImageDrawable(
            Drawable.createFromStream(context.assets.open("wallpapers/$image"), null)!!
        )
    }

    override fun getItemCount() = wallPapers.size
}