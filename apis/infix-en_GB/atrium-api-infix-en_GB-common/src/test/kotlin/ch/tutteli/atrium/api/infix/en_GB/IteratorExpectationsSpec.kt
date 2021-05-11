package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction2

class IteratorExpectationsSpec : ch.tutteli.atrium.specs.integration.IteratorExpectationsSpec(
    getToHaveNextPair(),
    getNotToHaveNextPair()
) {
    companion object {
        private val toHave: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> = Expect<Iterator<Int>>::toHave
        private fun getToHaveNextPair() = "${toHave.name} ${next::class.simpleName}" to Companion::toHaveNext
        private fun toHaveNext(expect: Expect<Iterator<Int>>) = expect toHave next

        private val notToHave: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::notToHave

        private fun getNotToHaveNextPair() = "${notToHave.name} ${next::class.simpleName}" to Companion::notToHaveNext

        private fun notToHaveNext(expect: Expect<Iterator<Int>>) = expect notToHave next
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()

        a1 = a1 toHave next
        a1 = a1 notToHave next
    }
}
