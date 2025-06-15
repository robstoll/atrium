package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.expectationCreatorTestSetup
import ch.tutteli.atrium.specs.integration.utils.subjectLessTestSetup
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import org.junit.jupiter.api.BeforeAll
import java.util.logging.ConsoleHandler
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


actual abstract class ExpectationFunctionBaseTest {

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutableForTests>.(T) -> Unit
    ): Any = testFactory {
        describeFun(specPair, setup)
    }

    protected actual fun <SubjectT> subjectLessTestFactory(
        expectationCreator: Pair<String, Expect<SubjectT>.() -> Unit>,
        vararg otherExpectationCreators: SpecPair<Expect<SubjectT>.() -> Unit>,
        groupPrefix: String?
    ): Any = testFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}check expectation function can be used in a proof explanation without subject defined and does not throw") {
            this.apply(subjectLessTestSetup(expectationCreator, otherExpectationCreators))
        }
    }

    protected actual fun subjectLessTestFactory(
        testData: SubjectLessTestData<*>,
        vararg otherTestData: SubjectLessTestData<*>,
    ): Any = testFactory {
        describe("check expectation function can be used in a proof explanation without subject defined and does not throw") {
            applySubjectLessTestSetup(testData, otherTestData)
        }
    }


    protected actual fun <SubjectT> expectationCreatorTestFactory(
        subject: SubjectT,
        assertionCreator: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        vararg otherAssertionCreators: Triple<String, String, Pair<Expect<SubjectT>.() -> Expect<SubjectT>, Expect<SubjectT>.() -> Expect<SubjectT>>>,
        groupPrefix: String?
    ): Any = testFactory {
        describe("${groupPrefix?.let { "$it - " } ?: ""}specifying two expectationCreator-lambda, one is empty") {
            this.apply(expectationCreatorTestSetup(subject, assertionCreator, otherAssertionCreators))
        }
    }

    protected actual fun expectationCreatorTestFactory(
        testData: ExpectationCreatorTestData<*>,
        vararg otherTestData: ExpectationCreatorTestData<*>,
    ): Any = testFactory {
        describe("specifying two expectationCreator-lambda, one is empty") {
            applyExpectationCreatorTestSetup(testData, otherTestData)
        }
    }

    protected actual fun <T : Any> nonNullableCases(
        nonNullableSpecPair: SpecPair<T>,
        nullableSpecPair: Any,
        testExecutable: ExpectTestExecutableForTests.(T) -> Unit,
    ): Any = testFactory {
        describe("non-nullable cases") {
            uncheckedToNonNullable(nonNullableSpecPair, nullableSpecPair)
                .forEach { specPair -> itFun(specPair, testExecutable) }
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
            .replace(testFactoryRegex, "")

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

        // we only need this filter since Kotlin does not allow to define typealiases with type parameters on the right
        // hand side, otherwise we could define that testFactory returns List<DynamicNode> on the JVM platform
        val testFactoryRegex =
            Regex("\\(\\d+\\) \\[INFO\\].*@TestFactory method 'public final java.lang.Object[\\S\\s]+?(\n\n|$)")
    }
}
