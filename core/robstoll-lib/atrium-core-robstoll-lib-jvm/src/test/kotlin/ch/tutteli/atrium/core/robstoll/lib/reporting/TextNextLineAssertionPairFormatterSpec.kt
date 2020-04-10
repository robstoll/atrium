package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.reporting.AssertionFormatterSpecBase
import ch.tutteli.atrium.specs.reporting.ToStringObjectFormatter
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

class TextNextLineAssertionPairFormatterSpec : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) = describeFunTemplate("", funName, body = body)

    val testee = TextNextLineAssertionPairFormatter(
        ToStringObjectFormatter,
        UsingDefaultTranslator()
    )

    val bulletPoint = "** "
    val indentBulletPoint = " ".repeat(bulletPoint.length)
    val name = "group name"
    val subject = 123

    describeFun(testee::formatGroupHeader.name) {
        it("puts the representation on the next line indented as the bullet point used for newParameterObject") {
            val newParameterObject = parameterObject.createChildWithNewPrefix(bulletPoint)
            val assertionGroup = AssertImpl.builder.root
                .withDescriptionAndRepresentation(name, subject)
                .withAssertions(listOf())
                .build()
            testee.formatGroupHeader(parameterObject, assertionGroup, newParameterObject)
            expect(sb.toString()).toBe("$name:$lineSeperator$indentBulletPoint$subject")
        }

        it("does not append a new line if the subject is ${RawString::class.simpleName}${RawString.Companion::EMPTY.name}") {
            val assertionGroup = AssertImpl.builder.root
                .withDescriptionAndEmptyRepresentation(name)
                .withAssertions(listOf())
                .build()
            val newParameterObject = parameterObject.createChildWithNewPrefix(bulletPoint)
            testee.formatGroupHeader(parameterObject, assertionGroup, newParameterObject)
            expect(sb.toString()).toBe("$name:")
        }
    }

    describeFun(testee::format.name) {
        context("current indent 0, no prefix") {
            it("puts the representation on the next line without indent") {
                testee.format(parameterObject, Untranslatable(name), subject)
                expect(sb.toString()).toBe("$name:$lineSeperator$subject")
            }
        }

        context("current indent 2, new prefix length 2") {
            it("puts the representation on the next line with 2 indent") {
                val newParameterObject = parameterObject.createChildWithNewPrefix("==")
                testee.format(newParameterObject, Untranslatable(name), subject)
                expect(sb.toString()).toBe("$name:$lineSeperator  $subject")
            }
        }

        it("does not append a new line if the subject is ${RawString::class.simpleName}${RawString.Companion::EMPTY.name}") {
            val newParameterObject = parameterObject.createChildWithNewPrefix(bulletPoint)
            testee.format(
                newParameterObject, Untranslatable(name),
                RawString.EMPTY
            )
            expect(sb.toString()).toBe("$name:")
        }
    }
})
