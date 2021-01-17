package ch.tutteli.atrium.bctest.jupiter

import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtensionContext

class JupiterForgivingSamplesExtension : ExecutionCondition {
    override fun evaluateExecutionCondition(extensionContext: ExtensionContext): ConditionEvaluationResult {
        val maybeForgivePattern = extensionContext.getConfigurationParameter("forgive")
        return maybeForgivePattern.map { forgivePattern: String ->
            val forgive = Regex(forgivePattern)
            val path = extensionContext.path
            if (forgive.matches(path)) {
                println("forgiving sample $path")
                ConditionEvaluationResult.disabled("forgiven")
            } else {
                ConditionEvaluationResult.enabled("not forgiven")
            }
        }.orElse(ConditionEvaluationResult.enabled("no forgivePattern specified"))
    }
}

