package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInAnyOrderAtLeast1ValuesExpectationsSpec.Companion as C

class IterableContainsInAnyOrderAtLeast1ValuesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesExpectationsSpec(
        functionDescription to C::containsValues,
        (functionDescription to C::containsNullableValues).withNullableSuffix(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesExpectationsSpec(
        fun2<Iterable<Double>, Double, Array<out Double>>(Expect<Iterable<Double>>::contains),
        fun2<Iterable<Double?>, Double?, Array<out Double?>>(Expect<Iterable<Double?>>::contains),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription =  "$contains.$inAnyOrder.$atLeast(1).$value/$values"

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.atLeast(1).value(a)
            else expect.contains.inAnyOrder.atLeast(1).values(a, *aX)

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.atLeast(1).value(a)
            else expect.contains.inAnyOrder.atLeast(1).values(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.atLeast(1).value(1)
        nList = nList.contains.inAnyOrder.atLeast(1).value(1)
        subList = subList.contains.inAnyOrder.atLeast(1).value(1)
        star = star.contains.inAnyOrder.atLeast(1).value(1)

        list = list.contains.inAnyOrder.atLeast(1).values(1, 1.2)
        nList = nList.contains.inAnyOrder.atLeast(1).values(1, 1.2)
        subList = subList.contains.inAnyOrder.atLeast(1).values(1, 2.2)
        star = star.contains.inAnyOrder.atLeast(1).values(1, 1.2, "asdf")

        list = list.contains(1)
        nList = nList.contains(1)
        subList = subList.contains(1)
        star = star.contains(1)

        list = list.contains(1, 1.2)
        nList = nList.contains(1, 1.2)
        subList = subList.contains(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.contains("asdf")
        star = star.contains(1, 1.2, "asdf")
    }
}
