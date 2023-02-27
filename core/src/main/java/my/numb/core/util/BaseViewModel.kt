package my.numb.core.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {

    private val mutableProgressBarState =
        MutableStateFlow<Progress>(Progress.HideProgress)

    val progressBarState: StateFlow<Progress> = mutableProgressBarState

    fun showProgress() {
        mutableProgressBarState.value = Progress.ShowProgress
    }

    fun hideProgress() {
        mutableProgressBarState.value = Progress.HideProgress
    }
}