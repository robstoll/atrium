package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_UK.isEmpty
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class AssertionFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val controller = mock<AssertionFormatterController>()
    val testee = testeeFactory(mapOf(), controller, ToStringObjectFormatter, UsingDefaultTranslator())

    var sb = StringBuilder()
    var parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
    }

    describeFun(testee::format.name) {
        it("throws an UnsupportedOperationException if ${AssertionGroup::class.simpleName} is passed") {
            verbs.checkException {
                testee.format(object : AssertionGroup {
                    override val name = Untranslatable("test")
                    override val type = RootAssertionGroupType
                    override val subject = 1
                    override val assertions: List<Assertion> = emptyList()
                }, parameterObject)
            }.toThrow<UnsupportedOperationException> { message { toBe(AssertionFormatter.CALL_FORMAT_GROUP) } }
            verbs.checkImmediately(sb).isEmpty()
        }
    }
})
