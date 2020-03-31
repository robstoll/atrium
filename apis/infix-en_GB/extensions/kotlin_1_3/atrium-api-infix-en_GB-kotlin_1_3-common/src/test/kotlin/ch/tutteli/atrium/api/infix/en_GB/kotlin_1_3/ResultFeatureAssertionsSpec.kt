package ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.ResultFeatureAssertionsSpec

class ResultFeatureAssertionsSpec : ResultFeatureAssertionsSpec(
    feature0<Result<Int>, Int>(Expect<Result<Int>>::toBe),
    fun1<Result<Int>, Expect<Int>.() -> Unit>(Expect<Result<Int>>::toBe),
    feature0<Result<Int?>, Int?>(Expect<Result<Int?>>::toBe).withNullableSuffix(),
    fun1<Result<Int?>, Expect<Int?>.() -> Unit>(Expect<Result<Int?>>::toBe).withNullableSuffix(),
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

            a1 toBe success
            a1 = a1 toBe success { }

            a1.isFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.isFailure<IllegalArgumentException> { }

            a1b toBe success
            a1b = a1b toBe success { }

            a1b.isFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.isFailure<IllegalArgumentException> { }

            star toBe success
            star = star toBe success { }

            star.isFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = star.isFailure<IllegalArgumentException> { }
        }
    }
}
