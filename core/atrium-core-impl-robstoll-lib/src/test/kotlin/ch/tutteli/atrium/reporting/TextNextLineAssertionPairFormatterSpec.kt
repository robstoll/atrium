package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.assertions.AssertionGroupBuilder
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.reporting.AssertionFormatterSpecBase
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

class TextNextLineAssertionPairFormatterSpec : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun("", funName, body = body)

    val testee = TextNextLineAssertionPairFormatter(ToStringObjectFormatter, UsingDefaultTranslator())

    val bulletPoint = "** "
    val indentBulletPoint = " ".repeat(bulletPoint.length)
    val name = "group name"
    val subject = 123

    describeFun(testee::formatGroupHeader.name) {
        it("puts the subject on the next line indented as the bullet point used for newMethodObject") {
            val newMethodObject = methodObject.createChildWithNewPrefix(bulletPoint)
            val assertionGroup = AssertionGroupBuilder.root.create(Untranslatable(name), subject, listOf())
            testee.formatGroupHeader(methodObject, assertionGroup, newMethodObject)
            assert(sb.toString()).toBe("$name:$separator$indentBulletPoint$subject")
        }

        it("does not append a new line if the subject is ${RawString::class.simpleName}${RawString.Companion::EMPTY.name}") {
            val assertionGroup = AssertionGroupBuilder.root.create(Untranslatable(name), RawString.EMPTY, listOf())
            val newMethodObject = methodObject.createChildWithNewPrefix(bulletPoint)
            testee.formatGroupHeader(methodObject, assertionGroup, newMethodObject)
            assert(sb.toString()).toBe("$name:")
        }
    }

    describeFun(testee::format.name) {
        context("current indent 0, no prefix") {
            it("puts the subject on the next line without indent") {
                testee.format(methodObject, Untranslatable(name), subject)
                assert(sb.toString()).toBe("$name:$separator$subject")
            }
        }

        context("current indent 2, new prefix length 2") {
            it("puts the subject on the next line with 2 indent") {
                val newMethodObject = methodObject.createChildWithNewPrefix("==")
                testee.format(newMethodObject, Untranslatable(name), subject)
                assert(sb.toString()).toBe("$name:$separator  $subject")
            }
        }

        it("does not append a new line if the subject is ${RawString::class.simpleName}${RawString.Companion::EMPTY.name}") {
            val newMethodObject = methodObject.createChildWithNewPrefix(bulletPoint)
            testee.format(newMethodObject, Untranslatable(name), RawString.EMPTY)
            assert(sb.toString()).toBe("$name:")
        }
    }
})
