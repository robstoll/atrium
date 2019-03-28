package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory

abstract class PairFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    firstValPair: Pair<String, Assert<Pair<String, Int>>.() -> Assert<String>>,
    firstFunPair: Pair<String, Assert<Pair<String, Int>>.(assertionCreator: Assert<String>.() -> Unit) -> Assert<Pair<String, Int>>>,
    secondValPair: Pair<String, Assert<Pair<String, Int>>.() -> Assert<Int>>,
    secondFunPair: Pair<String, Assert<Pair<String, Int>>.(assertionCreator: Assert<Int>.() -> Unit) -> Assert<Pair<String, Int>>>,
    nullableFirstValPair: Pair<String, Assert<Pair<String?, Int?>>.() -> AssertionPlantNullable<String?>>,
    nullableSecondValPair: Pair<String, Assert<Pair<String?, Int?>>.() -> AssertionPlantNullable<Int?>>,
    describePrefix: String = "[Atrium] "
) : KeyValueLikeFeatureAssertionsSpec<Pair<String, Int>, Pair<String?, Int?>>(
    verbs,
    ::Pair,
    ::Pair,
    "first",
    "second",
    firstValPair,
    firstFunPair,
    secondValPair,
    secondFunPair,
    nullableFirstValPair,
    nullableSecondValPair,
    describePrefix
)
