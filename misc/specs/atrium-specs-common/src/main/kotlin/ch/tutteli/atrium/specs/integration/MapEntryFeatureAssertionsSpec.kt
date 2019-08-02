package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory

abstract class MapEntryFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keyValPair: Feature0<Map.Entry<String, Int>, String>,
    keyFunPair: Fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>,
    valueValPair: Feature0<Map.Entry<String, Int>, Int>,
    valueFunPair: Fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>,
    nullableKeyValPair: Feature0<Map.Entry<String?, Int?>, String?>,
    nullableValueValPair: Feature0<Map.Entry<String?, Int?>, Int?>,
    describePrefix: String = "[Atrium] "
) : KeyValueLikeFeatureAssertionsSpec<Map.Entry<String, Int>, Map.Entry<String?, Int?>>(
    verbs,
    ::mapEntry,
    ::mapEntry,
    "key",
    "value",
    keyValPair,
    keyFunPair,
    valueValPair,
    valueFunPair,
    nullableKeyValPair,
    nullableValueValPair,
    describePrefix
)
