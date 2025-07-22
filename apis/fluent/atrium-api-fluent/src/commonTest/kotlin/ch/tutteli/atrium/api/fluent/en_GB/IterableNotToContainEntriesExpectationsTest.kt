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

}

@Test
@Suppress("unused", "UNUSED_VALUE")
fun ambiguityTest() {
    var list: Expect<List<Number>> = expect(listOf(1, 2, 3))
    var nList: Expect<Set<Number?>> = expect(setOf(1, 2, null))
    var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 2, 3))
    var star: Expect<Collection<*>> = expect(listOf(1, 2))


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
