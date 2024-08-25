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
class LeafTestNode(val displayName: String, val executable: TestExecutable.() -> Unit) : TestNode()

/**
 * A branch test node, i.e. which contains [nodes] itself.
 */
class BranchTestNode(val displayName: String, val nodes: List<TestNode>) : TestNode()
