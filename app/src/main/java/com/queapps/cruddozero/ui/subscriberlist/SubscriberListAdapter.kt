package com.queapps.cruddozero.ui.subscriberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.queapps.cruddozero.data.db.entity.SubscriberEntity
import com.queapps.cruddozero.databinding.SubscriberItemBinding

class SubscriberListAdapter(
    private val subscriberList: List<SubscriberEntity>
):RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity:SubscriberEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder =
        SubscriberListViewHolder(
            SubscriberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = subscriberList.size

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscriberList[position])
    }


    inner class SubscriberListViewHolder(
        binding: SubscriberItemBinding
    ):RecyclerView.ViewHolder(binding.root){

        private val tvSubscriberName = binding.textSubscriberName
        private val tvSubscriberEmail = binding.textSubscriberEmail
        private val itemView = binding.root


        fun bindView(subscriberEntity: SubscriberEntity){
            tvSubscriberName.text = subscriberEntity.name
            tvSubscriberEmail.text = subscriberEntity.email

            itemView.setOnClickListener {
                onItemClick?.invoke(subscriberEntity)
            }

        }

    }


}