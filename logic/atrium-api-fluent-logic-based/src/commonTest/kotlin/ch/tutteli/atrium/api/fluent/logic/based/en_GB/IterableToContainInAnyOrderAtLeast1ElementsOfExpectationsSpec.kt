package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.IterableToContainInAnyOrderAtLeast1ElementsOfExpectationsSpec.Companion as C

class IterableToContainInAnyOrderAtLeast1ElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        functionDescription to C::toContainValues,
        (functionDescription to C::toContainNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        shortcutDescription to C::toContainInAnyOrderShortcut,
        (shortcutDescription to C::toContainInAnyOrderNullableShortcut).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        functionDescription,
        listOf(1, 2),
        { input -> toContain.inAnyOrder.atLeast(1).elementsOf(input) }
    )

    object ShortcutIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        shortcutDescription,
        listOf(1, 2),
        { input -> toContainElementsOf(input) }
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription =  "$toContain.$inAnyOrder.$atLeast(1).$elementsOf"
        val shortcutDescription = Expect<Iterable<Double>>::toContainElementsOf.name

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.toContain.inAnyOrder.atLeast(1).elementsOf(listOf(a, *aX))

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.toContain.inAnyOrder.atLeast(1).elementsOf(sequenceOf(a, *aX))

        private fun toContainInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.toContainElementsOf(arrayOf(a, *aX))

        private fun toContainInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.toContainElementsOf(sequenceOf(a, *aX).asIterable())
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        nList = nList.toContain.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        subList = subList.toContain.inAnyOrder.atLeast(1).elementsOf(listOf(1))
        star = star.toContain.inAnyOrder.atLeast(1).elementsOf(listOf(1))

        list = list.toContainElementsOf(listOf(1))
        nList = nList.toContainElementsOf(listOf(1))
        subList = subList.toContainElementsOf(listOf(1))
        star = star.toContainElementsOf(listOf(1))

        list = list.toContainElementsOf(listOf(1, 1.2))
        nList = nList.toContainElementsOf(listOf(1, 1.2))
        subList = subList.toContainElementsOf(listOf(1, 2.2))
        subList = subList.toContainElementsOf(listOf(1))
        star = star.toContainElementsOf(listOf(1, 1.2, "asdf"))
    }
}
