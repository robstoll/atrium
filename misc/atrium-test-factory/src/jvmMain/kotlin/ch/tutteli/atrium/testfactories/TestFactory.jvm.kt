package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.junit.turnIntoJunitDynamicNodes
import org.junit.jupiter.api.DynamicNode

actual typealias TestFactory = org.junit.jupiter.api.TestFactory
actual typealias PlatformTestNodeContainer<T> = IterableDynamicNodeWrapper<T>
actual typealias PlatformTestNode = DynamicNode

interface IterableDynamicNodeWrapper<T : DynamicNode> : Iterable<DynamicNode>

class DefaultIterableDynamicNodeWrapper<T : DynamicNode>(list: List<DynamicNode>) : IterableDynamicNodeWrapper<T>,
    Iterable<DynamicNode> by list

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): PlatformTestNodeContainer<PlatformTestNode> = turnIntoJunitDynamicNodes(testNodes, testExecutableFactory)
