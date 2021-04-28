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
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::toContainInOrderOnlyValues,
        (functionDescription to C::toContainInOrderOnlyNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        shortcutDescription to C::toContainExactlyElementsOfShortcut,
        (shortcutDescription to C::toContainExactlyElementsOfNullableShortcut).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            functionDescription,
            listOf(1, 2),
            { input -> toContain.inOrder.only.elementsOf(input) }
        )

    object ShortcutIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            shortcutDescription,
            listOf(1, 2),
            { input -> toContainExactlyElementsOf(input) }
        )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$elementsOf"
        val shortcutDescription = Expect<Iterable<Int>>::toContainExactlyElementsOf.name

        fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.toContain.inOrder.only.elementsOf(listOf(a, *aX))

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.toContain.inOrder.only.elementsOf(sequenceOf(a, *aX))

        private fun toContainExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.toContainExactlyElementsOf(arrayOf(a, *aX))

        private fun toContainExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.toContainExactlyElementsOf(sequenceOf(a, *aX).asIterable())
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inOrder.only.elementsOf(listOf<Int>())
        nList = nList.toContain.inOrder.only.elementsOf(listOf<Int>())
        subList = subList.toContain.inOrder.only.elementsOf(listOf<Int>())
        star = star.toContain.inOrder.only.elementsOf(listOf<Int>())

        list = list.toContainExactlyElementsOf(1)
        nList = nList.toContainExactlyElementsOf(1)
        subList = subList.toContainExactlyElementsOf(1)
        star = star.toContainExactlyElementsOf(1)

        list = list.toContainExactlyElementsOf(listOf(1, 1.2))
        nList = nList.toContainExactlyElementsOf(listOf(1, 1.2))
        subList = subList.toContainExactlyElementsOf(listOf(1, 2.2))
        subList = subList.toContainExactlyElementsOf(listOf(1))
        star = star.toContainExactlyElementsOf(listOf(1, 1.2, "asdf"))
    }
}

