package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.notImplemented

object IterableAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAssertionsSpec(
    fun0(Expect<Iterable<Int>>::hasNext),
    fun0(Expect<Iterable<Int>>::hasNotNext),
    fun0(Expect<Iterable<Int>>::containsNoDuplicates)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Double>> = notImplemented()
        var a1b: Expect<Set<Double?>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        a1 = a1.hasNext()
        a1 = a1.hasNotNext()
        a1 = a1.containsNoDuplicates()

        a1b = a1b.hasNext()
        a1b = a1b.hasNotNext()
        a1b = a1b.containsNoDuplicates()

        star = star.hasNext()
        star = star.hasNotNext()
        star = star.containsNoDuplicates()
    }
}
