package readme.examples

import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.runtime.execution.ExecutionListener
import org.spekframework.spek2.runtime.execution.ExecutionResult
import org.spekframework.spek2.runtime.scope.TestScopeImpl

class ReadmeExecutionListener(
    private val listener: JUnitEngineExecutionListenerAdapter,
    private val examples: MutableMap<String, String>,
    private val snippets: MutableSet<String>
) : ExecutionListener by listener {

    override fun testExecutionFinish(test: TestScopeImpl, result: ExecutionResult) {
        when (result) {
            ExecutionResult.Success -> handleSuccess(test)
            is ExecutionResult.Failure -> handleFailure(result, test)
        }
    }

    private fun handleSuccess(test: TestScopeImpl) {
        if (!test.id.name.startsWith("snippet")) {
            listener.testExecutionFinish(
                test,
                ExecutionResult.Failure(IllegalStateException("readme tests are supposed to fail"))
            )
            return
        }
        if (snippets.contains(test.id.name)) {
            listener.testExecutionFinish(
                test,
                ExecutionResult.Failure(IllegalStateException("snippet ${test.id.name} is at least defined twice"))
            )
            return
        }
        snippets.add(test.id.name)
        listener.testExecutionFinish(test, ExecutionResult.Success)
    }

    private fun handleFailure(result: ExecutionResult.Failure, test: TestScopeImpl) {
        when (result.cause) {
            is AssertionError -> {
                examples[test.id.name] = result.cause.message!!
                listener.testExecutionFinish(test, ExecutionResult.Success)
            }
            else -> listener.testExecutionFinish(test, result)
        }
    }
}
