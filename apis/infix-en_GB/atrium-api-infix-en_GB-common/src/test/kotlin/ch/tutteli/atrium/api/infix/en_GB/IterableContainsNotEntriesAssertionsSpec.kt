package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsNotEntriesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsNotEntriesAssertionsSpec(
        getContainsNotPair(),
        getContainsNotNullablePair().withNullableSuffix(),
        "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect containsNot o entry a
            else expect containsNot o the entries(a, *aX)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect containsNot o entry a
            else expect containsNot o the entries(a, *aX)
    }
}
