package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek

class IterableToHaveNextAndNoneExpectationsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndNoneExpectationsSpec(
        fun1(Expect<Iterable<Double>>::toHaveNextAndNone),
        fun1(Expect<Iterable<Double?>>::toHaveNextAndNone).withNullableSuffix(),
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableToHaveNextAndNoneExpectationsSpec(
        getNotToContainPair(),
        getNotToContainNullablePair().withNullableSuffix(),
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
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.toHaveNextAndNone {}
        a1 = a1 notToContain o entry {}

        a1b = a1b toHaveNextAndNone {}
        a1b = a1b toHaveNextAndNone null
        a1b = a1b notToContain o entry {}
        a1b = a1b notToContain o entry null

        star = star.toHaveNextAndNone {}
        star = star notToContain o entry {}
    }
}
