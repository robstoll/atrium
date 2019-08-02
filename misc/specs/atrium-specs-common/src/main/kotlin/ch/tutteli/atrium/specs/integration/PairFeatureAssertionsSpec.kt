package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory

abstract class PairFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    firstValPair: Feature0<Pair<String, Int>, String>,
    firstFunPair: Fun1<Pair<String, Int>, Expect<String>.() -> Unit>,
    secondValPair: Feature0<Pair<String, Int>, Int>,
    secondFunPair: Fun1<Pair<String, Int>, Expect<Int>.() -> Unit>,
    nullableFirstValPair: Feature0<Pair<String?, Int?>, String?>,
    nullableSecondValPair: Feature0<Pair<String?, Int?>, Int?>,
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
