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
        val a1: Expect<List<Int>> = notImplemented()
        val a1b: Expect<Set<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1.isEmpty()
        a1.isNotEmpty()

        a1b.isEmpty()
        a1b.isNotEmpty()

        star.isEmpty()
        star.isNotEmpty()
    }
}
