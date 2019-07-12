package ch.tutteli.atrium.creating.any.typetransformation.creators

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.any.typetransformation.*
import ch.tutteli.atrium.verbs.internal.expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context

//TODO rewrite and use Expect instead
object TypeTransformationAssertionCreatorSpec : Spek({

    val either: Either<String, Int> =
        Left("hello")
    context("custom Either<String, Int> with left \"hello\"") {
        test("${assert(either)::isLeft.name} does not throw") {
            assert(either).isLeft {
                startsWith("h")
            }
        }
        test("${assert(either)::isRight.name} throws AssertionError containing explanation") {
            expect {
                assert(either).isRight {
                    isLessThan(2)
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


fun <A : Any, B : Any> Assert<Either<A, B>>.isLeft(assertionCreator: Assert<A>.() -> Unit) {
    @Suppress("DEPRECATION") val parameterObject = AnyTypeTransformation.ParameterObject(
        Untranslatable("is a"),
        RawString.create(Left::class.java.simpleName),
        this,
        assertionCreator,
        Untranslatable("Could not evaluate the defined assertion(s) -- Either.isLeft was false")
    )
    @Suppress("DEPRECATION")
    AssertImpl.any.typeTransformation.transform(
        parameterObject, { it.isLeft() }, { (it as Left).a },
        AssertImpl.any.typeTransformation.failureHandlers.newExplanatory()
    )
}


fun <A : Any, B : Any> Assert<Either<A, B>>.isRight(assertionCreator: Assert<B>.() -> Unit) {
    @Suppress("DEPRECATION")
    val parameterObject = AnyTypeTransformation.ParameterObject(
        Untranslatable("is a"),
        RawString.create(Right::class.java.simpleName),
        this,
        assertionCreator,
        Untranslatable("Could not evaluate the defined assertion(s) -- Either.isRight was false")
    )
    @Suppress("DEPRECATION")
    AssertImpl.any.typeTransformation.transform(
        parameterObject, { it.isRight() }, { (it as Right).b },
        AssertImpl.any.typeTransformation.failureHandlers.newExplanatory()
    )
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
