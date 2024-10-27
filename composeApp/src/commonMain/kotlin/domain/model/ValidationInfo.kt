package domain.model

data class ValidationInfo(
    val success: Boolean,
    val showError: Boolean,
    val messageError: String = ""
)
enum class TypeEdit {
    TEXT, PHONE, EMAIL, SEARCH, FAST_SEARCH
}