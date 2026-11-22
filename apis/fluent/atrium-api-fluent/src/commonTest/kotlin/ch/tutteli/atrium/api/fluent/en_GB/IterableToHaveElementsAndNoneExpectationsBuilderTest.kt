package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndNoneExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix

// TODO 2.1.0 #722 this will differ once we don't implement the same behaviour for notTContain and none
// that's fine and we can simply remove this test here
class IterableToHaveElementsAndNoneExpectationsBuilderTest :
    AbstractIterableToHaveElementsAndNoneExpectationsTest(
        functionDescription to Companion::notToContainFun,
        (functionDescription to Companion::notToContainNullableFun).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$notToContain.$entry"

        private fun notToContainFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.notToContain.entry(a)

        private fun notToContainNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.notToContain.entry(a)
    }
}
