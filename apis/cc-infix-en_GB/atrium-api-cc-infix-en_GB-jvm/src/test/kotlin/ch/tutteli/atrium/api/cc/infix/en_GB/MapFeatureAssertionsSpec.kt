package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

class MapFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.MapFeatureAssertionsSpec(
    AssertionVerbFactory,
    keysVal.name to keysVal,
    keysFun.name to Companion::keys,
    valuesVal.name to valuesVal,
    valuesFun.name to Companion::values,
    Assert<Map<String, Int>>::getExisting.name to Companion::getExisting
){
    companion object {
        val keysVal: KProperty1<Assert<Map<String, Int>>, Assert<Set<String>>> = Assert<Map<String, Int>>::keys
        val keysFun: KFunction2<Assert<Map<String, Int>>, Assert<Set<String>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::keys
        val valuesVal: KProperty1<Assert<Map<String, Int>>, Assert<Collection<Int>>> = Assert<Map<String, Int>>::values
        val valuesFun: KFunction2<Assert<Map<String, Int>>, Assert<Collection<Int>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::values

        fun keys(plant: Assert<Map<String, Int>>, assertionCreator: Assert<Set<String>>.() -> Unit)
            = plant keys { assertionCreator() }

        fun values(plant: Assert<Map<String, Int>>, assertionCreator: Assert<Collection<Int>>.() -> Unit)
            = plant values{ assertionCreator() }

        fun getExisting(plant: Assert<Map<String, Int>>, key: String, assertionCreator: Assert<Int>.() -> Unit)
            = plant getExisting key assertIt { assertionCreator() }
    }
}
