package ch.tutteli.assertk

//TODO use KBox as dependency instead
inline fun <T> List<T>.joinToString(separator: String, append: (it: T, sb: StringBuilder) -> Unit): String
    = joinToString(StringBuilder(size * 4), separator, append)

inline fun <T> List<T>.joinToString(
    sb: StringBuilder,
    separator: String,
    append: (it: T, sb: StringBuilder) -> Unit): String {

    val size = this.size
    if (size > 0) {
        append(this[0], sb)
    }
    for (i in 1..size - 1) {
        sb.append(separator)
        append(this[i], sb)
    }
    return sb.toString()
}

