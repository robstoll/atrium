package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInOrderOnlyElementsOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeToIterableSpec)
    include(ShortcutIterableLikeToIterableSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContain o inGiven order and only elementsOf",
            listOf(1, 2),
            { input -> it toContain o inGiven order and only elementsOf input }
        )

    object ShortcutIterableLikeToIterableSpec :
        ch.tutteli.atrium.specs.integration.IterableLikeToIterableSpec<List<Int>>(
            "toContainExactlyElementsOf",
            listOf(1, 2),
            { input -> it toContainExactlyElementsOf input }
        )

    companion object : IterableContainsSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyValues

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect toContain o inGiven order and only elementsOf listOf(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inOrder $andOnly $inOrderElementsOf" to Companion::toContainInOrderOnlyNullableValues

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect toContain o inGiven order and only elementsOf sequenceOf(a, *aX)

        private val toContainExactlyElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContainExactlyElementsOf

        private fun getToContainShortcutPair() =
            toContainExactlyElementsOfShortcutFun.name to Companion::toContainExactlyElementsOfShortcut

        private fun toContainExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect toContainExactlyElementsOf arrayOf(a, *aX)

        private val toContainExactlyElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContainExactlyElementsOf

        private fun getToContainNullableShortcutPair() =
            toContainExactlyElementsOfNullableShortcutFun.name to Companion::toContainExactlyElementsOfNullableShortcut;

        private fun toContainExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect toContainExactlyElementsOf sequenceOf(a, *aX).asIterable()

    }
}

