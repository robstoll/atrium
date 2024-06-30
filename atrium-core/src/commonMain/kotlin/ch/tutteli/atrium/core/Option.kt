package ch.tutteli.atrium.core

/**
 * Represents an optional value with [map], [flatMap], [fold] and [getOrElse] to transform it.
 */
sealed class Option<out T> {

    /**
     * @returns true if this [Option] is [Some].
     */
    fun isDefined() = this is Some

    /**
     * Filters the value if defined by the given [predicate].
     */
    inline fun filter(predicate: (T) -> Boolean): Option<T> = flatMap { if (predicate(it)) Some(it) else None }

    /**
     * Maps over the value if defined.
     */
    inline fun <R> map(f: (T) -> R): Option<R> = flatMap { Some(f(it)) }

    /**
     * Flat-maps over the value if defined.
     */
    inline fun <R> flatMap(f: (T) -> Option<R>): Option<R> = fold({ None }, f)

    /**
     * Folds this [Option].
     */
    inline fun <R> fold(default: () -> R, f: (T) -> R): R = when (this) {
        is Some -> f(value)
        None -> default()
    }

    /**
     * Executed the given function [f] in case this [Option] is defined.
     */
    inline fun ifDefined(f: (T) -> Unit) = fold({}, f)

    companion object {
        /**
         * Factory method to create an [Option].
         * @return [Some] wrapping the value given by the [provider] if the predicate holds, [None] otherwise.
         */
        inline fun <T> someIf(predicate: Boolean, provider: () -> T): Option<T> =
            if (predicate) Some(provider()) else None
    }
}

/**
 * Get the value of this [Option] if defined or use the [default]-value provider.
 */
inline fun <T> Option<T>.getOrElse(default: () -> T): T = fold(default) { it }

/**
 * Represents a present value in terms of [Option].
 */
data class Some<T>(val value: T) : Option<T>()

/**
 * Represents an absent value in terms of [Option].
 */
//TODO 3.0.0 (once we require at least kotlin 1.9) switch to data object
object None : Option<Nothing>() {
    override fun toString(): String = "None"
}
