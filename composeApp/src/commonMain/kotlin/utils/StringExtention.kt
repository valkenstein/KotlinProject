package utils

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