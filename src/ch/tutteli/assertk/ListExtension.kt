package ch.tutteli.assertk

import java.util.*

//TODO use KBox as dependency instead
inline fun <T> List<T>.joinToString(separator: String, append: (it: T, sb: StringBuilder) -> Unit)
    = appendToStringBuilder(StringBuilder(size * 4), separator, append).toString()

inline fun <T> List<T>.appendToStringBuilder(
    sb: StringBuilder, separator: String, append: (it: T, sb: StringBuilder) -> Unit) {

    val size = this.size
    if (size > 0) {
        append(this[0], sb)
    }
    for (i in 1 until size) {
        sb.append(separator)
        append(this[i], sb)
    }
}

//TODO use KBox as dependency instead
inline fun <T, T2> List<T>.flatten(getter: (it: T) -> List<T2>): List<T2> {
    val list = ArrayList<T2>(this.size * 2)
    this.forEach {
        list.addAll(getter(it))
    }
    return list
}
