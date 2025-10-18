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
 * Note, we only have a type parameter because Kotlin does not allow yet to have a generic type with a fixed type parameter
 * on the RHS of an actual typealias such as `actual typealias DynamicNodeContainer = Iterable<DynamicNode>` and
 * because JUnits compatibility check at runtime requires a ParameterizedType.
 * This is also the reason why we need [DynamicNodeLike]
 *
 * @since 1.3.0
 */
expect interface DynamicNodeContainer<T : DynamicNodeLike>

/**
 * Note, this type only exists  because Kotlin does not allow yet to have a generic type with a fixed type parameter
 * on the RHS of an actual typealias such as `actual typealias DynamicNodeContainer = Iterable<DynamicNode>` and
 * because JUnits compatibility check at runtime requires a ParameterizedType. If JUnit would not require it
 * but instead check if a supertype is compliant, then we could just use DynamicNodeContainer and type alias it to
 * IterableDynamicNodeWrapper in jvm.
 *
 * @since 1.3.0
 */
expect abstract class DynamicNodeLike

/**
 * Turns the given [testNodes] into a platform specific representation such as JUnit's DynamicNode.
 *
 * @return The platform specific test factories.
 *
 * @since 1.3.0
 */
expect fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): DynamicNodeContainer<DynamicNodeLike>

