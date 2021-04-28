package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsNotValuesExpectationsSpec : Spek({

    include(BuilderSpecToContain)
    include(ShortcutSpecToContain)

}) {

    object BuilderSpecToContain : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        getContainsNotPair(),
        getContainsNotNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpecToContain : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        getContainsNotShortcutPair(),
        getContainsNotNullablePair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect containsNot o value a
            else expect containsNot o the values(a, *aX)

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect containsNot o value a
            else expect containsNot o the values(a, *aX)

        private val containsNotShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsNot

        private fun getContainsNotShortcutPair() = containsNotShortcutFun.name to Companion::containsNotShortcut

        private fun containsNotShortcut(expect: Expect<Iterable<Double>>, a: Double, aX: Array<out Double>) =
            if (aX.isEmpty()) expect containsNot a
            else expect containsNot values(a, *aX)
    }
}
