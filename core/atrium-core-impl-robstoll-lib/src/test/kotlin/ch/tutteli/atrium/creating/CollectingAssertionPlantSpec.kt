package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

object CollectingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CollectingAssertionPlantSpec(
    AssertionVerbFactory, ::CollectingAssertionPlant)
