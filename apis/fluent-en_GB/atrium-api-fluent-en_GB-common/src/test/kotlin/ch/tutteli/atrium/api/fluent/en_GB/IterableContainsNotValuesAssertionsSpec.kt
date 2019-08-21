package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction3

class IterableContainsNotValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsNotValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsNotShortcutPair(),
        getContainsNotNullablePair(),
        "◆ ", "✔ ", "✘ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.containsNot.value(a)
            else expect.containsNot.values(a, *aX)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.containsNot.value(a)
            else expect.containsNot.values(a, *aX)

        private val containsNotShortcutFun: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsNot

        private fun getContainsNotShortcutPair() = containsNotShortcutFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(expect: Expect<Iterable<Double>>, a: Double, aX: Array<out Double>) =
            expect.containsNot(a, *aX)
    }
}
