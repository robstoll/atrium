import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.tasks.testing.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.withType

// TODO 0.20.0 move to tutteli-gradle-plugin
fun Project.configureTestTasks() {
    fun memoizeTestFile(testTask: AbstractTestTask) =
        project.file("${project.buildDir}/test-results/memoize-previous-state-${testTask.name}.txt")

   tasks.withType<AbstractTestTask> {
        testLogging {
            events(
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT,
                TestLogEvent.STANDARD_ERROR
            )
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
        val testTask = this
        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor) {}
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}
            override fun afterSuite(suite: TestDescriptor, result: TestResult) {
                if (suite.parent == null) {
                    if (result.testCount == 0L) {
                        throw GradleException("No tests executed, most likely the discovery failed.")
                    }
                    println("Result: ${result.resultType} (${result.successfulTestCount} succeeded, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)")
                    memoizeTestFile(testTask).writeText(result.resultType.toString())
                }
            }
        })
    }

    tasks.withType<Test>().forEach { testTask ->
        val failIfTestFailedLastTime =
            tasks.register("fail-if-${testTask.name}-failed-last-time") {
                doLast {
                    if (!testTask.didWork) {
                        val memoizeTestFile = memoizeTestFile(testTask)
                        if (memoizeTestFile.exists() && memoizeTestFile.readText() == TestResult.ResultType.FAILURE.toString()) {
                            val allTests = tasks.getByName("allTests") as TestReport
                            throw GradleException(
                                "test failed in last run, execute clean${testTask.name} to force its execution\n" +
                                    "See the following report for more information:\nfile://${allTests.destinationDir}/index.html"
                            )
                        }
                    }
                }
            }
        testTask.finalizedBy(failIfTestFailedLastTime)
    }
}
