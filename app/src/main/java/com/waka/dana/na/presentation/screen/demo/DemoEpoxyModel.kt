package com.waka.dana.na.presentation.screen.demo

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.ItemDemoBinding

/**
 * Created by hvngoc on 7/29/22
 */

@EpoxyModelClass(layout = R.layout.item_demo)
abstract class DemoEpoxyModel :
    EpoxyModelWithHolder<DemoEpoxyModel.ChildHolder>() {

    @EpoxyAttribute
    var text: String? = null

    override fun bind(holder: ChildHolder) {
        holder.binding.text.text = text
    }

    class ChildHolder : EpoxyHolder() {

        lateinit var binding: ItemDemoBinding

        override fun bindView(itemView: View) {
            binding = ItemDemoBinding.bind(itemView)
        }
    }
}