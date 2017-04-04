package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory

class AssertionPlantCheckLazilySpec : ch.tutteli.atrium.test.creating.AssertionPlantCheckLazilySpec(
    AssertionVerbFactory, AtriumFactory::newCheckLazily)
