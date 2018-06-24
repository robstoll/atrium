package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object CollectingAssertionPlantSpec : ch.tutteli.atrium.spec.creating.CollectingAssertionPlantSpec(
    AssertionVerbFactory, ::CollectingAssertionPlantImpl)
