@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableAllAssertionsSpec: ch.tutteli.atrium.spec.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    Assert<Iterable<Double>>::alle.name to Assert<Iterable<Double>>::alle,
    Assert<Iterable<Double?>>::alle.name to Assert<Iterable<Double?>>::alle,
    "* ", "(!) ", "- ", "» ", ">> ", "=> "
)
