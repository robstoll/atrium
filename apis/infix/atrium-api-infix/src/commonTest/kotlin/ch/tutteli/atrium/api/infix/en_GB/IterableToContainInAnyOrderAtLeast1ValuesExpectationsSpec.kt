package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
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

    companion object : IterableToContainSpecBase() {
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

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order atLeast 1 value 1
        nList = nList toContain o inAny order atLeast 1 value 1
        subList = subList toContain o inAny order atLeast 1 value 1
        star = star toContain o inAny order atLeast 1 value 1

        //TODO type parameter should not be necessary, check with Kotlin 1.4
        list = list toContain o inAny order atLeast 1 the values<Number>(1, 1.2)
        nList = nList toContain o inAny order atLeast 1 the values<Number>(1, 1.2)
        subList = subList toContain o inAny order atLeast 1 the values<Number>(1, 2.2)
        star = star toContain o inAny order atLeast 1 the values(1, 1.2, "asdf")

        list = list toContain 1
        nList = nList toContain 1
        subList = subList toContain 1
        star = star toContain 1

        list = list toContain values(1, 1.2)
        nList = nList toContain values(1, 1.2)
        subList = subList toContain values(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList toContain values("asdf")
        star = star toContain values(1, 1.2, "asdf")
    }
}
