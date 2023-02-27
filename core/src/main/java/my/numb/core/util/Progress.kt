package my.numb.core.util

sealed class Progress {
    object ShowProgress : Progress()
    object HideProgress : Progress()
}