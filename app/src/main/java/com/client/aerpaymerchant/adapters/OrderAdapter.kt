package com.client.aerpaymerchant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.client.aerpaymerchant.databinding.ItemOrderBinding
import com.client.aerpaymerchant.model.OrderDetailsModel

class OrderAdapter(
    private val list: ArrayList<OrderDetailsModel> = arrayListOf(),
   val onclick : (OrderDetailsModel) -> Unit
) : RecyclerView.Adapter<OrderAdapter.MyView>() {
    // View Holder class which
    // extends RecyclerView.ViewHolder

    fun setList(mlist: ArrayList<OrderDetailsModel>){
        list.clear();
        list.addAll(mlist);
        notifyDataSetChanged()
    }
    inner class MyView  // parameterised constructor for View Holder class
    // which takes the view as a parameter
        (view: ItemOrderBinding) : RecyclerView.ViewHolder(view.root) {
        // Text View
        var tvName: TextView? = view.tvOrderId
        var tvPrice: TextView? = view.tvPrice
        var tvTime: TextView? = view.tvTime
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {

        // Inflate item.xml using LayoutInflator
        val itemView =
            ItemOrderBinding.inflate(LayoutInflater
            .from(parent.context),
                parent,
                false
            )

        // return itemView
        return MyView(itemView)
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {

        val order =list[position]
        holder.tvName?.text = "Order#${order.order.id}"
        holder.tvPrice?.text = "${order.order.total}"
        holder.tvTime?.text = order.order.created.substringAfter(" ")
        // Set the text of each item of
        // Recycler view with the list items

        holder.itemView.setOnClickListener {
            onclick.invoke(list[holder.adapterPosition])
        }

    }



    // Override getItemCount which Returns
    // the length of the RecyclerView.
    override fun getItemCount(): Int {
//        return 6
        return list.size
    }
}