package ch.tutteil.atrium.bctest

import org.junit.platform.engine.*
import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.junit.SpekTestDescriptor
import org.spekframework.spek2.junit.SpekTestDescriptorFactory
import org.spekframework.spek2.junit.SpekTestEngine
import org.spekframework.spek2.runtime.SpekRuntime
import org.spekframework.spek2.runtime.execution.ExecutionRequest
import java.util.*
import org.junit.platform.engine.ExecutionRequest as JUnitExecutionRequest

class DeprecationSpek2TestEngine : TestEngine {
    private val spek = SpekTestEngine()
    private lateinit var forgiveRegex: Regex

    override fun getId(): String = "spek2-deprecation"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val descriptor = spek.discover(discoveryRequest, uniqueId)
        val forgive = discoveryRequest.configurationParameters.get("forgive").orElse(null)
        forgiveRegex = Regex(forgive)
        require(descriptor.children.isNotEmpty()) {
            "Could not find any specification, check your runtime classpath"
        }
        return descriptor
    }

    override fun execute(request: JUnitExecutionRequest) {
        val default = Locale.getDefault()
        try {
            Locale.setDefault(Locale.UK)

            runSpekWithCustomListener(request)

        } catch (t: Throwable) {
            t.printStackTrace()
            Locale.setDefault(default)

            request.fail(t)
        }
    }

    private fun runSpekWithCustomListener(request: JUnitExecutionRequest) {
        val roots = request.rootTestDescriptor.children
            .filterIsInstance<SpekTestDescriptor>()
            .map(SpekTestDescriptor::scope)

        val executionListener = DeprecationSpek2ExecutionListener(
            JUnitEngineExecutionListenerAdapter(request.engineExecutionListener, SpekTestDescriptorFactory()),
            forgiveRegex
        )
        val executionRequest = ExecutionRequest(roots, executionListener)
        SpekRuntime().execute(executionRequest)
    }

    private fun JUnitExecutionRequest.fail(throwable: Throwable) {
        engineExecutionListener.executionFinished(
            rootTestDescriptor,
            TestExecutionResult.failed(throwable)
        )
    }
}

