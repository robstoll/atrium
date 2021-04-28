package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableNotToContainValuesExpectationsSpec : Spek({

    include(BuilderSpecToContain)
    include(ShortcutSpecToContain)

}) {

    object BuilderSpecToContain : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        getNotToContainPair(),
        getNotToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpecToContain : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        getNotToContainShortcutPair(),
        getNotToContainNullablePair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getNotToContainPair() = notToContain to Companion::notToContainFun

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)

        private fun getNotToContainNullablePair() = notToContain to Companion::notToContainNullableFun

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)

        private val notToContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::notToContain

        private fun getNotToContainShortcutPair() = notToContainShortcutFun.name to Companion::notToContainShortcut

        private fun notToContainShortcut(expect: Expect<Iterable<Double>>, a: Double, aX: Array<out Double>) =
            if (aX.isEmpty()) expect notToContain a
            else expect notToContain values(a, *aX)
    }
}
