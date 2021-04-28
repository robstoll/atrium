package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableNotToContainEntriesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableNotToContainEntriesExpectationsSpec(
        functionDescription to Companion::notToContainFun,
        (functionDescription to Companion::notToContainNullableFun).withNullableSuffix(),
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        private val functionDescription = "$notToContain.$entry/$entries"

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.notToContain.entry(a)
            else expect.notToContain.entries(a, *aX)

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.notToContain.entry(a)
            else expect.notToContain.entries(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.notToContain.entry {}
        nList = nList.notToContain.entry {}
        subList = subList.notToContain.entry {}
        star = star.notToContain.entry {}

        nList = nList.notToContain.entry(null)
        star = star.notToContain.entry(null)

        list = list.notToContain.entries({}, {})
        nList = nList.notToContain.entries({}, {})
        subList = subList.notToContain.entries({}, {})
        star = star.notToContain.entries({}, {})

        nList = nList.notToContain.entries(null, {}, null)
        star = star.notToContain.entries(null, {}, null)
    }
}
