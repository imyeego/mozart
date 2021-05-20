package com.imyeego.mozart

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_fourth.*
import kotlin.collections.ArrayList

/**
 * @authur : liuzhao
 * @time   : 5/20/21 10:59 PM
 * @Des    :
 *
 */
class FourthActivity: BaseActivity() {

    var list = ArrayList<String>()
    lateinit var adapter: StringAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_fourth
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        btn_remove.setOnClickListener {
            remove(5, 3)
        }

        btn_add.setOnClickListener {
            add(5, 3)
        }

        btn_merge.setOnClickListener {
            changeData()
        }
        rv.layoutManager = layoutManager
        adapter = StringAdapter(R.layout.item_fourth, list)

        rv.adapter = adapter

    }

    override fun initData() {
        super.initData()

        for (i in 1..20) {
            list.add("init $i")
        }

        adapter.notifyDataSetChanged()
    }

    private fun changeData() {
        remove(5, 3)
        add(5, 1)
    }

    private fun remove(start: Int, size: Int) {
        for (i in 1..size) {
            list.removeAt(start + i)
        }
        adapter.notifyItemRangeRemoved(start, size)
    }

    private fun add(start: Int, size: Int) {
        val subList = ArrayList<String>()
        for (i in 1..size) {
            subList.add("add $i")
        }
        list.addAll(start, subList)
        adapter.notifyItemRangeInserted(start, size)
    }

    class StringAdapter(layoutId: Int, list: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(layoutId, list) {

        override fun convert(helper: BaseViewHolder?, item: String?) {
            helper?.setText(R.id.tv_string, item)
        }
    }

}