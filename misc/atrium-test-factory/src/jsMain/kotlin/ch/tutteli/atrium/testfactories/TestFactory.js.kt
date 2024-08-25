package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectationVerbs

actual typealias TestFactory = kotlin.test.Test

actual fun turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    expectationVerbs: ExpectationVerbs
): Any = turnTestNodesIntoExpectGrouping(testNodes, expectationVerbs)
