package com.eneskayiklik.demofeed.ui.feed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneskayiklik.demofeed.data.model.timeline.Mention
import com.eneskayiklik.demofeed.databinding.OneRowMentionBinding

class MentionAdapter(
    private val mentionList: List<Mention>
) : RecyclerView.Adapter<MentionAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int = mentionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            OneRowMentionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(mentionList[position], position)
    }

    inner class CustomViewHolder(private val binding: OneRowMentionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mention: Mention, position: Int) {
            binding.apply {
                tvOneRowMentionFullName.text = mention.fullName
                tvOneRowMentionUserName.text = mention.userName
                if (!mention.isFollowing)
                    btnOneRowMentionFollow.visibility = View.GONE
                if (position == 0)
                    separator.visibility = View.GONE
                Glide.with(this.root).load(mention.profileImage)
                    .into(icOneRowMention)
            }
        }
    }
}