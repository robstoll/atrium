package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.isEmpty
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

open class AssertionFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter
) : Spek({

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter(), UsingDefaultTranslator())

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject(sb, 0, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject(sb, 0, alwaysTrueAssertionFilter)
    }

    describe("fun ${testee::format.name}") {
        it("throws an UnsupportedOperationException if ${IAssertionGroup::class.simpleName} is passed") {
            verbs.checkException {
                testee.format(object: IAssertionGroup {
                    override val name = Untranslatable("test")
                    override val type = RootAssertionGroupType
                    override val subject = 1
                    override val assertions: List<IAssertion> = emptyList()
                }, methodObject)
            }.toThrow<UnsupportedOperationException>().and.message.toBe(IAssertionFormatter.CALL_FORMAT_GROUP)
            verbs.checkImmediately(sb).isEmpty()
        }
    }
})
