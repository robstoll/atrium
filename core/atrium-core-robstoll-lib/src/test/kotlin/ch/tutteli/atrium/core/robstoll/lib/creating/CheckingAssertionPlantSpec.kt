package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object CheckingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CheckingAssertionPlantSpec(
    AssertionVerbFactory, ::CheckingAssertionPlantImpl)
