package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec.Companion as C

class IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        functionDescription to C::containsValues,
        (functionDescription to C::containsNullableValues).withNullableSuffix(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        shortcutDescription to C::containsInAnyOrderShortcut,
        (shortcutDescription to C::containsInAnyOrderNullableShortcut).withNullableSuffix(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        functionDescription,
        listOf(1, 2),
        { input -> contains.inAnyOrder.atLeast(1).elementsOf(input) }
    )

    object ShortcutIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        shortcutDescription,
        listOf(1, 2),
        { input -> containsElementsOf(input) }
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription =  "$contains.$inAnyOrder.$atLeast(1).$elementsOf"
        val shortcutDescription = Expect<Iterable<Double>>::containsElementsOf.name

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(listOf(a, *aX))

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(sequenceOf(a, *aX))

        private fun containsInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsElementsOf(arrayOf(a, *aX))

        private fun containsInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsElementsOf(sequenceOf(a, *aX).asIterable())
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        nList = nList.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        subList = subList.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        star = star.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1))

        list = list.containsElementsOf(listOf(1))
        nList = nList.containsElementsOf(listOf(1))
        subList = subList.containsElementsOf(listOf(1))
        star = star.containsElementsOf(listOf(1))

        list = list.containsElementsOf(listOf(1, 1.2))
        nList = nList.containsElementsOf(listOf(1, 1.2))
        subList = subList.containsElementsOf(listOf(1, 2.2))
        subList = subList.containsElementsOf(listOf(1))
        star = star.containsElementsOf(listOf(1, 1.2, "asdf"))
    }
}
