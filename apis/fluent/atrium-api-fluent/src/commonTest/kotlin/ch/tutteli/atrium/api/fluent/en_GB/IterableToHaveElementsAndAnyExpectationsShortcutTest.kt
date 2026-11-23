package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToHaveElementsAndAnyExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix

// TODO 2.0.0 #722 this will differ once we don't implement the same behaviour for notToContain and none
// that's fine and we can simply remove this test here
class IterableToHaveElementsAndAnyExpectationsShortcutTest :
    AbstractIterableToHaveElementsAndAnyExpectationsTest(
        shortcutDescription to Companion::toContainEntryShortcut,
        (shortcutDescription to Companion::toContainNullableEntryShortcut).withNullableSuffix()
    ) {
    companion object : IterableToContainSpecBase() {
        val shortcutDescription = toContain

        private fun toContainEntryShortcut(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.toContain(a)

        private fun toContainNullableEntryShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?
        ) = expect.toContain(a)
    }
}
