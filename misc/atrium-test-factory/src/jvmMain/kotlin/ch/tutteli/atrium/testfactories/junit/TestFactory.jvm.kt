package ch.tutteli.atrium.testfactories.junit

import ch.tutteli.atrium.testfactories.*
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicTest.dynamicTest

fun <TestExecutableT : TestExecutable> turnIntoJunitDynamicNodes(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): List<org.junit.jupiter.api.DynamicNode> =
    testNodes.map { node ->
        when (node) {
            is BranchTestNode -> dynamicContainer(
                node.displayName,
                // I doubt we will run into stack-overflow issues as the nesting is normally very limited.
                // If we should run into stack-overflow issues, then we could turn this into a tailrec function
                // using an own stack
                turnIntoJunitDynamicNodes(node.nodes, testExecutableFactory)
            )

            is LeafTestNode<*> -> dynamicTest(node.displayName) {
                testExecutableFactory().execute(node)
            }
        }
    }
