package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class ResultExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractResultExpectationsTest(
    feature0<Result<Int>, Int>(Expect<Result<Int>>::toBeASuccess),
    fun1<Result<Int>, Expect<Int>.() -> Unit>(Expect<Result<Int>>::toBeASuccess),
    feature0<Result<Int?>, Int?>(Expect<Result<Int?>>::toBeASuccess).withNullableSuffix(),
    fun1<Result<Int?>, Expect<Int?>.() -> Unit>(Expect<Result<Int?>>::toBeASuccess).withNullableSuffix(),
    ("isFailure" to Companion::toBeAFailureFeature).withFeatureSuffix(),
    "isFailure" to Companion::toBeAFailure
) {
    companion object {
        private fun toBeAFailureFeature(expect: Expect<Result<Int>>) = expect.toBeAFailure<IllegalArgumentException>()

        private fun toBeAFailure(
            expect: Expect<out Result<*>>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.toBeAFailure<IllegalArgumentException> { assertionCreator() }

        @Suppress("unused", "UNUSED_VARIABLE", "UNUSED_VALUE")
        @Test
        fun ambiguityTest() {
            var a1: Expect<Result<Int>> = expect(Result.success(1))
            var a1b: Expect<Result<Int?>> = expect(Result.success(1))
            var star: Expect<Result<*>> = expect(Result.success(1))

            a1.toBeASuccess()
            a1 = a1.toBeASuccess { toEqual(1) }

            a1 = expect(Result.failure(IllegalArgumentException("oh no...")))
            a1.toBeAFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.toBeAFailure<IllegalArgumentException> { messageToContain("oh no...") }

            a1b.toBeASuccess()
            a1b = a1b.toBeASuccess { toEqual(1) }

            a1b = expect(Result.failure<Int?>(IllegalArgumentException("oh no...")))
            a1b.toBeAFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.toBeAFailure<IllegalArgumentException> { messageToContain("oh no...") }

            star.toBeASuccess()
            star = star.toBeASuccess { toEqual(1) }

            star = expect(Result.failure<Int>(IllegalArgumentException("oh no...")))
            star.toBeAFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = star.toBeAFailure<IllegalArgumentException> { messageToContain("oh no...") }
        }
    }
}
