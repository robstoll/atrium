package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun3
import ch.tutteli.atrium.specs.integration.AbstractThirdPartyExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class ThirdPartyExpectationsTest : AbstractThirdPartyExpectationsTest(
    fun3(Expect<Int>::toHoldThirdPartyExpectation),
    fun3(Expect<Int?>::toHoldThirdPartyExpectation).withNullableSuffix(),
) {

    @Suppress("unused")
    @Test
    fun ambiguityTest() {
        val int: Expect<Int> = expect(1)
        val nullableInt: Expect<Int?> = expect(1)
        val star: Expect<*> = expect(1)

        int.toHoldThirdPartyExpectation("descr", 1) { _ -> }
        nullableInt.toHoldThirdPartyExpectation("descr", 1) { _ -> }
        star.toHoldThirdPartyExpectation("descr", 1) { _ -> }
    }
}
