package ch.tutteli.atrium.bctest.spek

import org.spekframework.spek2.junit.JUnitEngineExecutionListenerAdapter
import org.spekframework.spek2.runtime.execution.ExecutionListener
import org.spekframework.spek2.runtime.execution.ExecutionResult
import org.spekframework.spek2.runtime.scope.GroupScopeImpl
import org.spekframework.spek2.runtime.scope.TestScopeImpl

class Spek2ForgivingExecutionListener(
    private val listener: JUnitEngineExecutionListenerAdapter,
    private val forgiveRegex: Regex
) : ExecutionListener by listener {

    override fun groupExecutionFinish(group: GroupScopeImpl, result: ExecutionResult) {
        when (result) {
            ExecutionResult.Success -> listener.groupExecutionFinish(group, result)
            is ExecutionResult.Failure -> handleGroupFailure(result, group)
        }
    }

    private fun handleGroupFailure(result: ExecutionResult.Failure, group: GroupScopeImpl) {
        if (forgiveRegex.matches(group.path.toString())) {
            println("forgiving ${group.path}")
            listener.groupExecutionFinish(group, ExecutionResult.Success)
        } else {
            println("!!!!! path of group in case you want to forgive it failing:\n${group.path}")
            listener.groupExecutionFinish(group, result)
        }
    }

    override fun testExecutionFinish(test: TestScopeImpl, result: ExecutionResult) {
        when (result) {
            ExecutionResult.Success -> listener.testExecutionFinish(test, result)
            is ExecutionResult.Failure -> handleTestFailure(result, test)
        }
    }

    private fun handleTestFailure(result: ExecutionResult.Failure, test: TestScopeImpl) {
        if (forgiveRegex.matches(test.path.toString())) {
            println("forgiving ${test.path}")
            listener.testExecutionFinish(test, ExecutionResult.Success)
        } else {
            println("!!!!! path of test in case you want to forgive it failing:\n${test.path}")
            listener.testExecutionFinish(test, result)
        }
    }
}
