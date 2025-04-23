package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.junit.turnIntoJunitDynamicNodes

actual typealias TestFactory = org.junit.jupiter.api.TestFactory

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): Any = turnIntoJunitDynamicNodes(testNodes, testExecutableFactory)
