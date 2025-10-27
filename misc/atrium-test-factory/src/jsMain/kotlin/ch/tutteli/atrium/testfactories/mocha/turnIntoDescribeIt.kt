package ch.tutteli.atrium.testfactories.mocha

import ch.tutteli.atrium.testfactories.*

/**
 * Turns the given [testNodes] into [describe] and [it] calls from mocha (or any other configured runner which supports
 * `describe` and `it` -- e.g. Jasmine.
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> turnIntoDescribeIt(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT,
    isFirstDescribe: Boolean
): PlatformTestNodeContainer<PlatformTestNode> = UnitPlatformTestNodeContainer.also {
    testNodes.forEach { node ->
        when (node) {
            is BranchTestNode ->
                if (isFirstDescribe) {
                    val currentTest = js("globalThis.__currentTestName__")
                    val displayName =
                        "${cleanedDisplayName(node)} ${if (currentTest != null) "-- $currentTest" else ""}"
                    describe(displayName) {
                        turnIntoDescribeIt(node.nodes, testExecutableFactory, false)
                    }
                } else {
                    collectDescribeAndOutputSingleTest(cleanedDisplayName(node), node.nodes, testExecutableFactory)
                }

            is LeafTestNode<*> -> {
                check(isFirstDescribe.not()) {
                    "you need to define a describe, you cannot start with `it(\"my test case\"){...}` directly"
                }
                it(cleanedDisplayName(node)) {
                    testExecutableFactory().execute(node)
                }
            }
        }
    }
}

/**
 * Collects the display names of all BranchTestNode and create one single [it] with the descriptions.
 * This function mainly exists for the shortcomings of the JS -> Gradle -> Intellij interplay. At some stage the
 * intermediate [describe] are lost in reporting.
 *
 * @since 1.3.0
 */
private fun <TestExecutableT : TestExecutable> collectDescribeAndOutputSingleTest(
    displayName: String,
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT,
) {
    testNodes.forEach { node ->
        when (node) {
            is BranchTestNode ->
                // I doubt we will run into stack-overflow issues as the nesting is normally very limited.
                // If we should run into stack-overflow issues, then we could turn this into a tailrec function
                // using an own stack
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
external fun it(name: String, block: () -> Any?)
