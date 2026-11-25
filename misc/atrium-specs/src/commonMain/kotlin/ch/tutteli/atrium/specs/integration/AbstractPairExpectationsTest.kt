package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.integration.utils.keyValueLikeTestFactory
import ch.tutteli.atrium.testfactories.PlatformTestNode
import ch.tutteli.atrium.testfactories.PlatformTestNodeContainer
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractPairExpectationsTest(
    private val firstFeatureSpec: Feature0<Pair<String, Int>, String>,
    private val firstSpec: Fun1<Pair<String, Int>, Expect<String>.() -> Unit>,
    private val secondFeatureSpec: Feature0<Pair<String, Int>, Int>,
    private val secondSpec: Fun1<Pair<String, Int>, Expect<Int>.() -> Unit>,
    private val nullableFirstFeatureSpec: Feature0<Pair<String?, Int?>, String?>,
    private val nullableFirstSpec: Fun1<Pair<String?, Int?>, Expect<String?>.() -> Unit>,
    private val nullableSecondFeatureSpec: Feature0<Pair<String?, Int?>, Int?>,
    private val nullableSecondSpec: Fun1<Pair<String?, Int?>, Expect<Int?>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun keyValueLike() = keyValueLikeTestFactory(
        ::Pair,
        ::Pair,
        "first",
        "second",
        firstFeatureSpec,
        firstSpec,
        secondFeatureSpec,
        secondSpec,
        nullableFirstFeatureSpec,
        nullableFirstSpec,
        nullableSecondFeatureSpec,
        nullableSecondSpec
    )
}
