package my.numb.numbersfactstask.feature.interestingfacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import my.numb.domain.model.FactAboutNumber
import my.numb.numbersfactstask.databinding.NumberFactItemBinding

class FactsNumbersAdapter(private val onItemClick: (FactAboutNumber) -> Unit) :
    ListAdapter<FactAboutNumber, FactsNumbersViewHolder>(FactNumberComparator()) {

    fun setFactsList(factNumbers: List<FactAboutNumber>?) {
        factNumbers?.let {
            submitList(factNumbers)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsNumbersViewHolder {
        val binding = NumberFactItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FactsNumbersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactsNumbersViewHolder, position: Int) {
        val numberFact = getItem(position)
        holder.bind(numberFact, onItemClick)
    }
}