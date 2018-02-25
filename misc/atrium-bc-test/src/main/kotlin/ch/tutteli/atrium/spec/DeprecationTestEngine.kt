package ch.tutteli.atrium.spec

import org.jetbrains.spek.engine.Scope
import org.jetbrains.spek.engine.SpekEngineDescriptor
import org.jetbrains.spek.engine.SpekTestEngine
import org.junit.platform.engine.*

class DeprecationTestEngine : TestEngine {
    private val spek = SpekTestEngine()

    override fun getId() = "spek-deprecation"
    override fun execute(request: ExecutionRequest) = spek.execute(request)

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val descriptor = spek.discover(discoveryRequest, uniqueId) as SpekEngineDescriptor

        val forgive = discoveryRequest.configurationParameters.get("forgive").orElse(null)
        if (forgive != null) {
            exchangeWithForgivingTests(descriptor, Regex(forgive))
        }
        return descriptor
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
}
