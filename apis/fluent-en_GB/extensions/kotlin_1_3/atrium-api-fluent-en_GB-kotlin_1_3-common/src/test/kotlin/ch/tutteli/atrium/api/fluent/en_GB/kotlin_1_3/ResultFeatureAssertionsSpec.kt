package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.ResultFeatureAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented

class ResultFeatureAssertionsSpec : ResultFeatureAssertionsSpec(
    feature0<Result<Int>, Int>(Expect<Result<Int>>::isSuccess),
    fun1<Result<Int>, Expect<Int>.() -> Unit>(Expect<Result<Int>>::isSuccess),
    feature0<Result<Int?>, Int?>(Expect<Result<Int?>>::isSuccess),
    fun1<Result<Int?>, Expect<Int?>.() -> Unit>(Expect<Result<Int?>>::isSuccess),
    "isFailure" to Companion::isFailureFeature,
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
            var a2: Expect<out Result<Int>> = notImplemented()
            var a1b: Expect<Result<Int?>> = notImplemented()
            var a2b: Expect<out Result<Int?>> = notImplemented()

            var a3: Expect<Result<*>> = notImplemented()

            a1.isSuccess()
            a2.isSuccess()
            a1 = a1.isSuccess { }
            a2 = a2.isSuccess { }

            a1.isFailure<IllegalArgumentException>()
            a2.isFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.isFailure<IllegalArgumentException> {  }
            val r2: Expect<IllegalArgumentException> = a2.isFailure<IllegalArgumentException> {  }

            a1b.isSuccess()
            a2b.isSuccess()
            a1b = a1b.isSuccess { }
            a2b = a2b.isSuccess { }

            a1b.isFailure<IllegalArgumentException>()
            a2b.isFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.isFailure<IllegalArgumentException> {  }
            val r2b: Expect<IllegalArgumentException> = a2b.isFailure<IllegalArgumentException> {  }

            a3.isSuccess()
            a3 = a3.isSuccess { }

            a3.isFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = a3.isFailure<IllegalArgumentException> { }
        }
    }
}
