package ch.tutteli.atrium.creating.any.typetransformation.creators

import ch.tutteli.atrium.api.cc.en_GB.isLessThan
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.startsWith
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.verbs.internal.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TypeTransformationAssertionCreatorSpec : Spek({

    val either: Either<String, Int> =
        Left("hello")
    describe("custom Either<String, Int> with left \"hello\"") {
        it("${assert(either)::isLeft.name} does not throw") {
            assert(either).isLeft {
                asAssert().startsWith("h")
            }
        }
        it("${assert(either)::isRight.name} throws AssertionError containing explanation") {
            expect {
                assert(either).isRight {
                    asAssert().isLessThan(2)
                }
            }.toThrow<AssertionError> {
                messageContains(
                    "is a: ${Right::class.java.simpleName}",
                    "${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 2"
                )
            }
        }
    }
})

fun <A, B> Expect<Either<A, B>>.isLeft(assertionCreator: Expect<A>.() -> Unit) =
    ExpectImpl.changeSubject.reportBuilder(this)
        .withDescriptionAndRepresentation("is a", RawString.create(Left::class.java.simpleName))
        .withCheck { it.isLeft() }
        .withTransformation { (it as Left).a }
        .build()
        .addToInitial(assertionCreator)

fun <A, B> Expect<Either<A, B>>.isRight(assertionCreator: Expect<B>.() -> Unit) =
    ExpectImpl.changeSubject.reportBuilder(this)
        .withDescriptionAndRepresentation("is a", RawString.create(Right::class.java.simpleName))
        .withCheck { it.isRight() }
        .withTransformation { (it as Right).b }
        .build()
        .addToInitial(assertionCreator)

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
