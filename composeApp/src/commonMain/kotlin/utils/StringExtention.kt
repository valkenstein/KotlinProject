package utils

import domain.model.TypeEdit
import domain.model.ValidationInfo

fun String.formattedNumber(): String {
    var str = ""
    val rootString = this.replace("₽", "").replace(" ", "")
    if (rootString.isEmpty()) return str
    rootString.reversed().forEachIndexed { index, c ->
        if (index % 3 == 0)
            str += " $c"
        else
            str += c
    }
    return str.reversed().trim()
}

fun String.validationField(
    type: TypeEdit,
    isNeedFull: Boolean = false
): ValidationInfo {
    val txt = this
    return when (type) {
        TypeEdit.TEXT -> {
            val errorMessage =
                if (isNeedFull && txt.isEmpty()) "это поле не может быть пустым" else if (txt.checkCharacters()) "недопустимые символы" else ""
            ValidationInfo(
                success = errorMessage.isEmpty(),
                showError = txt.checkCharacters(),
                messageError = errorMessage
            )
        }

        TypeEdit.PHONE -> ValidationInfo(txt.length > 17, false)
        TypeEdit.EMAIL -> {
            val errorMessage =
                if (isNeedFull && txt.isEmpty()) "это поле не может быть пустым" else if (txt.contains(
                        "@"
                    ) && txt.contains(".")
                ) "" else "email неверный"
            ValidationInfo(
                errorMessage.isEmpty(),
                errorMessage.isEmpty(),
                messageError = errorMessage
            )
        }

        TypeEdit.SEARCH -> ValidationInfo(txt.isNotEmpty(), txt.checkCharacters())
        TypeEdit.FAST_SEARCH -> ValidationInfo(txt.isNotEmpty(), txt.checkCharacters())
    }
}

fun String.checkCharacters(): Boolean {
    return this.contains("[\\d~!@#\$%^&*+./&*)(,]".toRegex())
}

fun String.toPrice(): String {
    if (this.isEmpty()) return ""
    if (this.contains('₽')) return this
    if (last() == '%') return this
    var str = ""
    val rootString = this.replace("₽", "").replace(" ", "")
    if (rootString.isEmpty()) return str
    rootString.reversed().forEachIndexed { index, c ->
        if (index % 3 == 0)
            str += " $c"
        else
            str += c
    }
    return "${str.reversed()}₽"
}
fun String.decliningInvait(): String {
    var finishedResult = ""
    val str = this
    if (str.isEmpty()) return ""
    val lastValue = str.last().toString().toIntOrNull() ?: 0
    finishedResult = if (str.length > 1) {
        val penultimateValue = str[str.length - 2].toString().toInt()
        if (lastValue in 2..4 && penultimateValue != 1)
            "$this инвайта"
        else if (lastValue == 1 && penultimateValue != 1)
            "$this инвайт"
        else "$this инвайтов"
    } else {
        when (lastValue) {
            in 2..4 -> "$this инвайта"
            1 -> "$this инвайт"
            else -> "$this инвайтов"
        }
    }
    return finishedResult
}