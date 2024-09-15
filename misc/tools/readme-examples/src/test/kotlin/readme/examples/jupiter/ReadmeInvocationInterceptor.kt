package readme.examples.jupiter

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext
import java.lang.reflect.Method
import java.util.*

class ReadmeInvocationInterceptor : InvocationInterceptor {
    override fun interceptTestMethod(
        invocation: InvocationInterceptor.Invocation<Void>,
        invocationContext: ReflectiveInvocationContext<Method>,
        extensionContext: ExtensionContext?
    ) {
        // we only intercept ReadmeTests
        if (ReadmeTest::class.java.isAssignableFrom(invocationContext.executable.declaringClass)) {
            val failure = executeAndCatch(invocation, invocationContext, extensionContext)
            ReadmeState.testClasses.add(invocationContext.executable.declaringClass)

            val testName = invocationContext.executable.name
            if (testName.startsWith("code-")) {
                handleCode(failure, testName)
            } else if (testName.startsWith("ex-") || testName.startsWith("exs-")) {
                handleExample(failure, testName)
            } else {
                failure.ifPresent {
                    throw AssertionError(
                        "only example tests are supposed to fail, test $testName does neither start with ex- nor with exs-",
                        it
                    )
                }
            }
        } else {
            super.interceptTestMethod(invocation, invocationContext, extensionContext)
        }
    }

    private fun executeAndCatch(
        invocation: InvocationInterceptor.Invocation<Void>,
        invocationContext: ReflectiveInvocationContext<Method>,
        extensionContext: ExtensionContext?
    ): Optional<Throwable> {
        val failure = try {
            super.interceptTestMethod(invocation, invocationContext, extensionContext)
            Optional.empty()
        } catch (e: Throwable) {
            Optional.of(e)
        }
        return failure
    }

    private fun handleCode(failure: Optional<Throwable>, testName: String) {
        // code should not fail
        failure.ifPresent { throw it }
        if (ReadmeState.code.contains(testName)) {
            throw IllegalStateException("code $testName is at least defined twice")
        } else {
            ReadmeState.code.add(testName)
        }
    }

    private fun handleExample(failure: Optional<Throwable>, testName: String) {
        failure.ifPresentOrElse(
            {
                when (it) {
                    is AssertionError -> {
                        ReadmeState.examples[testName] = it.message!!
                        // swallow exception, i.e. turn failing into successful
                    }

                    else -> throw it
                }
            },
            { throw IllegalStateException("example tests are supposed to fail") }
        )
    }


}
