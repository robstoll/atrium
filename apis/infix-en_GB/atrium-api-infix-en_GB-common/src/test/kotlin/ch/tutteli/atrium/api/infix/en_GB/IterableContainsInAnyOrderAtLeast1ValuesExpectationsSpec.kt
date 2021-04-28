package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        getToContainPair(),
        getToContainNullablePair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::toContainValues

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 value a
            else expect toContain o inAny order atLeast 1 the values(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::toContainNullableValues

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 value a
            else expect toContain o inAny order atLeast 1 the values(a, *aX)


        private val toContainFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContain

        fun getToContainShortcutPair() = toContainFun.name to Companion::toContainValuesShortcut

        private fun toContainValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain values(a, *aX)


        private val toContainNullableFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContain

        fun getToContainNullableShortcutPair() = toContainNullableFun.name to Companion::toContainNullableValuesShortcut

        private fun toContainNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain values(a, *aX)

    }
}
