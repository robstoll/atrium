package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.junit.turnIntoJunitDynamicNodes
import org.junit.jupiter.api.DynamicNode

actual typealias TestFactory = org.junit.jupiter.api.TestFactory
actual typealias DynamicNodeContainer<T> = IterableDynamicNodeWrapper<T>
actual typealias DynamicNodeLike = DynamicNode

interface IterableDynamicNodeWrapper<T : DynamicNode> : Iterable<DynamicNode>

class DefaultIterableDynamicNodeWrapper<T : DynamicNode>(list: List<DynamicNode>) : IterableDynamicNodeWrapper<T>,
    Iterable<DynamicNode> by list

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): DynamicNodeContainer<DynamicNodeLike> = turnIntoJunitDynamicNodes(testNodes, testExecutableFactory)
