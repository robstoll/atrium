package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isEmpty
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class AssertionFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, ITranslator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)


    val testee = testeeFactory(mapOf(), AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    }

    describeFun(testee::format.name) {
        it("throws an UnsupportedOperationException if ${IAssertionGroup::class.simpleName} is passed") {
            verbs.checkException {
                testee.format(object : IAssertionGroup {
                    override val name = Untranslatable("test")
                    override val type = RootAssertionGroupType
                    override val subject = 1
                    override val assertions: List<IAssertion> = emptyList()
                }, methodObject)
            }.toThrow<UnsupportedOperationException> { message { toBe(AssertionFormatter.CALL_FORMAT_GROUP) } }
            verbs.checkImmediately(sb).isEmpty()
        }
    }
})
