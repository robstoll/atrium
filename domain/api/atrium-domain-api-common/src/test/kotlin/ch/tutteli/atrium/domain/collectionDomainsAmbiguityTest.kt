package ch.tutteli.atrium.domain

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.specs.notImplemented

@Suppress("unused")
private fun ambiguityTest() {
    val fluent: Expect<Collection<Int>> = notImplemented()

    //collection
    fluent._domain.size

    //iterable
    fluent._domain.hasNext()

    //iterable comparable
    fluent._domain.min()
}
