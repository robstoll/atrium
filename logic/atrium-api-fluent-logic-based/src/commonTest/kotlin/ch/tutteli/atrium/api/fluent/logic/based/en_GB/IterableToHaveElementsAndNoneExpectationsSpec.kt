package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.IterableToHaveElementsAndNoneExpectationsSpec.Companion as C

class IterableToHaveElementsAndNoneExpectationsSpec : Spek({
    include(PredicateSpec)
    include(BuilderSpec)
}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndNoneExpectationsSpec(
        fun1(Expect<Iterable<Double>>::toHaveElementsAndNone),
        fun1(Expect<Iterable<Double?>>::toHaveElementsAndNone).withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Predicate] "
    )

    // TODO 1.3.0 #722 this will differ once we don't implement the same behaviour for notTContain and none
    // that's fine and we can simply remove this test here
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndNoneExpectationsSpec(
        functionDescription to C::notToContainFun,
        (functionDescription to C::notToContainNullableFun).withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Builder] "
    )

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$notToContain.$entry"

        private fun notToContainFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect.notToContain.entry(a)

        private fun notToContainNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect.notToContain.entry(a)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toHaveElementsAndNone {}
        nList = nList.toHaveElementsAndNone {}
        subList = subList.toHaveElementsAndNone {}
        star = star.toHaveElementsAndNone {}

        nList = nList.toHaveElementsAndNone(null)
        subList = subList.toHaveElementsAndNone(null)
        star = star.toHaveElementsAndNone(null)
    }
}
