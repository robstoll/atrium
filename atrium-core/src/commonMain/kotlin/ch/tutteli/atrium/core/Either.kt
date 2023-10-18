package ch.tutteli.atrium.core

/**
 * Represents a disjoint union i.e. a type with two possibilities, either [Left] or [Right].
 *
 * Provides a [Right] biased [map] and [flatMap] function as well as a few others.
 */
sealed class Either<out L, out R> {

    /**
     * Maps over the [Right] side of this [Either].
     */
    inline fun <T> map(f: (R) -> T): Either<L, T> = flatMap { Right(f(it)) }

    /**
     * fold this.
     */
    inline fun <T> fold(fL: (L) -> T, fR: (R) -> T): T = when (this) {
        is Right -> fR(this.r)
        is Left -> fL(this.l)
    }

    /**
     * Turns this [Either] into an [Option], the [Right] side becomes [Some] the left side is ignored and turned
     * into [None].
     */
    fun toOption(): Option<R> = if (this is Right) Some(this.r) else None
}

/**
 * flat-map over the [Right] side of this [Either].
 */
inline fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> = fold({ Left(it) }, f)

/**
 * The left case of an [Either].
 */
data class Left<L>(val l: L) : Either<L, Nothing>()

/**
 * The right case of an [Either].
 */
data class Right<R>(val r: R) : Either<Nothing, R>()
