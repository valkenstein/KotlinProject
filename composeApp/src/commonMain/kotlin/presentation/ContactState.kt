package presentation

import domain.model.ContactDom


data class ContactListState(
    val contacts: List<ContactDom> = emptyList(),
    val recentlyAddedContacts: List<ContactDom> = emptyList(),
    val selectedContact: ContactDom? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)