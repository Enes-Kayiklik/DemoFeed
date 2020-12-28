package com.eneskayiklik.demofeed.ui.feed.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.eneskayiklik.demofeed.R
import com.eneskayiklik.demofeed.data.model.timeline.Mention
import com.eneskayiklik.demofeed.data.model.timeline.TimelineX
import com.eneskayiklik.demofeed.databinding.OneLineTimelineBinding
import com.eneskayiklik.demofeed.utils.Functions.getDateAsString

class TimelineAdapter(
    private val onMentionClick: (List<Mention>) -> Unit
) : PagingDataAdapter<TimelineX, TimelineAdapter.CustomViewHolder>(customCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = OneLineTimelineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CustomViewHolder(private val binding: OneLineTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(timeline: TimelineX?) {
            val overlapAdapter = OverlapAdapter(3, 50)
            binding.apply {
                timeline?.let { data ->
                    tvFeedTitle.text = data.title
                    tvFeedDesc.text = "${data.countryCount} ".plus(
                        this.root.context.resources.getString(
                            R.string.countries
                        )
                    )
                    tvFeedDaysAgo.text = data.date.toLong().getDateAsString()
                    recyclerViewLiked.adapter = overlapAdapter
                    overlapAdapter.addAll(data.mentions)
                    overlapAdapter.addAnimation = true
                    root.setOnClickListener {
                        if (data.mentions.isNotEmpty())
                            onMentionClick(data.mentions)
                    }
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
                        }).into(this.imgFeedPhoto)
                }
            }
        }
    }

    companion object {
        private val customCallBack = object : DiffUtil.ItemCallback<TimelineX>() {
            override fun areItemsTheSame(oldItem: TimelineX, newItem: TimelineX): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TimelineX, newItem: TimelineX): Boolean {
                return oldItem == newItem
            }
        }
    }
}