package ch.tutteil.atrium.bctest

import org.jetbrains.spek.engine.Scope
import org.jetbrains.spek.engine.SpekEngineDescriptor
import org.jetbrains.spek.engine.SpekExecutionContext
import org.jetbrains.spek.engine.SpekTestEngine
import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.hierarchical.Node
import java.lang.reflect.InvocationTargetException

/**
 * Represents a [TestEngine] (id 'spek-deprecation') which uses the [SpekTestEngine] but allows to define tests
 * which shall be converted to [ForgivingTest]s.
 *
 * Which tests shall be forgivable can be defined with the custom config property `forgive` and a regular expression
 * matches desired [UniqueId]s. For instance:
 *
 * `forgive=.*\.Iterable\..*`
 *
 * Turns all tests containing `.Iterable.` in their [UniqueId] into a [ForgivingTest].
 *
 * Moreover, the engine fails if no tests were discovered or if an error occurs during discovery.
 */
class DeprecationSpek1TestEngine : TestEngine {
    private val spek = SpekTestEngine()

    override fun getId() = "spek1-deprecation"
    override fun execute(request: ExecutionRequest) = spek.execute(request)

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        return try {
            val descriptor = spek.discover(discoveryRequest, uniqueId)
            val forgive = discoveryRequest.configurationParameters.get("forgive").orElse(null)
            if (forgive != null) {
                exchangeWithForgivingTests(descriptor, Regex(forgive))
            }
            require(descriptor.children.isNotEmpty()) {
                "Could not find any specification, check your runtime classpath"
            }
            descriptor
        } catch (t: Throwable) {
            //since the junit gradle platform does not treat an error during discovery as failure,
            // we have to return a fake descriptor which fails
            // TODO check if this changes with https://github.com/junit-team/junit5/issues/1298
            SpekEngineDescriptor(uniqueId).apply {
                addChild(DiscoveryFailed(uniqueId, t))
            }
        }
    }

    private fun exchangeWithForgivingTests(descriptor: TestDescriptor, testToForgive: Regex) {
        descriptor.children.toList().forEach { child ->
            if (child is Scope.Test) {
                if (child.uniqueId.toString().matches(testToForgive)) {
                    val parent = child.getParent()
                    descriptor.removeChild(child)
                    parent.ifPresent {
                        child.setParent(it)
                    }
                    val forgivingTest = ForgivingTest(child)
                    descriptor.addChild(forgivingTest)
                }
            }
            exchangeWithForgivingTests(child, testToForgive)
        }
    }

    private class DiscoveryFailed(
        uniqueId: UniqueId,
        private val throwable: Throwable
    ) : AbstractTestDescriptor(uniqueId.append("discovery", "fail"), "discovering specifications"),
        Node<SpekExecutionContext> {

        override fun getType() = TestDescriptor.Type.TEST
        override fun execute(context: SpekExecutionContext?, dynamicTestExecutor: Node.DynamicTestExecutor?) =
            when (throwable) {
                is InvocationTargetException ->
                    throw AssertionError(
                        "InvocationTargetException occurred with targetException:" +
                            "\n ${throwable.targetException}",
                        throwable
                    )
                is ExceptionInInitializerError -> throw AssertionError(
                    "ExceptionInInitializerError occurred with exception:" +
                        "\n ${throwable.exception}",
                    throwable
                )

                else -> throw throwable
            }
    }
}
