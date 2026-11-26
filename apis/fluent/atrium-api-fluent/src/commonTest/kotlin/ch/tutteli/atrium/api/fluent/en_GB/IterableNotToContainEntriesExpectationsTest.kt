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
        Expect<List<Int>>::notToHaveElementsOrNone.name
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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 2))
        var nList: Expect<Set<Number?>> = expect(setOf(1, 2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2))
        var star: Expect<Collection<*>> = expect(listOf(1, 2))


        list = list.notToContain.entry { toEqual(3) }
        nList = nList.notToContain.entry { toEqual(3) }
        subList = subList.notToContain.entry { toEqual(3) }
        star = star.notToContain.entry { toEqual(3) }

        nList = nList.notToContain.entry(null)
        star = star.notToContain.entry(null)

        list = list.notToContain.entries({ toEqual(3) }, { toEqual(4) })
        nList = nList.notToContain.entries({ toEqual(3) }, { toEqual(4) })
        subList = subList.notToContain.entries({ toEqual(3) }, { toEqual(4) })
        star = star.notToContain.entries({ toEqual(3) }, { toEqual(4) })

        nList = nList.notToContain.entries(null, { toEqual(3) }, null)
        star = star.notToContain.entries(null, { toEqual(3) }, null)
    }
}
