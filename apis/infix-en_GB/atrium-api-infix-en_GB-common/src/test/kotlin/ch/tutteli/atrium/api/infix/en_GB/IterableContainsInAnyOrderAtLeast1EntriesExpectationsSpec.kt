package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::toContainInAnyOrderEntries

        private fun toContainInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 entry a
            else expect toContain o inAny order atLeast 1 the entries(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::toContainNullableEntries

        private fun toContainNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 entry a
            else expect toContain o inAny order atLeast 1 the entries(a, *aX)


        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContain

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInAnyOrderEntriesShortcut

        private fun toContainInAnyOrderEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain entries(a, *aX)

        private val toContainEntriesFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContain

        fun getToContainNullableShortcutPair() = toContainEntriesFun.name to Companion::toContainNullableEntriesShortcut

        private fun toContainNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain entries(a, *aX)
    }
}
