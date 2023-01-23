package dev.abdujabbor.db_browser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abdujabbor.db_browser.databinding.MyRvItemBinding
import dev.abdujabbor.db_browser.model.Uset
import java.util.*
import kotlin.collections.ArrayList


class MyRvAdapter(var list: ArrayList<Uset>, var rvAction: RvAction) : RecyclerView.Adapter<MyRvAdapter.VH>() {

    inner class VH(var itemRV: MyRvItemBinding) : RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(user: Uset,position: Int) {
            itemRV.myName.text = user.name
            itemRV.myNumber.text = user.number

            itemRV.imageMenu.setOnClickListener {
                rvAction.showPopupMenu(it,position, user)
            }
            itemRV.call.setOnClickListener {
                rvAction.click(position,user)
            }
            itemRV.sms.setOnClickListener {
                rvAction.clicksms(position,user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MyRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}

interface RvAction{
    fun showPopupMenu(view:View,position: Int, user:Uset)
    fun click(position: Int, user:Uset)
    fun clicksms(position: Int, user:Uset)
}

