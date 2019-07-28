package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.property

class MapFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.MapFeatureAssertionsSpec(
    AssertionVerbFactory,
    property<Map<String, Int>, Set<String>>(Expect<Map<String, Int>>::keys),
    fun1<Map<String, Int>, Expect<Set<String>>.() -> Unit>(Expect<Map<String, Int>>::keys),
    property<Map<String, Int>, Collection<Int>>(Expect<Map<String, Int>>::values),
    fun1<Map<String, Int>, Expect<Collection<Int>>.() -> Unit>(Expect<Map<String, Int>>::values),
    feature1<Map<String, Int>, String, Int>(Expect<Map<String, Int>>::getExisting),
    fun2<Map<String, Int>, String,  Expect<Int>.() -> Unit>(Expect<Map<String, Int>>::getExisting),
    feature1<Map<String?, Int?>, String?, Int?>(Expect<Map<String?, Int?>>::getExisting),
    "getExistingNullable fun with creator not implemented in this API" to Companion::getExistingNullable
){
    companion object {

        fun getExistingNullable(expect: Expect<Map<String?, Int?>>, key: String?, assertionCreator: Expect<Int?>.() -> Unit): Expect<Map<String?, Int?>>
            = ExpectImpl.map.getExisting(expect, key).addToInitial(assertionCreator)
    }
}
