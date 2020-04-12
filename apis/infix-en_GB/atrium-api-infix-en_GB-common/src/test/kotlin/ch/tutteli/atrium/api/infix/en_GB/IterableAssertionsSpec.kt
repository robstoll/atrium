package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

class IterableAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAssertionsSpec(
    getHasNextPair(),
    getHasNotNextPair()
) {
    companion object : WithAsciiReporter() {
        private val has: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> = Expect<Iterable<Int>>::has
        private fun getHasNextPair() = "${has.name} ${next::class.simpleName}" to Companion::hasNext
        private fun hasNext(expect: Expect<Iterable<Int>>) = expect has next

        private val hasNot: KFunction2<Expect<Iterable<Int>>, next, Expect<Iterable<Int>>> =
            Expect<Iterable<Int>>::hasNot

        private fun getHasNotNextPair() = "${hasNot.name} ${next::class.simpleName}" to Companion::hasNotNext
        private fun hasNotNext(expect: Expect<Iterable<Int>>) = expect hasNot next
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1 has next
        a1 = a1 hasNot next

        a1b = a1b has next
        a1b = a1b hasNot next

        star = star has next
        star = star hasNot next
    }
}
