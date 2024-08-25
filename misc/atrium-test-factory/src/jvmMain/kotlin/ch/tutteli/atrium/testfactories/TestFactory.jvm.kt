package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.junit.turnIntoJunitDynamicNodes

actual typealias TestFactory = org.junit.jupiter.api.TestFactory

actual fun turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    expectationVerbs: ExpectationVerbs
): Any = turnIntoJunitDynamicNodes(testNodes, expectationVerbs)
