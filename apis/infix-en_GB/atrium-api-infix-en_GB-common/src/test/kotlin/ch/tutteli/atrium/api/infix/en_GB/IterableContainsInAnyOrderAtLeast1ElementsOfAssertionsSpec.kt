package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderIterableLikeSpec)
    include(ShortcutIterableLikeSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ",
        "[Atrium][Shortcut] "
    )

    object BuilderIterableLikeSpec : ch.tutteli.atrium.specs.integration.IterableLikeSpec<List<Int>>(
        "contains o inAny order atLeast 1 elementsOf",
        listOf(1, 2),
        { input -> it contains o inAny order atLeast 1 elementsOf input },
        { input -> it contains o inAny order atLeast 1 elementsOf input }
    )

    object ShortcutIterableLikeSpec : ch.tutteli.atrium.specs.integration.IterableLikeSpec<List<Int>>(
        "containsElementsOf",
        listOf(1, 2),
        { input -> it containsElementsOf input },
        { input -> it containsElementsOf input }
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::containsValues

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect contains o inAny order atLeast 1 elementsOf listOf(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::containsNullableValues

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect contains o inAny order atLeast 1 elementsOf listOf(a, *aX)

        private val containsElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsElementsOf

        private fun getContainsShortcutPair() = containsElementsOfShortcutFun.name to Companion::containsInAnyOrderShortcut

        private fun containsInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect containsElementsOf listOf(a, *aX)

        private val containsElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsElementsOf

        private fun getContainsNullableShortcutPair() = containsElementsOfNullableShortcutFun.name to Companion::containsInAnyOrderNullableShortcut;

        private fun containsInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect containsElementsOf listOf(a, *aX)

    }
}
