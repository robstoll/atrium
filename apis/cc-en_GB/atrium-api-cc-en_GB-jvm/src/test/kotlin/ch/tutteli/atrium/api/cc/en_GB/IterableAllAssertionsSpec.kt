@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableAllAssertionsSpec: ch.tutteli.atrium.spec.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    Assert<Iterable<Double>>::all.name to Assert<Iterable<Double>>::all,
    Assert<Iterable<Double?>>::all.name to Assert<Iterable<Double?>>::all,
    "◆ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ "
)
