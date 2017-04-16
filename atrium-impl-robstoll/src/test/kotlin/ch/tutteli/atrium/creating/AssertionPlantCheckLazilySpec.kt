package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory

object AssertionPlantCheckLazilySpec : ch.tutteli.atrium.spec.creating.AssertionPlantCheckLazilySpec(
    AssertionVerbFactory, AtriumFactory::newCheckLazily)
