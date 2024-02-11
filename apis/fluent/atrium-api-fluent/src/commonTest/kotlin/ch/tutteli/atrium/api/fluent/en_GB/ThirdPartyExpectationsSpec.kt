package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class ThirdPartyExpectationsSpec : ch.tutteli.atrium.specs.integration.ThirdPartyExpectationsSpec(
    fun3(Expect<Int>::toHoldThirdPartyExpectation),
    fun3(Expect<Int?>::toHoldThirdPartyExpectation).withNullableSuffix(),
) {

    @Suppress("unused")
    private fun ambiguityTest() {
        val int: Expect<Int> = notImplemented()
        val nullableInt: Expect<Int?> = notImplemented()
        val star: Expect<*> = notImplemented()

        int.toHoldThirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
        nullableInt.toHoldThirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
        star.toHoldThirdPartyExpectation("descr", 1) { _ -> throw IllegalStateException() }
    }
}
