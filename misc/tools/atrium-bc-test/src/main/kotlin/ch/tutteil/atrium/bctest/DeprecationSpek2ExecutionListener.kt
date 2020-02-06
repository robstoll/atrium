package ch.tutteil.atrium.bctest

import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.runtime.execution.ExecutionListener
import org.spekframework.spek2.runtime.execution.ExecutionResult
import org.spekframework.spek2.runtime.scope.TestScopeImpl

class DeprecationSpek2ExecutionListener(
    private val listener: JUnitEngineExecutionListenerAdapter,
    private val forgiveRegex: Regex
) : ExecutionListener by listener {

    override fun testExecutionFinish(test: TestScopeImpl, result: ExecutionResult) {
        when (result) {
            ExecutionResult.Success -> listener.testExecutionFinish(test, ExecutionResult.Success)
            is ExecutionResult.Failure -> handleFailure(result, test)
        }
    }

    private fun handleFailure(result: ExecutionResult.Failure, test: TestScopeImpl) {
        if (forgiveRegex.matches(test.path.toString())) {
            println("forgiving ${test.path}")
            listener.testExecutionFinish(test, ExecutionResult.Success)
        } else {
            listener.testExecutionFinish(test, result)
        }
    }
}
