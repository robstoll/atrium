package ch.tutteli.atrium.testfactories

/**
 * Annotation which tells the test runner that a test factory is used.
 *
 * The platform then maps this annotation to the platform specific test runner. For instance, on JVM we map this to
 * org.junit.jupiter.api.TestFactory on JS we map it to [kotlin.test.Test].
 *
 * @since 1.3.0
 */
@Target(AnnotationTarget.FUNCTION)
expect annotation class TestFactory()

/**
 * Turns the given [testNodes] into a platform specific representation such as JUnit's DynamicNode.
 *
 * @return The platform specific test factories.
 *
 * @since 1.3.0
 */
expect fun <TestExecutableT: TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): Any

fun <TestExecutableT : TestExecutable> TestExecutableT.execute(node: LeafTestNode<*>): TestExecutableT {
    // we know this is unsafe, as long as we don't start to cast in other places neither mix expectation verbs
    // we should be relatively safe
    @Suppress("UNCHECKED_CAST")
    return apply(node.executable as TestExecutableT.() -> Unit)
}

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
 * @return The [TestNode]s the given [setup] function create
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> buildTestNodes(setup: TestFactoryBuilder<TestExecutableT>.() -> Unit): List<TestNode> =
    TestFactoryBuilder<TestExecutableT>().also(setup).nodes

