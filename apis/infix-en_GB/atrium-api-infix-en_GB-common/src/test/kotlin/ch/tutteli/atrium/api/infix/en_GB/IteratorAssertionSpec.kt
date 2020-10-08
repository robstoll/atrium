package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

class IteratorAssertionSpec : ch.tutteli.atrium.specs.integration.IteratorAssertionSpec(
    getHasNextPair(),
    getHasNotNextPair(),
    getContainsNoDuplicatesPair()
) {
    companion object : WithAsciiReporter() {
        private val has: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> = Expect<Iterator<Int>>::has
        private fun getHasNextPair() = "${has.name} ${next::class.simpleName}" to Companion::hasNext
        private fun hasNext(expect: Expect<Iterator<Int>>) = expect has next

        private val hasNot: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::hasNot

        private fun getHasNotNextPair() = "${hasNot.name} ${next::class.simpleName}" to Companion::hasNotNext

        private fun hasNotNext(expect: Expect<Iterator<Int>>) = expect hasNot next

        private val containsDuplicates: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::containsNoDuplicates
        private fun getContainsNoDuplicatesPair() =
            "${containsDuplicates.name} ${next::class.simpleName}" to Companion::containsNoDuplicates
        private fun containsNoDuplicates(expect: Expect<Iterator<Int>>) = expect containsNoDuplicates next

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1 has next
        a1 = a1 hasNot next
        a1 = a1 containsNoDuplicates next
    }
}
