package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.impl.GroupingBasedTestExecutable

actual typealias TestFactory = kotlin.test.Test


actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): Any = turnTestNodesIntoExpectGrouping(testNodes, testExecutableFactory as () -> GroupingBasedTestExecutable<*>)

//turnIntoMochaDescribeIt(testNodes, testExecutableFactory, isFirstDescribe = true)

fun <TestExecutableT : TestExecutable> turnIntoMochaDescribeIt(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT,
    isFirstDescribe: Boolean
): Any = testNodes.forEach { node ->
    when (node) {
        is BranchTestNode ->
            if (isFirstDescribe) {
                describe(node.displayName) {
                    turnIntoMochaDescribeIt(node.nodes, testExecutableFactory, false)
                }
            } else {
                collectDescribeAndOutputSingleTest(node.displayName, node.nodes, testExecutableFactory)
            }

        is LeafTestNode<*> -> it(node.displayName) {
            testExecutableFactory().execute(node)
        }
    }
}

fun <TestExecutableT : TestExecutable> collectDescribeAndOutputSingleTest(
    displayName: String,
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT,
) {
    testNodes.forEach { node ->
        when (node) {
            is BranchTestNode ->
                collectDescribeAndOutputSingleTest(
                    displayName + " - " + node.displayName,
                    node.nodes,
                    testExecutableFactory
                )

            is LeafTestNode<*> -> it(displayName + " - " + node.displayName) {
                testExecutableFactory().execute(node)
            }
        }
    }
}

external fun describe(name: String, block: () -> Unit)
external fun it(name: String, block: () -> Unit)
