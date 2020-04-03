package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyEntriesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyEntriesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::containsInOrderOnly

        private fun containsInOrderOnly(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inGiven order and only entry a
            else expect contains o inGiven order and only the ch.tutteli.atrium.api.infix.en_GB.creating.Entries(
                a,
                *aX
            )

        fun getContainsNullablePair() =
            "$contains $filler $inOrder $andOnly $inOrderOnlyEntries" to Companion::containsInOrderOnlyNullableEntriesPair

        private fun containsInOrderOnlyNullableEntriesPair(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inGiven order and only entry a
            else expect contains o inGiven order and only the ch.tutteli.atrium.api.infix.en_GB.creating.Entries(
                a,
                *aX
            )

        private val containsShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactly

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyEntriesShortcut

        private fun containsInOrderOnlyEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect containsExactly { a() }
            else expect containsExactly ch.tutteli.atrium.api.infix.en_GB.creating.Entries(
                a,
                *aX
            )

        private val containsNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactly

        fun getContainsNullableShortcutPair() =
            containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableEntriesShortcut

        private fun containsInOrderOnlyNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) {
                //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
                if (a == null) expect containsExactly a as Double?
                else expect containsExactly { a() }
            } else {
                expect containsExactly ch.tutteli.atrium.api.infix.en_GB.creating.Entries(
                    a,
                    *aX
                )
            }
    }
}
