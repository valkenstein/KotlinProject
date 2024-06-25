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
