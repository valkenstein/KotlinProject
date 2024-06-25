package domain.model

class ContactDom(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val image: ByteArray?,
)