package ch.tutteli.atrium.testfactories

/**
 * Marker interface for test nodes.
 *
 * @since 1.3.0
 */
sealed class TestNode

/**
 * A leaf test node consisting of a [displayName] and the [executable] which represents the Test as such
 *
 * @since 1.3.0
 */
class LeafTestNode<TestExecutableT : TestExecutable>(
    val displayName: String,
    val executable: TestExecutableT.() -> Unit
) : TestNode()

/**
 * A branch test node, i.e. which contains [nodes] itself.
 *
 * @since 1.3.0
 */
class BranchTestNode(val displayName: String, val nodes: List<TestNode>) : TestNode()


/**
 * Performs an unsafe cast on the given [node] to [LeafTestNode]<[TestExecutableT]> and applies
 * [LeafTestNode.executable] on this [TestExecutableT].
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> TestExecutableT.execute(node: LeafTestNode<*>): TestExecutableT {
    // we know this is unsafe, as long as we don't start to cast in other places neither mix expectation verbs
    // we should be relatively safe
    @Suppress("UNCHECKED_CAST")
    return apply(node.executable as TestExecutableT.() -> Unit)
}
