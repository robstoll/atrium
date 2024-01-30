package ch.tutteli.atrium.api.infix.en_GB

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
            expect: Expect<Result<*>>,
            assertionCreator: Expect<IllegalArgumentException>.() -> Unit
        ) = expect.toBeAFailure<IllegalArgumentException> { assertionCreator() }

        @Suppress("unused", "UNUSED_VARIABLE")
        private fun ambiguityTest() {
            var a1: Expect<Result<Int>> = notImplemented()
            var a1b: Expect<Result<Int?>> = notImplemented()

            var star: Expect<Result<*>> = notImplemented()

            a1 toBe aSuccess
            a1 = a1 toBe aSuccess { }

            a1.toBeAFailure<IllegalArgumentException>()
            val r1: Expect<IllegalArgumentException> = a1.toBeAFailure<IllegalArgumentException> { }

            a1b toBe aSuccess
            a1b = a1b toBe aSuccess { }

            a1b.toBeAFailure<IllegalArgumentException>()
            val r1b: Expect<IllegalArgumentException> = a1b.toBeAFailure<IllegalArgumentException> { }

            star toBe aSuccess
            star = star toBe aSuccess { }

            star.toBeAFailure<IllegalArgumentException>()
            val r3: Expect<IllegalArgumentException> = star.toBeAFailure<IllegalArgumentException> { }
        }
    }
}
