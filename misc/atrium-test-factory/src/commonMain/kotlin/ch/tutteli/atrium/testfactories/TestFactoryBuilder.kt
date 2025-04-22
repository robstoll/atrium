package ch.tutteli.atrium.testfactories

/**
 * Builder to create a hierarchy of [TestNode]s.
 *
 * @since 1.3.0
 */
class TestFactoryBuilder<TestExecutableT : TestExecutable> {
    private val mutableNodes = mutableListOf<TestNode>()

    /**
     * Returns the so far defined nodes.
     *
     * @since 1.3.0
     */
    val nodes: List<TestNode> get() = mutableNodes.toList()

    /**
     * Creates a [LeafTestNode] with the given [displayName] and [executable].
     *
     * @since 1.3.0
     */
    fun it(displayName: String, executable: TestExecutableT.() -> Unit) {
        mutableNodes.add(LeafTestNode(displayName, executable))
    }

    /**
     * Creates a [BranchTestNode] with the given [displayName] and [TestNode]s the given [setup] creates.
     *
     * @since 1.3.0
     */
    fun describe(displayName: String, setup: TestFactoryBuilder<TestExecutableT>.() -> Unit) {
        mutableNodes.add(BranchTestNode(displayName, buildTestNodes(setup)))
    }
}


/**
 * Helper function to create [TestNode]s based on the given [setup] function.
 *
 * @return The [TestNode]s the given [setup] function creates.
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> buildTestNodes(setup: TestFactoryBuilder<TestExecutableT>.() -> Unit): List<TestNode> =
    TestFactoryBuilder<TestExecutableT>().also(setup).nodes
