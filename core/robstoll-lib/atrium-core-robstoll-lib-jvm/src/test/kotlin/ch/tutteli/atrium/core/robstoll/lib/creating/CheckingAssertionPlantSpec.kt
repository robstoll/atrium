package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object CheckingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CheckingAssertionPlantSpec(
    AssertionVerbFactory, { a -> CheckingAssertionPlantImpl { a } })
