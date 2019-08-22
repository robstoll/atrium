package readme.examples

import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.runtime.execution.ExecutionListener
import org.spekframework.spek2.runtime.execution.ExecutionResult
import org.spekframework.spek2.runtime.scope.TestScopeImpl

class ReadmeExecutionListener(
    private val listener: JUnitEngineExecutionListenerAdapter,
    private val outputs: MutableMap<String, String>
) : ExecutionListener by listener {

    override fun testExecutionFinish(test: TestScopeImpl, result: ExecutionResult) {
        when (result) {
            ExecutionResult.Success -> listener.testExecutionFinish(
                test,
                ExecutionResult.Failure(IllegalStateException("readme tests are supposed to fail"))
            )
            is ExecutionResult.Failure -> handleFailure(result, test)
        }
    }

    private fun handleFailure(result: ExecutionResult.Failure, test: TestScopeImpl) {
        when (result.cause) {
            is AssertionError -> {
                outputs[test.id.name] = result.cause.message!!
                listener.testExecutionFinish(test, ExecutionResult.Success)
            }
            else -> listener.testExecutionFinish(test, result)
        }
    }
}
