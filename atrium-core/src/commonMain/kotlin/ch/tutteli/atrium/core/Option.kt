package ch.tutteli.atrium.core

/**
 * Represents an optional value with [map], [flatMap], [fold] and [getOrElse] to transform it.
 */
sealed class Option<out T> {

    fun isDefined() = this is Some

    inline fun filter(predicate: (T) -> Boolean): Option<T> = flatMap { if (predicate(it)) Some(it) else None }

    inline fun <R> map(f: (T) -> R): Option<R> = flatMap { Some(f(it)) }

    inline fun <R> flatMap(f: (T) -> Option<R>): Option<R> = fold({ None }, f)

    inline fun <R> fold(default: () -> R, f: (T) -> R): R = when (this) {
        is Some -> f(value)
        None -> default()
    }

    companion object {
        inline fun <T> someIf(predicate: Boolean, provider: () -> T): Option<T> =
            if (predicate) Some(provider()) else None
    }
}

inline fun <T> Option<T>.getOrElse(default: () -> T): T = fold(default) { it }

/**
 * Represents a present value in terms of [Option].
 */
data class Some<T>(val value: T) : Option<T>()

/**
 * Represents an absent value in terms of [Option].
 */
object None : Option<Nothing>()
