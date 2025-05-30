//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toStartWith
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Showcase for using _logic.extractFeature
 */
object EitherSpec : Spek({

    describe("custom Either<String, Int> with Left \"hello\"") {
        val either: Either<String, Int> =
            Left("hello")
        it("toBeLeft does not throw") {
            expect(either).toBeLeft { toStartWith("h") }
        }
        it("toBeRight throws AssertionError containing explanation") {
            expect {
                expect(either).toBeRight { toBeLessThan(2) }
            }.toThrow<AssertionError> {
                messageToContain(
                    "value of Right: ❗❗ is not a Right",
                    "${DescriptionComparableExpectation.TO_BE_LESS_THAN.getDefault()}: 2"
                )
            }
        }
    }

    describe("custom Either<String, Int> with Right 1") {
        val either: Either<String, Int> =
            Right(1)
        it("toBeLeft does not throw") {
            expect {
                expect(either).toBeLeft { toStartWith("h") }
            }.toThrow<AssertionError> {
                messageToContain(
                    "value of Left: ❗❗ is not a Left",
                    "${DescriptionCharSequenceExpectation.TO_START_WITH.getDefault()}: \"h\""
                )
            }
        }
        it("toBeRight throws AssertionError containing explanation") {
            expect(either).toBeRight { toBeLessThan(2) }
        }
    }
})

fun <A, B> Expect<Either<A, B>>.toBeLeft(): Expect<A> = extractLeft().transform()
fun <A, B> Expect<Either<A, B>>.toBeLeft(assertionCreator: Expect<A>.() -> Unit) =
    extractLeft().collectAndAppend(assertionCreator)

private fun <A, B> Expect<Either<A, B>>.extractLeft(): FeatureExtractorBuilder.ExecutionStep<Either<A, B>, A> =
    _logic.extractFeature
        .withDescription("value of Left")
        .withRepresentationForFailure(Text("❗❗ is not a Left"))
        .withFeatureExtraction {
            if (it is Left) Some(it.a) else None
        }
        .withoutOptions()
        .build()

fun <A, B> Expect<Either<A, B>>.toBeRight(): Expect<B> = extractRight().transform()
fun <A, B> Expect<Either<A, B>>.toBeRight(assertionCreator: Expect<B>.() -> Unit) =
    extractRight().collectAndAppend(assertionCreator)

private fun <A, B> Expect<Either<A, B>>.extractRight(): FeatureExtractorBuilder.ExecutionStep<Either<A, B>, B> =
    _logic.extractFeature
        .withDescription("value of Right")
        .withRepresentationForFailure(Text("❗❗ is not a Right"))
        .withFeatureExtraction {
            if (it is Right) Some(it.b) else None
        }
        .withoutOptions()
        .build()

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
