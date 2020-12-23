package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek

class IterableNoneAssertionsSpec : Spek({

    include(PredicateSpec)
    include(BuilderSpec)

}) {
    object PredicateSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        fun1(Expect<Iterable<Double>>::none),
        fun1(Expect<Iterable<Double?>>::none).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Predicate] "
    )

    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableNoneAssertionsSpec(
        getContainsNotPair(),
        getContainsNotNullablePair().withNullableSuffix(),
        "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {

        private fun getContainsNotPair() = containsNot to Companion::containsNotFun

        private fun containsNotFun(expect: Expect<Iterable<Double>>, a: Expect<Double>.() -> Unit) =
            expect containsNot o entry a

        private fun getContainsNotNullablePair() = containsNot to Companion::containsNotNullableFun

        private fun containsNotNullableFun(expect: Expect<Iterable<Double?>>, a: (Expect<Double>.() -> Unit)?) =
            expect containsNot o entry a
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.none {}
        a1 = a1 containsNot o entry {}

        a1b = a1b none {}
        a1b = a1b none null
        a1b = a1b containsNot o entry {}
        a1b = a1b containsNot o entry null

        star = star.none {}
        star = star containsNot o entry {}
    }
}
