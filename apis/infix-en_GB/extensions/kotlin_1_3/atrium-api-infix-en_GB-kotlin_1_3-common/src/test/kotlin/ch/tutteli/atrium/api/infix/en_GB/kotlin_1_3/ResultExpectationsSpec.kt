package ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3

import ch.tutteli.atrium.api.infix.en_GB.aSuccess
import ch.tutteli.atrium.api.infix.en_GB.success
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.ResultExpectationsSpec
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withFeatureSuffix
import ch.tutteli.atrium.specs.withNullableSuffix

class ResultExpectationsSpec : ResultExpectationsSpec(
    ("toBe ${aSuccess::class::simpleName}" to (Companion::toBeASuccessFeature)).withFeatureSuffix(),
    "toBe ${aSuccess::class::simpleName}" to Companion::toBeASuccess,
    ("toBe ${aSuccess::class::simpleName}" to (Companion::toBeASuccessFeatureNullable)).withFeatureSuffix()
        .withNullableSuffix(),
    ("toBe ${aSuccess::class::simpleName}" to (Companion::toBeASuccessNullable)).withNullableSuffix(),
    ("toBeAFailure" to Companion::toBeAFailureFeature).withFeatureSuffix(),
    "toBeAFailure" to Companion::toBeAFailure
) {
    companion object {
        private fun toBeASuccessFeature(expect: Expect<Result<Int>>) = expect toBe aSuccess
        private fun toBeAFailureFeature(expect: Expect<Result<Int>>) = expect.toBeAFailure<IllegalArgumentException>()

        private fun toBeASuccessFeatureNullable(expect: Expect<Result<Int?>>) = expect toBe aSuccess

        private fun toBeASuccessNullable(
            expect: Expect<Result<Int?>>,
            assertionCreator: Expect<Int?>.() -> Unit
        ) = expect toBe aSuccess { assertionCreator() }

        private fun toBeASuccess(
            expect: Expect<Result<Int>>,
            assertionCreator: Expect<Int>.() -> Unit
        ) = expect toBe aSuccess { assertionCreator() }

        private fun toBeAFailure(
            expect: Expect<out Result<*>>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.toBeAFailure<IllegalArgumentException> { assertionCreator() }

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
