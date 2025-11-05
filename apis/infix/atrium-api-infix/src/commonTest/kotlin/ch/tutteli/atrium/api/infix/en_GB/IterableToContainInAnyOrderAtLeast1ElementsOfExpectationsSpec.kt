package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInAnyOrderAtLeast1ElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        "toContain o inAny order atLeast 1 elementsOf",
        listOf(1, 2),
        { input -> it toContain o inAny order atLeast 1 elementsOf input }
    )

    object ShortcutIterableLikeToIterableSpec : ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
        "toContainElementsOf",
        listOf(1, 2),
        { input -> it toContainElementsOf input }
    )

    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::toContainValues

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect toContain o inAny order atLeast 1 elementsOf listOf(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::toContainNullableValues

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect toContain o inAny order atLeast 1 elementsOf sequenceOf(a, *aX)

        private val toContainElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainElementsOf

        private fun getToContainShortcutPair() = toContainElementsOfShortcutFun.name to Companion::toContainInAnyOrderShortcut

        private fun toContainInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect toContainElementsOf arrayOf(a, *aX)

        private val toContainElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainElementsOf

        private fun getToContainNullableShortcutPair() = toContainElementsOfNullableShortcutFun.name to Companion::toContainInAnyOrderNullableShortcut;

        private fun toContainInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect toContainElementsOf sequenceOf(a, *aX).asIterable()
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order atLeast 1 elementsOf listOf(1)
        nList = nList toContain o inAny order atLeast 1 elementsOf listOf(1)
        subList = subList toContain o inAny order atLeast 1 elementsOf listOf(1)
        star = star toContain o inAny order atLeast 1 elementsOf listOf(1)

        list = list toContainElementsOf listOf(1)
        nList = nList toContainElementsOf listOf(1)
        subList = subList toContainElementsOf listOf(1)
        star = star toContainElementsOf listOf(1)

        list = list toContainElementsOf listOf(1, 1.2)
        nList = nList toContainElementsOf listOf(1, 1.2)
        subList = subList toContainElementsOf listOf(1, 2.2)
        subList = subList toContainElementsOf listOf(1)
        star = star toContainElementsOf listOf(1, 1.2, "asdf")
    }
}
