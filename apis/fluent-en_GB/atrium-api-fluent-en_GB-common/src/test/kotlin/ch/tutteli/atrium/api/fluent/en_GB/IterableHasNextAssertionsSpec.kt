package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0

object IterableHasNextAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableHasNextAssertionsSpec(
    fun0(Expect<Iterable<Int>>::hasNext)
)
