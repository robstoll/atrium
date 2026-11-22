package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndAnyExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableToHaveElementsAndAnyExpectationsBuilderTest :
    AbstractIterableToHaveElementsAndAnyExpectationsTest(
        functionDescription to Companion::toContainEntry,
        (functionDescription to Companion::toContainNullableEntry).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$atLeast(1).$entry"

        private fun toContainEntry(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.toContain.inAnyOrder.atLeast(1).entry(a)

        private fun toContainNullableEntry(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.toContain.inAnyOrder.atLeast(1).entry(a)
    }
}
