package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1

abstract class MapEntryFeatureAssertionsSpec(
    keyFeature: Feature0<Map.Entry<String, Int>, String>,
    key: Fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>,
    valueFeature: Feature0<Map.Entry<String, Int>, Int>,
    value: Fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<Map.Entry<String?, Int?>, String?>,
    nullableKey: Fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>,
    nullableValueFeature: Feature0<Map.Entry<String?, Int?>, Int?>,
    nullableValue: Fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : KeyValueLikeFeatureAssertionsSpec<Map.Entry<String, Int>, Map.Entry<String?, Int?>>(
    ::mapEntry,
    ::mapEntry,
    "key",
    "value",
    keyFeature,
    key,
    valueFeature,
    value,
    nullableKeyFeature,
    nullableKey,
    nullableValueFeature,
    nullableValue,
    describePrefix
)
