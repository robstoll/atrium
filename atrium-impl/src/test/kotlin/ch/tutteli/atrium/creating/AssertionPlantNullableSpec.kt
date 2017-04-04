package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

object AssertionPlantNullableSpec : ch.tutteli.atrium.test.creating.AssertionPlantNullableSpec(
        AssertionVerbFactory, AtriumFactory::newNullable)
