package ch.tutteli.atrium.core

sealed class Either<out L, out R> {

    inline fun <T> map(f: (R) -> T): Either<L, T> = flatMap { Right(f(it)) }

    inline fun <T> fold(default: (L) -> T, f: (R) -> T): T = when (this) {
        is Right -> f(this.r)
        is Left -> default(this.l)
    }
}

inline fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> = fold({ Left(it) }, f)

data class Left<L>(val l: L) : Either<L, Nothing>()
data class Right<R>(val r: R) : Either<Nothing, R>()
