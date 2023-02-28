package my.numb.core.util

import kotlinx.coroutines.flow.StateFlow

interface ProgressInterface {

    val progressBarState: StateFlow<Progress>

    fun showProgress()

    fun hideProgress()
}