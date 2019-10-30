package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.startsWith
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Showcase for using ExpectImpl.changeSubject
 */
object EitherSpec : Spek({

    describe("custom Either<String, Int> with Left \"hello\"") {
        val either: Either<String, Int> =
            Left("hello")
        it("isLeft does not throw") {
            expect(either).isLeft { startsWith("h") }
        }
        it("isRight throws AssertionError containing explanation") {
            expect {
                expect(either).isRight { isLessThan(2) }
            }.toThrow<AssertionError> {
                messageContains(
                    "is a: ${Right::class.simpleName}",
                    "${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 2"
                )
            }
        }
    }

    describe("custom Either<String, Int> with Right 1") {
        val either: Either<String, Int> =
            Right(1)
        it("isLeft does not throw") {
            expect {
                expect(either).isLeft { startsWith("h") }
            }.toThrow<AssertionError> {
                messageContains(
                    "is a: ${Left::class.simpleName}",
                    "${DescriptionCharSequenceAssertion.STARTS_WITH.getDefault()}: \"h\""
                )
            }
        }
        it("isRight throws AssertionError containing explanation") {
            expect(either).isRight { isLessThan(2) }
        }
    }
})

fun <A, B> Expect<Either<A, B>>.isLeft(): Expect<A> = changeToLeft().getExpectOfFeature()
fun <A, B> Expect<Either<A, B>>.isLeft(assertionCreator: Expect<A>.() -> Unit) =
    changeToLeft().addToInitial(assertionCreator)

private fun <A, B> Expect<Either<A, B>>.changeToLeft(): ChangedSubjectPostStep<Either<A, B>, A> {
    return ExpectImpl.changeSubject(this).reportBuilder()
        .withDescriptionAndRepresentation("is a", RawString.create("Left"))
        .withCheck { it.isLeft() }
        .withTransformation { (it as Left).a }
        .build()
}

fun <A, B> Expect<Either<A, B>>.isRight(): Expect<B> = changeToRight().getExpectOfFeature()
fun <A, B> Expect<Either<A, B>>.isRight(assertionCreator: Expect<B>.() -> Unit) =
    changeToRight().addToInitial(assertionCreator)

private fun <A, B> Expect<Either<A, B>>.changeToRight(): ChangedSubjectPostStep<Either<A, B>, B> {
    return ExpectImpl.changeSubject(this).reportBuilder()
        .withDescriptionAndRepresentation("is a", RawString.create("Right"))
        .withCheck { it.isRight() }
        .withTransformation { (it as Right).b }
        .build()
}

/** copied and simplified from
 *  https://github.com/arrow-kt/arrow/blob/master/arrow-core/src/main/kotlin/arrow/core/Either.kt
 */
sealed class Either<out A, out B> {
    abstract fun isLeft(): Boolean

    abstract fun isRight(): Boolean
    inline fun <C> fold(crossinline fa: (A) -> C, crossinline fb: (B) -> C): C = when (this) {
        is Right<A, B> -> fb(b)
        is Left<A, B> -> fa(a)
    }
}

@Suppress("DataClassPrivateConstructor")
data class Left<out A, out B> private constructor(val a: A) : Either<A, B>() {
    override fun isLeft() = true
    override fun isRight() = false

    companion object {
        operator fun <A> invoke(a: A): Either<A, Nothing> =
            Left(a)
    }
}

@Suppress("DataClassPrivateConstructor")
data class Right<out A, out B> private constructor(val b: B) : Either<A, B>() {
    override fun isLeft() = false
    override fun isRight() = true

    companion object {
        operator fun <B> invoke(b: B): Either<Nothing, B> =
            Right(b)
    }
}
