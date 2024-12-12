package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec.Companion as C

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        functionDescription to C::toContainValues,
        (functionDescription to C::toContainNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
        fun2<Iterable<Double>, Double, Array<out Double>>(Expect<Iterable<Double>>::toContain),
        fun2<Iterable<Double?>, Double?, Array<out Double?>>(Expect<Iterable<Double?>>::toContain),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription =  "$toContain.$inAnyOrder.$atLeast(1).$value/$values"

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).value(a)
            else expect.toContain.inAnyOrder.atLeast(1).values(a, *aX)

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).value(a)
            else expect.toContain.inAnyOrder.atLeast(1).values(a, *aX)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.atLeast(1).value(1)
        nList = nList.toContain.inAnyOrder.atLeast(1).value(1)
        subList = subList.toContain.inAnyOrder.atLeast(1).value(1)
        star = star.toContain.inAnyOrder.atLeast(1).value(1)

        list = list.toContain.inAnyOrder.atLeast(1).values(1, 1.2)
        nList = nList.toContain.inAnyOrder.atLeast(1).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.atLeast(1).values(1, 2.2)
        star = star.toContain.inAnyOrder.atLeast(1).values(1, 1.2, "asdf")

        list = list.toContain(1)
        nList = nList.toContain(1)
        subList = subList.toContain(1)
        star = star.toContain(1)

        list = list.toContain(1, 1.2)
        nList = nList.toContain(1, 1.2)
        subList = subList.toContain(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.toContain("asdf")
        star = star.toContain(1, 1.2, "asdf")
    }
}
