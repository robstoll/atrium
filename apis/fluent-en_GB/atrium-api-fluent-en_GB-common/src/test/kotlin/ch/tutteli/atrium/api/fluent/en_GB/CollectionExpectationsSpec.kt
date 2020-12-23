package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    fun0(Expect<Collection<Int>>::isEmpty),
    fun0(Expect<Collection<Int>>::isNotEmpty),
    property<Collection<Int>, Int>(Expect<Collection<Int>>::size),
    fun1<Collection<Int>, Expect<Int>.() -> Unit>(Expect<Collection<Int>>::size)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<Set<Int?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1.isEmpty()
        a1.isNotEmpty()

        a1b.isEmpty()
        a1b.isNotEmpty()

        star.isEmpty()
        star.isNotEmpty()

        a1.size
        a1 = a1.size { }

        a1b.size
        a1b = a1b.size { }

        star.size
        star = star.size { }
    }
}
