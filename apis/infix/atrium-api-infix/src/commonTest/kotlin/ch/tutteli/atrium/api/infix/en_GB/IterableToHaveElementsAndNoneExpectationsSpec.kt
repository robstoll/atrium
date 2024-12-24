package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek

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

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToHaveElementsAndNoneExpectationsSpec(
        getNotToContainPair(),
        getNotToContainNullablePair().withNullableSuffix(),
        Expect<List<Int>>::notToHaveElementsOrNone.name,
        "[Atrium][Builder] "
    )

    companion object : IterableToContainSpecBase() {

        private fun getNotToContainPair() = notToContain to Companion::notToContainFun

        private fun notToContainFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect notToContain o entry a

        private fun getNotToContainNullablePair() = notToContain to Companion::notToContainNullableFun

        private fun notToContainNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect notToContain o entry a
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<out Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toHaveElementsAndNone {}
        nList = nList toHaveElementsAndNone {}
        subList = subList toHaveElementsAndNone {}
        star = star toHaveElementsAndNone {}

        nList = nList toHaveElementsAndNone null
        subList = subList toHaveElementsAndNone null
        star = star toHaveElementsAndNone null
    }
}
