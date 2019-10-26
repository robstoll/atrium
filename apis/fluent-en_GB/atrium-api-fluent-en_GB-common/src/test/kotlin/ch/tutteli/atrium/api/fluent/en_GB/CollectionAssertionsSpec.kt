package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.notImplemented

object CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    fun0(Expect<Collection<Int>>::isEmpty),
    fun0(Expect<Collection<Int>>::isNotEmpty)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a2: Expect<out Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()
        val a2b: Expect<out Collection<Int?>> = notImplemented()

        val a3: Expect<out Collection<*>> = notImplemented()

        a1.isEmpty()
        a2.isEmpty()
        a1.isNotEmpty()
        a2.isNotEmpty()


        a1b.isEmpty()
        a2b.isEmpty()
        a1b.isNotEmpty()
        a2b.isNotEmpty()

        a3.isEmpty()
        a3.isNotEmpty()
    }
}
