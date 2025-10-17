package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*

object CollectionExpectationsTest : ch.tutteli.atrium.specs.integration.CollectionExpectationsSpec(
    fun0(Expect<Collection<Int>>::toBeEmpty),
    fun0(Expect<Collection<Int>>::notToBeEmpty),
    property<Collection<Int>, Int>(Expect<Collection<Int>>::size),
    fun1<Collection<Int>, Expect<Int>.() -> Unit>(Expect<Collection<Int>>::size)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<Set<Int?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1.toBeEmpty()
        a1.notToBeEmpty()

        a1b.toBeEmpty()
        a1b.notToBeEmpty()

        star.toBeEmpty()
        star.notToBeEmpty()

        a1.toHaveSize(2)
        a1b.toHaveSize(2)
        star.toHaveSize(2)

        a1.size
        a1 = a1.size { }

        a1b.size
        a1b = a1b.size { }

        star.size
        star = star.size { }
    }
}
