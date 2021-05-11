package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction2

class IteratorExpectationsSpec : ch.tutteli.atrium.specs.integration.IteratorExpectationsSpec(
    getToHaveNextPair(),
    getNotToHaveNextPair()
) {
    companion object {
        private val has: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> = Expect<Iterator<Int>>::has
        private fun getToHaveNextPair() = "${has.name} ${next::class.simpleName}" to Companion::toHaveNext
        private fun toHaveNext(expect: Expect<Iterator<Int>>) = expect toHave next

        private val hasNot: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::hasNot

        private fun getNotToHaveNextPair() = "${hasNot.name} ${next::class.simpleName}" to Companion::notToHaveNext

        private fun notToHaveNext(expect: Expect<Iterator<Int>>) = expect notToHave next
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1 toHave next
        a1 = a1 notToHave next
    }
}
