package ch.tutteli.atrium.testfactories.junit

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.BranchTestNode
import ch.tutteli.atrium.testfactories.LeafTestNode
import ch.tutteli.atrium.testfactories.TestNode
import ch.tutteli.atrium.testfactories.impl.RootVerbTestExecutable
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicTest.dynamicTest

fun turnIntoJunitDynamicNodes(
    testNodes: List<TestNode>,
    expectationVerbs: ExpectationVerbs
): List<org.junit.jupiter.api.DynamicNode> =
    testNodes.map { node ->
        when (node) {
            is BranchTestNode -> dynamicContainer(
                node.displayName,
                // I doubt we will run into stack-overflow issues as the nesting is normally very limited.
                // If we should run into stack-overflow issues, then we could turn this into a tailrec function
                // using an own stack
                turnIntoJunitDynamicNodes(node.nodes, expectationVerbs)
            )

            is LeafTestNode -> dynamicTest(node.displayName) {
                node.executable(RootVerbTestExecutable(expectationVerbs))
            }
        }
    }
