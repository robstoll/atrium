package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsInAnyOrderEntries

        private fun containsInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order atLeast 1 entry a
            else expect contains o inAny order atLeast 1 the entries(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::containsNullableEntries

        private fun containsNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inAny order atLeast 1 entry a
            else expect contains o inAny order atLeast 1 the entries(a, *aX)


        private val containsShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInAnyOrderEntriesShortcut

        private fun containsInAnyOrderEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains a
            else expect contains entries(a, *aX)

        private val containsEntriesFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() = containsEntriesFun.name to Companion::containsNullableEntriesShortcut

        private fun containsNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains a
            else expect contains entries(a, *aX)
    }
}
