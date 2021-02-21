package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsInOrderOnlyValuesExpectationsSpec.Companion as C

class IterableContainsInOrderOnlyValuesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::containsInOrderOnlyValues,
        (functionDescription to C::containsInOrderOnlyNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesExpectationsSpec(
        fun2<Iterable<Double>, Double, Array<out Double>>(Expect<Iterable<Double>>::containsExactly),
        fun2<Iterable<Double?>, Double?, Array<out Double?>>(Expect<Iterable<Double?>>::containsExactly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$value/$values"

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.value(a)
            else expect.contains.inOrder.only.values(a, *aX)

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.value(a)
            else expect.contains.inOrder.only.values(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.contains.inOrder.only.value(1)
        nList = nList.contains.inOrder.only.value(1)
        subList = subList.contains.inOrder.only.value(1)
        star = star.contains.inOrder.only.value(1)

        list = list.contains.inOrder.only.values(1, 1.2)
        nList = nList.contains.inOrder.only.values(1, 1.2)
        subList = subList.contains.inOrder.only.values(1, 2.2)
        star = star.contains.inOrder.only.values(1, 1.2, "asdf")

        list = list.containsExactly(1)
        nList = nList.containsExactly(1)
        subList = subList.containsExactly(1)
        star = star.containsExactly(1)

        list = list.containsExactly(1, 1.2)
        nList = nList.containsExactly(1, 1.2)
        subList = subList.containsExactly(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.containsExactly("asdf")
        star = star.containsExactly(1, 1.2, "asdf")
    }
}

