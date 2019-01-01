package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class ListFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    Assert<List<Int>>::get.name to Companion::get,
    Assert<List<Int>>::getNullable.name to Companion::getNullable

){
    companion object {

        fun get(plant: Assert<List<Int>>, index: Int, assertionCreator: Assert<Int>.() -> Unit)
            = plant get index assertIt { assertionCreator() }

        fun getNullable(plant: Assert<List<Int?>>, index: Int, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit)
            = plant getNullable index assertIt { assertionCreator() }
    }
}
