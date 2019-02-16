package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.map.get.builders.MapGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1
import ch.tutteli.atrium.verbs.internal.assert
import java.lang.IllegalArgumentException

class MapFeatureAssertionsSpec : Spek({

    include(object : ch.tutteli.atrium.spec.integration.MapFeatureAssertionsSpec(
        AssertionVerbFactory,
        keysVal.name to keysVal,
        keysFun.name to MapFeatureAssertionsSpec.Companion::keys,
        valuesVal.name to valuesVal,
        valuesFun.name to MapFeatureAssertionsSpec.Companion::values,
        getExistingPlantFun.name to MapFeatureAssertionsSpec.Companion::getExistingPlant,
        getExistingFun.name to MapFeatureAssertionsSpec.Companion::getExisting,
        getExistingNullablePlantFun.name to MapFeatureAssertionsSpec.Companion::getExistingNullablePlant,
        getExistingNullableFun.name to MapFeatureAssertionsSpec.Companion::getExistingNullable
    ) {})


    describeFun("", arrayOf("getExisting for nullable")){
        test("throws if no assertion is made for existing key") {
            expect {
                assert(mapOf(null as String? to 1)) getExisting Key(null) assertIt {}
            }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
        }
        test("throws if no assertion is made for non-existing key") {
            expect {
                assert(mapOf(null as String? to 1)) getExisting Key("a") assertIt {}
            //TODO change to IllegalStateException in v1.0.0
            }.toThrow<IllegalArgumentException> { messageContains("There was not any assertion created") }
        }
    }
}) {
    companion object {
        val keysVal: KProperty1<Assert<Map<String, Int>>, Assert<Set<String>>> = Assert<Map<String, Int>>::keys
        val keysFun: KFunction2<Assert<Map<String, Int>>, Assert<Set<String>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::keys
        val valuesVal: KProperty1<Assert<Map<String, Int>>, Assert<Collection<Int>>> = Assert<Map<String, Int>>::values
        val valuesFun: KFunction2<Assert<Map<String, Int>>, Assert<Collection<Int>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::values
        val getExistingPlantFun: KFunction2<Assert<Map<String, Int>>, String, Assert<Int>> = Assert<Map<String, Int>>::getExisting
        val getExistingFun: KFunction2<Assert<Map<String, Int>>, Key<String>, MapGetOption<String, Int, Map<String, Int>>> = Assert<Map<String, Int>>::getExisting
        val getExistingNullablePlantFun: KFunction2<Assert<Map<String, Int?>>, String, AssertionPlantNullable<Int?>> = Assert<Map<String, Int?>>::getExisting
        val getExistingNullableFun: KFunction2<Assert<Map<String, Int?>>, Key<String>, MapGetNullableOption<String, Int?, Map<String, Int?>>> = Assert<Map<String, Int?>>::getExisting

        fun keys(plant: Assert<Map<String, Int>>, assertionCreator: Assert<Set<String>>.() -> Unit)
            = plant keys { assertionCreator() }

        fun values(plant: Assert<Map<String, Int>>, assertionCreator: Assert<Collection<Int>>.() -> Unit)
            = plant values { assertionCreator() }

        fun getExistingPlant(plant: Assert<Map<String, Int>>, key: String)
            = plant getExisting key

        fun getExisting(plant: Assert<Map<String, Int>>, key: String, assertionCreator: Assert<Int>.() -> Unit)
            = plant getExisting Key(key) assertIt { assertionCreator() }

        fun getExistingNullablePlant(plant: Assert<Map<String, Int?>>, key: String)
            = plant getExisting key

        fun getExistingNullable(plant: Assert<Map<String, Int?>>, key: String, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit)
            = plant getExisting Key(key) assertIt { assertionCreator()  }
    }
}
