package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

object AssertionPlantCheckLazilySpec : ch.tutteli.atrium.spec.creating.AssertionPlantCheckLazilySpec(
    AssertionVerbFactory, ::AssertionPlantCheckLazily)
