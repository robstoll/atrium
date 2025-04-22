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
