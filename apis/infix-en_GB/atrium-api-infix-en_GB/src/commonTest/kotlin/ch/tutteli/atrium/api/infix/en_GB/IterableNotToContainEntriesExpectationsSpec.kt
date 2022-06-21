package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableNotToContainEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableNotToContainEntriesExpectationsSpec(
        getNotToContainPair(),
        getNotToContainNullablePair().withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Builder] "
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getNotToContainPair() = notToContain to Companion::notToContainFun

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect notToContain o entry a
            else expect notToContain o the entries(a, *aX)

        private fun getNotToContainNullablePair() = notToContain to Companion::notToContainNullableFun

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect notToContain o entry a
            else expect notToContain o the entries(a, *aX)
    }
}
