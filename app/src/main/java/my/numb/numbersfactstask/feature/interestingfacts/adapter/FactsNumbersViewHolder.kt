package my.numb.numbersfactstask.feature.interestingfacts.adapter

import androidx.recyclerview.widget.RecyclerView
import my.numb.domain.model.FactAboutNumber
import my.numb.numbersfactstask.databinding.NumberFactItemBinding

class FactsNumbersViewHolder(private val binding: NumberFactItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        factAboutNumber: FactAboutNumber,
        onCLick: (FactAboutNumber) -> Unit
    ) {
        binding.numberText.text = factAboutNumber.number.toString()
        binding.factText.text = factAboutNumber.text

        binding.root.setOnClickListener {
            onCLick(factAboutNumber)
        }
    }
}
