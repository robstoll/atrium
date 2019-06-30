package ch.tutteli.atrium.core

/**
 * Represents an optional value with [map], [flatMap], [fold] and [getOrElse] to transform it.
 */
sealed class Option<out T> {

    fun isDefined() = this is Some

    inline fun <R> map(f: (T) -> R): Option<R> = flatMap { Some(f(it)) }

    inline fun <R> flatMap(f: (T) -> Option<R>): Option<R> = fold({ None }, f)

    inline fun <R> fold(default: () -> R, f: (T) -> R): R = when (this) {
        is Some -> f(value)
        None -> default()
    }
}

inline fun <T> Option<T>.getOrElse(default: () -> T): T = fold(default) { it }

data class Some<T>(val value: T) : Option<T>()
object None : Option<Nothing>()
