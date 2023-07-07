package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

class ResultExpectationsSpec : ch.tutteli.atrium.specs.integration.ResultExpectationsSpec(
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

        @Suppress("unused", "UNUSED_VARIABLE")
        private fun ambiguityTest() {
            var a1: Expect<Result<Int>> = notImplemented()
            var a1b: Expect<Result<Int?>> = notImplemented()

            var star: Expect<Result<*>> = notImplemented()

            a1.toBeASuccess()
            a1 = a1.toBeASuccess { }

            a1.toBeAFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.toBeAFailure<IllegalArgumentException> { }

            a1b.toBeASuccess()
            a1b = a1b.toBeASuccess { }

            a1b.toBeAFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.toBeAFailure<IllegalArgumentException> { }

            star.toBeASuccess()
            star = star.toBeASuccess { }

            star.toBeAFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = star.toBeAFailure<IllegalArgumentException> { }
        }
    }
}
