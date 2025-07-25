package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableNotToContainEntriesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableNotToContainEntriesExpectationsTest :
    AbstractIterableNotToContainEntriesExpectationsTest(
        functionDescription to Companion::notToContainFun,
        (functionDescription to Companion::notToContainNullableFun).withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
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


    @Test
    @Suppress("AssignedValueIsNeverRead")
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 2, 3))
        var nList: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2, 3))
        var star: Expect<Collection<*>> = expect(listOf(1, 2))


        list = list.notToContain.value(4)
        nList = nList.notToContain.value(4)
        subList = subList.notToContain.value(4)
        star = star.notToContain.value(4)

        nList = nList.notToContain.value(null)
        star = star.notToContain.value(null)

        list = list.notToContain.values(5, 7)
        nList = nList.notToContain.values(3, 4)
        subList = subList.notToContain.values(5, 6)
        star = star.notToContain.values(7, 8)

        nList = nList.notToContain.values(null, 8)
        star = star.notToContain.values(null, {})
    }
}
