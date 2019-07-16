package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class IterableAllAssertionsSpec: ch.tutteli.atrium.specs.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    Expect<Iterable<Double>>::all.name to Expect<Iterable<Double>>::all,
    Expect<Iterable<Double?>>::all.name to Expect<Iterable<Double?>>::all,
    "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
)
