package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IMultiMessageAssertion
import ch.tutteli.atrium.assertions.IOneMessageAssertion
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import java.util.*

open class SameLineAssertionMessageFormatterSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (IObjectFormatter, ITranslator) -> IAssertionFormatter
) : Spek({
    val testee = testeeFactory(ToStringObjectFormatter(), AtriumFactory.newTranslator(ReporterBuilder.EMPTY_TRANSLATION_PROVIDER, Locale.UK))

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }


    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    context("unsupported ${IAssertion::class.java.simpleName}") {
        it("writes whether the assertion holds including a message telling the type is unsupported") {
            val assertion: IAssertion = object : IAssertion {
                override fun holds() = false
            }
            testee.format(sb, assertion, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            verbs.checkLazily(sb.toString()) {
                contains("Unsupported type ${assertion::class.java.name}")
                contains("false")
            }
        }

        it("writes ${Message::description.name} and ${Message::representation.name} on the same line separated by colon and space") {
            val assertion = object: IOneMessageAssertion{
                override val message = Message(IS_SAME, "bli", false)
            }
            testee.format(sb, assertion, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            verbs.checkImmediately(sb.toString()).toBe("${IS_SAME.getDefault()}: bli")
        }

        val separator = System.getProperty("line.separator")!!
        it("uses the system line separator if there are multiple messages") {
            testee.format(sb, object : IMultiMessageAssertion {
                override val messages = listOf(
                    Message(IS_SAME, "b", false),
                    Message(TO_BE, "d", false))
            }, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)

            verbs.checkImmediately(sb.toString()).contains("${IS_SAME.getDefault()}: b$separator"
                +"${TO_BE.getDefault()}: d")
        }
    }

})

