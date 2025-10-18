package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testFactory as internalTestFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.expectationCreatorTestSetup
import ch.tutteli.atrium.specs.integration.utils.subjectLessTestSetup
import ch.tutteli.atrium.specs.uncheckedToNonNullable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import org.junit.jupiter.api.BeforeAll
import java.util.logging.*


actual abstract class ExpectationFunctionBaseTest {

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ) = internalTestFactory {
        describeFun(specPair, setup)
    }

    protected actual fun testFactory(
        specPair: SpecPair<*>,
        vararg otherSpecPairs: SpecPair<*>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.() -> Unit,
    ) = internalTestFactory {
        setup()
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: Pair<String, Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ) = internalTestFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}check expectation function can be used in a proof explanation without subject defined and does not throw") {
            this.apply(subjectLessTestSetup(expectationCreator, otherExpectationCreators))
        }
    }

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ) = internalTestFactory {
        describe("check expectation function can be used in a proof explanation without subject defined and does not throw") {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }


    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ) = internalTestFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}specifying two expectationCreator-lambda, one is empty") {
            this.apply(expectationCreatorTestSetup(subject, assertionCreator, otherAssertionCreators))
        }
    }

    protected actual fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ) = internalTestFactory {
        describe("specifying two expectationCreator-lambda, one is empty") {
            applyExpectationCreatorTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit,
    ) = internalTestFactory {
        // we only use this describe as intellij otherwise repeats the function name, we could probably use a junit
        // DisplayNameProvider or similar to achieve the same without this hack
        describe("non-nullable cases") {
            uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair).forEach { specPair ->
                itFun(specPair, setup = testExecutable)
            }
        }
    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun suppressSpecificJUnitWarnings() {
            val logger = Logger.getLogger("org.junit.platform.launcher.core.DiscoveryIssueNotifier")
            logger.useParentHandlers = false

            logger.addHandler(
                SuppressingHandler(
                    logger.handlers.firstOrNull() ?: ConsoleHandler().apply { level = Level.ALL }
                )
            )
        }
    }
}

class SuppressingHandler(private val delegate: Handler) : Handler() {

    override fun publish(record: LogRecord) {
        val originalMessage = record.message ?: return

        val modifiedMessage = originalMessage
            .replace(runGutterRegex, "")

        if (modifiedMessage.replace(testEngineRegex, "").isBlank()) return
        else if (modifiedMessage != originalMessage) {
            val redactedRecord = LogRecord(
                record.level,
                "$modifiedMessage\n\nRemoved warnings about trigger_run_gutter and INFO about testFactory, that's why the number of issues is off"
            ).apply {
                loggerName = record.loggerName
                thrown = record.thrown
                sourceClassName = record.sourceClassName
                sourceMethodName = record.sourceMethodName
            }

            delegate.publish(redactedRecord)
        } else {
            delegate.publish(record)
        }
    }

    override fun flush() = delegate.flush()
    override fun close() = delegate.close()

    companion object {
        val testEngineRegex = Regex("TestEngine with ID.*\n\n")
        val runGutterRegex = Regex("\\(\\d+\\) \\[WARNING\\] .*trigger_run_gutter[\\S\\s]+?\n\n")
    }
}
