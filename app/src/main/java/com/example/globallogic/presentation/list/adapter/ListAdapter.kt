package com.example.globallogic.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.globallogic.R
import com.example.globallogic.commons.BaseAdapter
import com.example.globallogic.commons.BindingViewHolder
import com.example.globallogic.databinding.FragmentItemBinding
import com.example.globallogic.domain.ItemResponse
import com.squareup.picasso.Picasso

class ListAdapter(rv: RecyclerView, private val clickListener: ThumbnailListener) : BaseAdapter<ItemResponse, FragmentItemBinding>(rv, R.layout.fragment_item) {

    class ThumbnailListener(val clickListener: (item: ItemResponse) -> Unit) {
        fun onClick(item: ItemResponse) = clickListener(item)
    }

    override fun populateBindViewHolder(
        holder: BindingViewHolder<FragmentItemBinding>?,
        item: ItemResponse?,
        position: Int
    ) {
        if (holder != null && holder.binding != null && item != null) {
            Picasso.get()
                .load(item.image)
                .resize(100, 100)
                .centerCrop()
                .error(R.drawable.ic_baseline_not_interested_24)
                .placeholder(R.drawable.ic_baseline_not_interested_24)
                .into(holder.binding.thumbnail)
            holder.binding.title.text = item.title
            holder.binding.description.text = item.description
            holder.itemView.setOnClickListener {
                clickListener.clickListener(item)
            }
        }
    }

    override fun compareItems(itemA: ItemResponse?, itemB: ItemResponse?): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.title == itemB.title
        }
        return false
    }

    override fun compareItemsContent(itemA: ItemResponse?, itemB: ItemResponse?): Boolean {
        if (itemA != null && itemB != null) {
            return itemA.title.equals(itemB.title)
        }
        return false
    }
}