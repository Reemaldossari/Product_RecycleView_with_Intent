package com.example.hw_product_recycleview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.hw_product_recycleview.Productdetails
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_product_recycleview.R
import com.example.hw_product_recycleview.model.Product
import com.example.hw_product_recycleview.objects.Detail

class ItemAdapter(private val dataSet : List<Product> , private val context: Context)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {



    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {


        val phoneName: TextView = view.findViewById(R.id.item_name)
        val image: ImageView = view.findViewById(R.id.item_image)
        val vip: ImageView = view.findViewById(R.id.star)
        val price: TextView = view.findViewById(R.id.item_price)
        val buy: Button = view.findViewById(R.id.buy)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]

        holder.image?.setImageResource(item.productImage)
        holder.phoneName?.text = context.getString(item.productNameId)
        holder.price?.text = context.getString(item.productPrice)
        if (item.isVip) {
            holder.vip.visibility = View.VISIBLE
        }
        //to check the quantity if it more than 0 it's will go to new screen to show the phone name , quantity , image , price
        holder.buy.setOnClickListener {
            if (item.quantity > 0) {
                val intent= Intent(context, Productdetails::class.java )
                intent.putExtra(Detail.PhoneName, context?.getString(item.productNameId))
                intent.putExtra(Detail.PhoneImage, item.productImage)
                it?.context?.startActivity(intent)
            } else {
                Toast.makeText(context, "The item is out of stock", Toast.LENGTH_SHORT).show()
            }
        }


    }
}