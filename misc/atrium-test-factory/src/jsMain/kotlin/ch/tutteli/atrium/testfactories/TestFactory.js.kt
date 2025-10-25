package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.mocha.turnIntoDescribeIt

actual typealias TestFactory = kotlin.test.Test

actual interface PlatformTestNodeContainer<T : PlatformTestNode>
actual abstract class PlatformTestNode
object UnitPlatformTestNodeContainer : PlatformTestNodeContainer<PlatformTestNode>

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): PlatformTestNodeContainer<PlatformTestNode> = turnIntoDescribeIt(testNodes, testExecutableFactory, isFirstDescribe = true)
