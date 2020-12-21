package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderElementsOf",
        listOf(1, 2),
        { input -> contains.inAnyOrder.atLeast(1).elementsOf(input) }
    )

    object ShortcutIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        "containsElementsOf",
        listOf(1, 2),
        { input -> containsElementsOf(input) }
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderElementsOf" to Companion::containsValues

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderElementsOf" to Companion::containsNullableValues

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(sequenceOf(a, *aX))

        private val containsElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsElementsOf

        private fun getContainsShortcutPair() =
            containsElementsOfShortcutFun.name to Companion::containsInAnyOrderShortcut

        private fun containsInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsElementsOf(arrayOf(a, *aX))

        private val containsElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsElementsOf

        private fun getContainsNullableShortcutPair() =
            containsElementsOfNullableShortcutFun.name to Companion::containsInAnyOrderNullableShortcut;

        private fun containsInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsElementsOf(sequenceOf(a, *aX).asIterable())
    }
}
