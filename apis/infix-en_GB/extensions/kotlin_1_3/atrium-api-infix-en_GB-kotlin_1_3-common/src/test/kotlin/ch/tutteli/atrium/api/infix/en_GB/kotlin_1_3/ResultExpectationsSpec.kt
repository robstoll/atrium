package ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3

import ch.tutteli.atrium.api.infix.en_GB.success
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.ResultAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix

class ResultAssertionsSpec : ResultAssertionsSpec(
    ("toBe ${success::class::simpleName}" to (Companion::toBeSuccessFeature)).withFeatureSuffix(),
    "toBe ${success::class::simpleName}" to Companion::toBeSuccess,
    ("toBe ${success::class::simpleName}" to (Companion::toBeSuccessFeatureNullable)).withFeatureSuffix()
        .withNullableSuffix(),
    ("toBe ${success::class::simpleName}" to (Companion::toBeSuccessNullable)).withNullableSuffix(),
    ("isFailure" to Companion::isFailureFeature).withFeatureSuffix(),
    "isFailure" to Companion::isFailure
) {
    companion object {
        private fun toBeSuccessFeature(expect: Expect<Result<Int>>) = expect toBe success
        private fun isFailureFeature(expect: Expect<Result<Int>>) = expect.isFailure<IllegalArgumentException>()

        private fun toBeSuccessFeatureNullable(expect: Expect<Result<Int?>>) = expect toBe success

        private fun toBeSuccessNullable(
            expect: Expect<Result<Int?>>,
            assertionCreator: Expect<Int?>.() -> Unit
        ) = expect toBe success { assertionCreator() }

        private fun toBeSuccess(
            expect: Expect<Result<Int>>,
            assertionCreator: Expect<Int>.() -> Unit
        ) = expect toBe success { assertionCreator() }

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
