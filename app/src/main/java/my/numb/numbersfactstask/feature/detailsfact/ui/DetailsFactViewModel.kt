package my.numb.numbersfactstask.feature.detailsfact.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import my.numb.core.util.BaseViewModel
import my.numb.domain.model.FactAboutNumber
import my.numb.domain.util.Resource

class DetailsFactViewModel : BaseViewModel() {

    private val mutableFactNumberState =
        MutableStateFlow<Resource<FactAboutNumber>>(Resource.Empty())

    val factNumberState: StateFlow<Resource<FactAboutNumber>> = mutableFactNumberState

    fun saveData(aboutNumber: FactAboutNumber) {
        mutableFactNumberState.value = Resource.Success(aboutNumber)
    }
}