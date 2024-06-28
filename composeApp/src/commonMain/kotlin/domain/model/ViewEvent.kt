package domain.model

sealed class ViewEvent {

    sealed class Loading {
        object InitialEvent : Loading()
        object ShowLoading : Loading()
        object HideLoading : Loading()
    }

    sealed class Alert {
        object ShowAlert : Alert()
        object HideAlert : Alert()
    }

    data class Data<T>(val data: T) : ViewEvent()

}