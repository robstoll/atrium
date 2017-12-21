package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

object CheckingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CheckingAssertionPlantSpec(
    AssertionVerbFactory, ::CheckingAssertionPlantImpl)
