package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1

class IterableAllAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    fun1(Expect<Iterable<Double>>::all),
    fun1(Expect<Iterable<Double?>>::all),
    "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
)
