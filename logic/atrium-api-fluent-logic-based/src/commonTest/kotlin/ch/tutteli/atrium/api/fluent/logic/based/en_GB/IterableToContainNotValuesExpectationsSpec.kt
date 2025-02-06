package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.IterableToContainNotValuesExpectationsSpec.Companion as C

class IterableToContainNotValuesExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        functionDescription to C::notToContainFun,
        (functionDescription to C::notToContainNullableFun).withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableNotToContainValuesExpectationsSpec(
        fun2<Iterable<Double>,Double, Array<out Double>>(Expect<Iterable<Double>>::notToContain),
        fun2<Iterable<Double?>,Double?, Array<out Double?>>(Expect<Iterable<Double?>>::notToContain).withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Shortcut] "
    )

    companion object : IterableToContainSpecBase() {
        private val functionDescription = "$notToContain.$value/$values"

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()


        list = list.notToContain.value(1)
        nList = nList.notToContain.value(1)
        subList = subList.notToContain.value(1)
        star = star.notToContain.value(1)

        list = list.notToContain.values(1, 1.2)
        nList = nList.notToContain.values(1, 1.2)
        subList = subList.notToContain.values(1, 2.2)
        star = star.notToContain.values(1, 1.2, "asdf")

        list = list.notToContain(1, 1.2)
        nList = nList.notToContain(1, 1.2)
        subList = subList.notToContain(1, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.notToContain("asdf")
        star = star.notToContain(1, 1.2, "asdf")
    }
}
