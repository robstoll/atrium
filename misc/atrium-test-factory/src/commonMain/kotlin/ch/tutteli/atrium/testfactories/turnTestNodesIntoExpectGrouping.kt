package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.testfactories.impl.GroupingBasedTestExecutable
import ch.tutteli.atrium.testfactories.impl.executeAndAppendExceptionAsAssertion

/**
 * Turns the given [testNodes] into one [ExpectGrouping] with corresponding subgroups.
 *
 * @return The created [ExpectGrouping].
 *
 * @since 1.3.0
 */
fun <GroupingBasedTestExecutableT : GroupingBasedTestExecutable<*>> turnTestNodesIntoExpectGrouping(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> GroupingBasedTestExecutableT
): ExpectGrouping {
    val testExecutable = testExecutableFactory()
    val expectationVerbs = testExecutable.expectationVerbs

    return expectationVerbs.expectGrouped {
        processNodes(testNodes, testExecutable)
    }
}

private fun ExpectGrouping.processNodes(testNodes: List<TestNode>, testExecutable: GroupingBasedTestExecutable<*>) {
    testNodes.forEach { node ->
        when (node) {
            is BranchTestNode -> _logicAppend {
                grouping(node.displayName, Text.EMPTY_PROVIDER) {
                    // I doubt we will run into stack-overflow issues as the nesting is normally very limited.
                    // If we should run into stack-overflow issues, then we could turn this into a tailrec function
                    // using an own stack
                    processNodes(node.nodes, testExecutable)
                }
            }

            is LeafTestNode<*> -> _logicAppend {
                grouping(node.displayName, Text.EMPTY_PROVIDER) {
                    executeAndAppendExceptionAsAssertion {
                        testExecutable.testFactory(this).execute(node)
                    }
                }
            }
        }
    }
}
