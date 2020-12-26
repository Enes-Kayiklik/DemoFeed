package com.eneskayiklik.demofeed.ui.feed.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.eneskayiklik.demofeed.data.model.feature.Featured
import com.eneskayiklik.demofeed.databinding.OneLineFeaturedBinding

class FeaturedAdapter(
    private val featureList: List<Featured>
) : RecyclerView.Adapter<FeaturedAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int = featureList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = OneLineFeaturedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(featureList[position])
    }

    inner class CustomViewHolder(private val binding: OneLineFeaturedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Featured) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBarLoading.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBarLoading.visibility = View.GONE
                            return false
                        }
                    })
                    .into(this.imgFeaturedPhoto)
            }
        }
    }
}