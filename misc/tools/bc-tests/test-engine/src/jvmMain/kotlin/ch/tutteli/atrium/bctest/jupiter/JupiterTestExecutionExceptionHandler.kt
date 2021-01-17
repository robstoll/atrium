package ch.tutteli.atrium.bctest.jupiter

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler

class JupiterTestExecutionExceptionHandler : TestExecutionExceptionHandler {
    override fun handleTestExecutionException(extensionContext: ExtensionContext, t: Throwable) {
        println("!!!!! path of test in case you want to forgive it failing:\n${extensionContext.path}")
        throw t
    }

}
