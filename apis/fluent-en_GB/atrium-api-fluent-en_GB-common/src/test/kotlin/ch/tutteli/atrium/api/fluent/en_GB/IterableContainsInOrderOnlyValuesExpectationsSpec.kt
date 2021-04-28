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
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        functionDescription to C::toContainInOrderOnlyValues,
        (functionDescription to C::toContainInOrderOnlyNullableValues).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyValuesExpectationsSpec(
        fun2<Iterable<Double>, Double, Array<out Double>>(Expect<Iterable<Double>>::toContainExactly),
        fun2<Iterable<Double?>, Double?, Array<out Double?>>(Expect<Iterable<Double?>>::toContainExactly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$value/$values"

        private fun toContainInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
            else expect.toContain.inOrder.only.values(a, *aX)

        private fun toContainInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inOrder.only.value(a)
            else expect.toContain.inOrder.only.values(a, *aX)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.toContain.inOrder.only.value(1)
        nList = nList.toContain.inOrder.only.value(1)
        subList = subList.toContain.inOrder.only.value(1)
        star = star.toContain.inOrder.only.value(1)

        list = list.toContain.inOrder.only.values(1, 1.2)
        nList = nList.toContain.inOrder.only.values(1, 1.2)
        subList = subList.toContain.inOrder.only.values(1, 2.2)
        star = star.toContain.inOrder.only.values(1, 1.2, "asdf")

        list = list.toContainExactly(1)
        nList = nList.toContainExactly(1)
        subList = subList.toContainExactly(1)
        star = star.toContainExactly(1)

        list = list.toContainExactly(1, 1.2)
        nList = nList.toContainExactly(1, 1.2)
        subList = subList.toContainExactly(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.toContainExactly("asdf")
        star = star.toContainExactly(1, 1.2, "asdf")
    }
}

