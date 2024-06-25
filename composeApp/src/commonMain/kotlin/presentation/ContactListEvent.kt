package presentation

import domain.model.ContactDom


sealed interface ContactListEvent {
    object OnAddNewContactClick: ContactListEvent
    object DismissContact: ContactListEvent
    data class OnFirstNameChanged(val value: String): ContactListEvent
    data class OnLastNameChanged(val value: String): ContactListEvent
    data class OnEmailChanged(val value: String): ContactListEvent
    data class OnPhoneNumberChanged(val value: String): ContactListEvent
    class OnPhotoPicked(val bytes: ByteArray): ContactListEvent
    object OnAddPhotoClicked: ContactListEvent
    object SaveContact: ContactListEvent
    data class SelectContact(val contact: ContactDom): ContactListEvent
    data class EditContact(val contact: ContactDom): ContactListEvent
    object DeleteContact: ContactListEvent
}