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
import com.eneskayiklik.demofeed.R
import com.eneskayiklik.demofeed.data.model.timeline.TimelineX
import com.eneskayiklik.demofeed.databinding.OneLineFeedBinding
import com.eneskayiklik.demofeed.utils.Functions.getDateAsString

class FeedAdapter(
    private val timeline: List<TimelineX>
) : RecyclerView.Adapter<FeedAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int = timeline.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = OneLineFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(timeline[position])
    }

    inner class CustomViewHolder(private val binding: OneLineFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TimelineX) {
            binding.apply {
                tvFeedTitle.text = data.title
                tvFeedDesc.text = "${data.countryCount} ".plus(
                    this.root.context.resources.getString(
                        R.string.countries
                    )
                )
                tvFeedDaysAgo.text = data.date.toLong().getDateAsString()
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBarLoadingFeed.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBarLoadingFeed.visibility = View.GONE
                            return false
                        }
                    })
                    .into(this.imgFeedPhoto)
            }
        }
    }
}