package ch.tutteli.atrium.spec

import org.jetbrains.spek.engine.Scope
import org.jetbrains.spek.engine.SpekEngineDescriptor
import org.jetbrains.spek.engine.SpekExecutionContext
import org.jetbrains.spek.engine.SpekTestEngine
import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.hierarchical.Node

class DeprecationTestEngine : TestEngine {
    private val spek = SpekTestEngine()

    override fun getId() = "spek-deprecation"
    override fun execute(request: ExecutionRequest) = spek.execute(request)

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        return try {
            val descriptor = spek.discover(discoveryRequest, uniqueId) as SpekEngineDescriptor
            val forgive = discoveryRequest.configurationParameters.get("forgive").orElse(null)
            if (forgive != null) {
                exchangeWithForgivingTests(descriptor, Regex(forgive))
            }
            descriptor
        } catch (t: Throwable) {
            //since the junit gradle platform does not treat an error during discovery as failure, we have return a
            // fake descriptor which fails
            // TODO check if this changes with https://github.com/junit-team/junit5/issues/1298
            val descriptor = SpekEngineDescriptor(uniqueId)
            descriptor.addChild(FailingTest(uniqueId.append("discovery", "discovering tests"), t))
            descriptor
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

    private class FailingTest(uniqueId: UniqueId, private val throwable: Throwable) :
        AbstractTestDescriptor(uniqueId, "discovering tests"),
        Node<SpekExecutionContext> {
        override fun getType() = TestDescriptor.Type.TEST
        override fun execute(
            context: SpekExecutionContext?,
            dynamicTestExecutor: Node.DynamicTestExecutor?
        ): SpekExecutionContext {
            throw AssertionError(throwable)
        }
    }
}
