package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class ThirdPartyExpectationsSpec : ch.tutteli.atrium.specs.integration.ThirdPartyExpectationsSpec(
    fun3(Companion::toHoldThirdPartyExpectation),
    fun3<Int?, String, Any?, (Int?) -> Unit>(Companion::toHoldThirdPartyExpectation).withNullableSuffix(),
) {
    companion object {
        fun <T> toHoldThirdPartyExpectation(
            expect: Expect<T>,
            description: String,
            representation: Any?,
            expectationExecutor: (T) -> Unit
        ): Expect<T> = expect toHold thirdPartyExpectation(description, representation, expectationExecutor)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val int: Expect<Int> = notImplemented()
        val nullableInt: Expect<Int?> = notImplemented()
        val star: Expect<*> = notImplemented()

        int toHold thirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
        nullableInt toHold thirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
        star toHold thirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
    }
}
