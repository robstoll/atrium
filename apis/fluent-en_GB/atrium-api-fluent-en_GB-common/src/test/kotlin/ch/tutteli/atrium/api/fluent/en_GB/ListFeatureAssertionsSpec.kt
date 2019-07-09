package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    getFeature.name to getFeature,
    getFun.name to getFun,
    getNullableFeatureFun.name to getNullableFeatureFun,
    "get for nullable not implement in this API" to Companion::getNullable
){
    companion object {
        val getFeature: KFunction2<Expect<List<Int>>, Int, Expect<Int>> = Expect<List<Int>>::get
        val getNullableFeatureFun: KFunction2<Expect<List<Int?>>, Int, Expect<Int?>> = Expect<List<Int?>>::get
        val getFun: KFunction3<Expect<List<Int>>, Int, Expect<Int>.() -> Unit, Expect<List<Int>>> = Expect<List<Int>>::get

        fun getNullable(plant: Expect<List<Int?>>, index: Int, assertionCreator: Expect<Int?>.() -> Unit): Expect<List<Int?>>
            = plant.addAssertion(ExpectImpl.list.get(plant, index, assertionCreator))
    }
}
