package ch.tutteli.assertk

import java.util.*

//TODO use KBox as dependency instead
inline fun <T> List<T>.joinToString(separator: String, append: (it: T, sb: StringBuilder) -> Unit)
    = joinToString(StringBuilder(size * 4), separator, append)

inline fun <T> List<T>.joinToString(
    sb: StringBuilder,
    separator: String,
    append: (it: T, sb: StringBuilder) -> Unit) : String {

    val size = this.size
    if (size > 0) {
        append(this[0], sb)
    }
    for (i in 1 until size) {
        sb.append(separator)
        append(this[i], sb)
    }
    return sb.toString()
}

//TODO use KBox as dependency instead
inline fun <T, T2> List<T>.flatten(getter: (it: T) -> List<T2>): List<T2> {
    val list = ArrayList<T2>(this.size * 2)
    this.forEach {
        list.addAll(getter(it))
    }
    return list
}

inline fun <T, T2> Sequence<T>.flatten(getter: (it: T) -> Sequence<T2>): Sequence<T2> {
    val list = ArrayList<T2>()
    this.forEach {
        list.addAll(getter(it))
    }
    return list.asSequence()
}

