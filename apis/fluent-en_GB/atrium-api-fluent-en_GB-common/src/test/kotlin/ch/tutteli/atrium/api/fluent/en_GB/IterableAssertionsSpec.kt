package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0

object IterableAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAssertionsSpec(
    fun0(Expect<Iterable<Int>>::hasNext),
    fun0(Expect<Iterable<Int>>::hasNotNext)
)
