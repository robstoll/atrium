package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.IterableContainsNotValuesExpectationsSpec.Companion as C

class IterableContainsNotValuesExpectationsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsNotValuesExpectationsSpec(
        functionDescription to C::containsNotFun,
        (functionDescription to C::containsNotNullableFun).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsNotValuesExpectationsSpec(
        fun2<Iterable<Double>,Double, Array<out Double>>(Expect<Iterable<Double>>::containsNot),
        fun2<Iterable<Double?>,Double?, Array<out Double?>>(Expect<Iterable<Double?>>::containsNot).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        private val functionDescription = "$containsNot.$value/$values"

        private fun containsNotFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.containsNot.value(a)
            else expect.containsNot.values(a, *aX)

        private fun containsNotNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.containsNot.value(a)
            else expect.containsNot.values(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.containsNot.value(1)
        nList = nList.containsNot.value(1)
        subList = subList.containsNot.value(1)
        star = star.containsNot.value(1)

        list = list.containsNot.values(1, 1.2)
        nList = nList.containsNot.values(1, 1.2)
        subList = subList.containsNot.values(1, 2.2)
        star = star.containsNot.values(1, 1.2, "asdf")

        list = list.containsNot(1, 1.2)
        nList = nList.containsNot(1, 1.2)
        subList = subList.containsNot(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.containsNot("asdf")
        star = star.containsNot(1, 1.2, "asdf")
    }
}
