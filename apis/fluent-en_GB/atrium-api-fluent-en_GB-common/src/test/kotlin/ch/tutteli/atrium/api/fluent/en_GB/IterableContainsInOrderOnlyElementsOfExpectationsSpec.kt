package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInOrderOnlyElementsOfExpectationsSpec.Companion as C

class IterableContainsInOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::containsInOrderOnlyValues,
        (functionDescription to C::containsInOrderOnlyNullableValues).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesExpectationsSpec(
        shortcutDescription to C::containsExactlyElementsOfShortcut,
        (shortcutDescription to C::containsExactlyElementsOfNullableShortcut).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            functionDescription,
            listOf(1, 2),
            { input -> contains.inOrder.only.elementsOf(input) }
        )

    object ShortcutIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            shortcutDescription,
            listOf(1, 2),
            { input -> containsExactlyElementsOf(input) }
        )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$elementsOf"
        val shortcutDescription = Expect<Iterable<Int>>::containsExactlyElementsOf.name

        fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.elementsOf(sequenceOf(a, *aX))

        private fun containsExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsExactlyElementsOf(arrayOf(a, *aX))

        private fun containsExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsExactlyElementsOf(sequenceOf(a, *aX).asIterable())
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inOrder.only.elementsOf(listOf<Int>())
        nList = nList.contains.inOrder.only.elementsOf(listOf<Int>())
        subList = subList.contains.inOrder.only.elementsOf(listOf<Int>())
        star = star.contains.inOrder.only.elementsOf(listOf<Int>())

        list = list.containsExactlyElementsOf(1)
        nList = nList.containsExactlyElementsOf(1)
        subList = subList.containsExactlyElementsOf(1)
        star = star.containsExactlyElementsOf(1)

        list = list.containsExactlyElementsOf(listOf(1, 1.2))
        nList = nList.containsExactlyElementsOf(listOf(1, 1.2))
        subList = subList.containsExactlyElementsOf(listOf(1, 2.2))
        subList = subList.containsExactlyElementsOf(listOf(1))
        star = star.containsExactlyElementsOf(listOf(1, 1.2, "asdf"))
    }
}

