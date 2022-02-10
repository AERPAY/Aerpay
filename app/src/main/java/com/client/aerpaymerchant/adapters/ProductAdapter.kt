package com.client.aerpaymerchant.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.client.aerpaymerchant.Activities.ProductInsightActivity
import com.client.aerpaymerchant.R
import com.client.aerpaymerchant.databinding.ItemProductBinding
import com.client.aerpaymerchant.model.ProductDetails
import com.client.aerpaymerchant.network.APIConstants
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductAdapter(
    val activity: Activity,
    private val list: ArrayList<ProductDetails> = arrayListOf(),
   val  onDelete : (Int,String) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyView>() {
    private lateinit var dialog: BottomSheetDialog

    // View Holder class which
    // extends RecyclerView.ViewHolder
    inner class MyView
    // parameterised constructor for View Holder class
    // which takes the view as a parameter
        (view: ItemProductBinding) : RecyclerView.ViewHolder(view.root) {
        // Text View
        var tvname : TextView? = view.tvName
        var ivproduct : ImageView? = view.imgItem
        var ivmenu : ImageView? = view.imgMenu
        var ivDesc : TextView? = view.tvDesc
        var tvprice: Button? = view.btnPrice
    }


    fun setList(mlist: ArrayList<ProductDetails>){
        list.clear();
        list.addAll(mlist);
        notifyDataSetChanged()
    }
    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {

        // Inflate item.xml using LayoutInflator
        val itemView = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
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

        val model = list[position]

        if (model.productImage != null && model.productImage.size > 0)
        Glide.with(activity)
            .load(APIConstants.BASE_URL+model.productImage[0].image)
            .thumbnail(0.5f)
            .error(R.drawable.splash_logo)
            .into(holder.ivproduct!!);
        holder.tvname?.text = model.product.title
        holder.ivDesc?.text = model.product.description
        holder.tvprice?.text = "Rs ${model.product.price}"
        holder.ivmenu?.setOnClickListener {

            showMenu(holder.adapterPosition)
        }
        // Set the text of each item of
        // Recycler view with the list items

    }
    private fun showMenu(pos: Int) {
        val view: View = activity.layoutInflater.inflate(R.layout.menu_layout, null)
        val mProductInsightTv = view.findViewById<View>(R.id.mProductInsightTv) as TextView
        val TvDelete = view.findViewById<View>(R.id.tv_delete) as TextView
        mProductInsightTv.setOnClickListener {
            activity.startActivity(
                Intent(
                    activity,
                    ProductInsightActivity::class.java
                )
            )
        }

        TvDelete.setOnClickListener {
            onDelete.invoke(pos, list[pos].product.id);
        }
        dialog = BottomSheetDialog(activity)
        dialog.setContentView(view)
        dialog.show()
    }
    // Override getItemCount which Returns
    // the length of the RecyclerView.
    override fun getItemCount(): Int {
        return list.size
    }

    fun removePos(pos : Int) {

        list.removeAt(pos)
        notifyItemRemoved(pos)
        dialog?.dismiss()
    }


}