package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableAllAssertionsSpec: ch.tutteli.atrium.spec.integration.IterableAllAssertionsSpec(
    AssertionVerbFactory,
    Assert<Iterable<Double>>::alle.name to Assert<Iterable<Double>>::alle,
    Assert<Iterable<Double?>>::alleDerNullable.name to Assert<Iterable<Double?>>::alleDerNullable,
    "* ", "(!) ", "- ", "» ", ">> ", "=> "
)
