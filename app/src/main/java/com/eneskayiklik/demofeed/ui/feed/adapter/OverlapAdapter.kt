package com.eneskayiklik.demofeed.ui.feed.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneskayiklik.demofeed.data.model.timeline.Mention
import com.eneskayiklik.demofeed.databinding.OneRowOvelapImageBinding
import com.mindinventory.overlaprecylcerview.adapters.OverlapRecyclerViewAdapter
import com.mindinventory.overlaprecylcerview.utils.TextDrawable

class OverlapAdapter(
    overlapLimit: Int,
    overlapWidthInPercentage: Int
) : OverlapRecyclerViewAdapter<Mention, OverlapAdapter.CustomViewHolder>(
    overlapLimit,
    overlapWidthInPercentage
) {

    inner class CustomViewHolder(private val binding: OneRowOvelapImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mention: Mention?) {
            mention?.let {
                if (isLastVisibleItemItem(bindingAdapterPosition)) {
                    val drawable = TextDrawable.builder()
                        .beginConfig()
                        .textColor(Color.WHITE)
                        .width(28)
                        .height(28)
                        .endConfig()
                        .buildRound("+${notVisibleItems.size}", Color.TRANSPARENT)
                    binding.imgFeedMention.setImageDrawable(drawable)
                    true
                } else {
                    Glide.with(binding.root.context)
                        .load(mention.profileImage)
                        .into(binding.imgFeedMention)
                    true
                }
            }
        }
    }

    override fun bindItemViewHolder(viewHolder: CustomViewHolder, position: Int) {
        viewHolder.bind(getVisibleItemAt(position))
    }

    override fun createItemViewHolder(parent: ViewGroup): CustomViewHolder {
        return CustomViewHolder(
            OneRowOvelapImageBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
}