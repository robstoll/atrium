package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.mocha.turnIntoDescribeIt

actual typealias TestFactory = kotlin.test.Test

actual interface DynamicNodeContainer<T : DynamicNodeLike>
actual abstract class DynamicNodeLike
object UnitDynamicNodeContainer : DynamicNodeContainer<DynamicNodeLike>

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): DynamicNodeContainer<DynamicNodeLike> = turnIntoDescribeIt(testNodes, testExecutableFactory, isFirstDescribe = true)
