package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    fun1<Collection<Int>, Empty>(Expect<Collection<Int>>::toBe).name to Companion::isEmpty,
    fun1<Collection<Int>, Empty>(Expect<Collection<Int>>::notToBe).name to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe Empty
        fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe Empty
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a2: Expect<out Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()
        val a2b: Expect<out Collection<Int?>> = notImplemented()

        val a3: Expect<out Collection<*>> = notImplemented()

        a1 toBe Empty
        a2 toBe Empty
        a1 notToBe Empty
        a2 notToBe Empty


        a1b toBe Empty
        a2b toBe Empty
        a1b notToBe Empty
        a2b notToBe Empty

        a3 toBe Empty
        a3 notToBe Empty
    }
}
