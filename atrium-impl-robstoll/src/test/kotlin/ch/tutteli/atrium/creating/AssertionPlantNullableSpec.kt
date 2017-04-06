package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory

object AssertionPlantNullableSpec : ch.tutteli.atrium.test.creating.AssertionPlantNullableSpec(
        AssertionVerbFactory, AtriumFactory::newNullable)
