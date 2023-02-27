package my.numb.numbersfactstask.feature.interestingfacts.adapter

import androidx.recyclerview.widget.DiffUtil
import my.numb.domain.model.FactAboutNumber

class FactNumberComparator : DiffUtil.ItemCallback<FactAboutNumber>() {

    override fun areItemsTheSame(oldItem: FactAboutNumber, newItem: FactAboutNumber): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FactAboutNumber, newItem: FactAboutNumber): Boolean =
        oldItem == newItem
}