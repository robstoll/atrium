package ch.tutteli.atrium.testfactories.expect.grouped.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable
import ch.tutteli.atrium.logic.grouping
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.testfactories.*
import ch.tutteli.atrium.testfactories.expect.grouped.GroupingBasedTestExecutable
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation

/**
 * Turns the given [testNodes] into one [ExpectGrouping] with corresponding subgroups.
 *
 * @return The created [ExpectGrouping].
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> turnTestNodesIntoExpectGrouping(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): ExpectGrouping {
    // we know, it's not safe what we are doing, we rely on a convention here which will blow up at runtime if
    // it is not followed
    @Suppress("UNCHECKED_CAST")
    val groupingBasedTestExecutableFactory = testExecutableFactory as () -> GroupingBasedTestExecutable<*>
    val testExecutable = groupingBasedTestExecutableFactory()
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


//TODO 1.3.0 rename once we append Proofs -- also, catching exceptions should be a standard behaviour for expectation
// groups, that's still something we should improve. I think then this method wouldn't be necessary
private fun ExpectGrouping.executeAndAppendExceptionAsAssertion(executable: () -> Unit) =
    try {
        executable()
        // does not collect the expectations created by the inner group but that's a different story -- only relevant
        // if one shows also successful expectations -- we create a dummy test here so that we create at least one
        // expectation for the block (which also means we won't detect if executable does not append one :(
        _logic.createAndAppend("executing test group succeeds", Text.EMPTY) { true }
    } catch (e: Throwable) {
        val rootAssertion = (e as? AtriumError)?.rootAssertion
        if (rootAssertion != null) {
            // TODO 1.3.0 append a hint that a top-level expect was used within an expectGrouped instead of using
            //  the expect (or whatever verb is used) of the ExpectGrouping
            _logic.append(rootAssertion)
        } else {
            _logic.append(
                assertionBuilder.fixedClaimGroup
                    .withListType
                    .failing
                    .withDescriptionAndRepresentation(
                        TranslatableWithArgs(
                            DescriptionFunLikeExpectation.THREW,
                            e::class.fullName
                        ), Text.EMPTY
                    )
                    .withAssertion(propertiesOfThrowable(e, _logic))
                    .build()
            )
        }
    }


