package my.numb.core.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProgressInterfaceImpl : ProgressInterface {

    private val mutableProgressBarState =
        MutableStateFlow<Progress>(Progress.HideProgress)

    override val progressBarState: StateFlow<Progress> = mutableProgressBarState

    override fun showProgress() {
        mutableProgressBarState.value = Progress.ShowProgress
    }

    override fun hideProgress() {
        mutableProgressBarState.value = Progress.HideProgress
    }
}