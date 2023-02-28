package my.numb.numbersfactstask.feature.interestingfacts.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.numb.core.util.*
import my.numb.domain.model.FactAboutNumber
import my.numb.domain.util.Resource
import my.numb.numbersfactstask.R
import my.numb.numbersfactstask.databinding.FragmentInterestingFactNumbersBinding
import my.numb.numbersfactstask.feature.interestingfacts.adapter.FactsNumbersAdapter

@AndroidEntryPoint
class InterestingFactsNumbersFragment : Fragment(R.layout.fragment_interesting_fact_numbers) {
    private val binding: FragmentInterestingFactNumbersBinding by viewBinding(
        FragmentInterestingFactNumbersBinding::bind
    )
    private val viewModel: InterestingFactsViewModel by viewModels()
    private lateinit var factsNumbersAdapter: FactsNumbersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factsNumbersAdapter = FactsNumbersAdapter { factAboutNumber ->
            openDetailsScreen(factAboutNumber)
        }
        binding.factsRecycler.adapter = factsNumbersAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.factNumberState.collect { resource ->
                    when (resource) {
                        is Resource.Error -> binding.errorContainer.root.show()
                        else -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.progressBarState.collect { progressState ->
                    when (progressState) {
                        Progress.ShowProgress -> binding.progressView.root.show()
                        Progress.HideProgress -> binding.progressView.root.hide()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allFactsNumbersState.collect { resource ->
                    when (resource) {
                        is Resource.Success -> factsNumbersAdapter.setFactsList(resource.data)
                        is Resource.Error -> Unit
                        is Resource.Empty -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.networkState.collect { networkStatus ->
                    when (networkStatus) {
                        false -> binding.noInternetContainer.root.show()
                        true -> binding.noInternetContainer.root.hide()
                    }
                }
            }
        }

        binding.getFactButton.setOnClickListener {
            val enteredNumber = binding.inputField.text.toString().toLongOrNull()
            when (enteredNumber != null) {
                true -> viewModel.getFactNumber(enteredNumber)
                false -> context?.showAlertDialog()
            }
            hideKeyboard()
        }

        binding.getRandomFactButton.setOnClickListener {
            viewModel.getRandomFactNumber()
        }

        binding.headerDeleteImage.setOnClickListener {
            viewModel.deleteAllFacts()
        }

        binding.errorContainer.tryAgainButton.setOnClickListener {
            binding.errorContainer.root.hide()
        }
    }

    private fun openDetailsScreen(factAboutNumber: FactAboutNumber) {
        val action =
            InterestingFactsNumbersFragmentDirections.actionInterestingFactsNumbersToDetailsFactNumber(
                factAboutNumber
            )
        findNavController().navigate(action)
    }
}