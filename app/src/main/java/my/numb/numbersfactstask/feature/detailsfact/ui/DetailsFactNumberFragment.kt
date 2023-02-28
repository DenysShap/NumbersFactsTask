package my.numb.numbersfactstask.feature.detailsfact.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import my.numb.core.util.viewBinding
import my.numb.domain.util.Resource
import my.numb.numbersfactstask.R
import my.numb.numbersfactstask.databinding.FragmentDetailsFactNumberBinding

class DetailsFactNumberFragment : Fragment(R.layout.fragment_details_fact_number) {

    private val binding: FragmentDetailsFactNumberBinding by viewBinding(
        FragmentDetailsFactNumberBinding::bind
    )

    private val viewModel: DetailsFactViewModel by viewModels()
    private val arguments: DetailsFactNumberFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.saveData(arguments.factAboutNumber)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.factNumberState.collect { resource ->
                    when (resource) {
                        is Resource.Empty -> Unit
                        is Resource.Error -> Unit
                        is Resource.Success -> {
                            binding.factText.text = resource.data?.text
                            binding.numberText.text = resource.data?.number.toString()
                        }
                    }
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}