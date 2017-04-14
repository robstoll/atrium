package ch.tutteli.atrium.test.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IMultiMessageAssertion
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.test.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

open class SameLineAssertionMessageFormatterSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (IObjectFormatter) -> IAssertionFormatter
) : Spek({
    val testee = testeeFactory(ToStringObjectFormatter())

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
            testee.format(sb, OneMessageAssertion("bla", "bli", false), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            verbs.checkImmediately(sb.toString()).toBe("bla: bli")
        }

        val separator = System.getProperty("line.separator")!!
        it("uses the system line separator if there are multiple messages") {
            testee.format(sb, object : IMultiMessageAssertion {
                override val messages = listOf(Message("a", "b", false), Message("c", "d", false))
            }, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            verbs.checkImmediately(sb.toString()).contains("a: b${separator}c: d")
        }
    }

})

