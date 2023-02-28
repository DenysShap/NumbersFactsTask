package my.numb.numbersfactstask.feature.interestingfacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import my.numb.core.util.ProgressInterface
import my.numb.core.util.ProgressInterfaceImpl
import my.numb.domain.model.FactAboutNumber
import my.numb.domain.usecases.*
import my.numb.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class InterestingFactsViewModel @Inject constructor(
    private val getFactNumberUseCase: GetFactNumberUseCase,
    private val getRandomFactNumberUseCase: GetRandomFactNumberUseCase,
    private val getAllFactsNumbersUseCase: GetAllFactsNumbersUseCase,
    private val deleteAllFactsNumbersUseCase: DeleteAllFactsNumbersUseCase,
    private val networkStatusUseCase: NetworkStatusUseCase
) : ViewModel(), ProgressInterface by ProgressInterfaceImpl() {

    init {
        getAllFactsNumbers()
        observeNetworkStatus()
    }

    private val mutableFactNumberState =
        MutableStateFlow<Resource<FactAboutNumber>>(Resource.Empty())
    val factNumberState: StateFlow<Resource<FactAboutNumber>> = mutableFactNumberState

    private val mutableAllFactsNumbersState =
        MutableStateFlow<Resource<List<FactAboutNumber>>>(Resource.Empty())
    val allFactsNumbersState: StateFlow<Resource<List<FactAboutNumber>>> =
        mutableAllFactsNumbersState

    private val mutableNetworkState = MutableStateFlow<Boolean>(true)
    val networkState: StateFlow<Boolean> = mutableNetworkState

    fun getFactNumber(number: Long) {
        viewModelScope.launch {
            showProgress()
            val result = getFactNumberUseCase(number)
            mutableFactNumberState.value = result
            hideProgress()
        }
    }

    fun getRandomFactNumber() {
        viewModelScope.launch {
            showProgress()
            val result = getRandomFactNumberUseCase()
            mutableFactNumberState.value = result
            hideProgress()
        }
    }

    private fun getAllFactsNumbers() {
        viewModelScope.launch {
            getAllFactsNumbersUseCase().collect { listFacts ->
                mutableAllFactsNumbersState.value = listFacts
            }
        }
    }

    fun deleteAllFacts() {
        viewModelScope.launch {
            deleteAllFactsNumbersUseCase()
        }
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkStatusUseCase().collectLatest { networkStatus ->
                mutableNetworkState.value = networkStatus
            }
        }
    }
}