package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.*

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

        @Suppress("unused", "UNUSED_VALUE")
        private fun ambiguityTest() {
            var a1: Expect<Map<String, Int>> = notImplemented()
            var a2: Expect<Map<out String, Int>> = notImplemented()
            var a3: Expect<out Map<String, Int>> = notImplemented()
            var a4: Expect<out Map<out String, Int>> = notImplemented()

            a1.getExisting("a")
            a1 = a1.getExisting("a") { }
            a2.getExisting("a")
            a2 = a2.getExisting("a") { }
            a3.getExisting("a")
            a3 = a3.getExisting("a") { }
            a4.getExisting("a")
            a4 = a4.getExisting("a") { }
        }
    }
}
