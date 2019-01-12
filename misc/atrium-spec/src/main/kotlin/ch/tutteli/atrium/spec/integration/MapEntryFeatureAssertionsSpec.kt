package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory

abstract class MapEntryFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keyValPair: Pair<String, Assert<Map.Entry<String, Int>>.() -> Assert<String>>,
    keyFunPair: Pair<String, Assert<Map.Entry<String, Int>>.(assertionCreator: Assert<String>.() -> Unit) -> Assert<Map.Entry<String, Int>>>,
    valueValPair: Pair<String, Assert<Map.Entry<String, Int>>.() -> Assert<Int>>,
    valueFunPair: Pair<String, Assert<Map.Entry<String, Int>>.(assertionCreator: Assert<Int>.() -> Unit) -> Assert<Map.Entry<String, Int>>>,
    nullableKeyValPair: Pair<String, Assert<Map.Entry<String?, Int?>>.() -> AssertionPlantNullable<String?>>,
    nullableKeyFunPair: Pair<String, Assert<Map.Entry<String?, Int?>>.(assertionCreator: AssertionPlantNullable<String?>.() -> Unit) -> Assert<Map.Entry<String?, Int?>>>,
    nullableValueValPair: Pair<String, Assert<Map.Entry<String?, Int?>>.() -> AssertionPlantNullable<Int?>>,
    nullableValueFunPair: Pair<String, Assert<Map.Entry<String?, Int?>>.(assertionCreator: AssertionPlantNullable<Int?>.() -> Unit) -> Assert<Map.Entry<String?, Int?>>>,
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
    nullableKeyFunPair,
    nullableValueValPair,
    nullableValueFunPair,
    describePrefix
)
