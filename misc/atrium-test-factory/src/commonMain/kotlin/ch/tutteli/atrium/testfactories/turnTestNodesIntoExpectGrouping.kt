package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.testfactories.impl.ExpectGroupingBasedTestExecutable
import ch.tutteli.atrium.testfactories.impl.executeAndAppendExceptionAsAssertion

/**
 * Turns the given [testNodes] into one [ExpectGrouping] with corresponding subgroups and uses
 * [ExpectationVerbs.expectInExpectGrouped] as [TestExecutable.expect].
 *
 * @return The created [ExpectGrouping].
 *
 * @since 1.3.0
 */
fun turnTestNodesIntoExpectGrouping(
    testNodes: List<TestNode>,
    expectationVerbs: ExpectationVerbs
): ExpectGrouping {
    // padding null as description means use the default
    return expectationVerbs.expectGrouped(description = null) {
        processNodes(testNodes, expectationVerbs)
    }
}

private fun ExpectGrouping.processNodes(testNodes: List<TestNode>, expectationVerbs: ExpectationVerbs) {
    testNodes.forEach { node ->
        when (node) {
            is BranchTestNode -> _logicAppend {
                grouping(node.displayName, Text.EMPTY_PROVIDER) {
                    // I doubt we will run into stack-overflow issues as the nesting is normally very limited.
                    // If we should run into stack-overflow issues, then we could turn this into a tailrec function
                    // using an own stack
                    processNodes(node.nodes, expectationVerbs)
                }
            }

            is LeafTestNode -> _logicAppend {
                grouping(node.displayName, Text.EMPTY_PROVIDER) {
                    executeAndAppendExceptionAsAssertion {
                        node.executable(ExpectGroupingBasedTestExecutable(this, expectationVerbs))
                    }
                }
            }
        }
    }
}
