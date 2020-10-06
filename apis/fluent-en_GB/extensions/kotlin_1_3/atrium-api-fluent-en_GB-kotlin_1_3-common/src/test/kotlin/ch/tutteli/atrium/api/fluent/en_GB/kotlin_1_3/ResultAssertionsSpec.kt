package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

class ResultAssertionsSpec : ch.tutteli.atrium.specs.integration.ResultAssertionsSpec(
    feature0<Result<Int>, Int>(Expect<Result<Int>>::isSuccess),
    fun1<Result<Int>, Expect<Int>.() -> Unit>(Expect<Result<Int>>::isSuccess),
    feature0<Result<Int?>, Int?>(Expect<Result<Int?>>::isSuccess).withNullableSuffix(),
    fun1<Result<Int?>, Expect<Int?>.() -> Unit>(Expect<Result<Int?>>::isSuccess).withNullableSuffix(),
    ("isFailure" to Companion::isFailureFeature).withFeatureSuffix(),
    "isFailure" to Companion::isFailure
) {
    companion object {
        private fun isFailureFeature(expect: Expect<Result<Int>>) = expect.isFailure<IllegalArgumentException>()

        private fun isFailure(
            expect: Expect<out Result<*>>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.isFailure<IllegalArgumentException> { assertionCreator() }

        @Suppress("unused", "UNUSED_VALUE", "UNUSED_VARIABLE")
        private fun ambiguityTest() {
            var a1: Expect<Result<Int>> = notImplemented()
            var a1b: Expect<Result<Int?>> = notImplemented()

            var star: Expect<Result<*>> = notImplemented()

            a1.isSuccess()
            a1 = a1.isSuccess { }

            a1.isFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.isFailure<IllegalArgumentException> { }

            a1b.isSuccess()
            a1b = a1b.isSuccess { }

            a1b.isFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.isFailure<IllegalArgumentException> { }

            star.isSuccess()
            star = star.isSuccess { }

            star.isFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = star.isFailure<IllegalArgumentException> { }
        }
    }
}
