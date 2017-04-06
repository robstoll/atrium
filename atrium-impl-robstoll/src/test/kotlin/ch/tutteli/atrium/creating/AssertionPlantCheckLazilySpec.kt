package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory

object AssertionPlantCheckLazilySpec : ch.tutteli.atrium.test.creating.AssertionPlantCheckLazilySpec(
    AssertionVerbFactory, AtriumFactory::newCheckLazily)
