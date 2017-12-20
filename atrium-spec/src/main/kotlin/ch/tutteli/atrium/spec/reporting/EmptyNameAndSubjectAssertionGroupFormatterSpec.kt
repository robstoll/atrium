package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.containsNotDefaultTranslationOf
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class EmptyNameAndSubjectAssertionGroupFormatterSpec<T : IAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (AssertionFormatterController) -> AssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController())

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val testString = "bla blu"
    val testSubject = "testSubject"
    describeFun(AssertionFormatter::formatGroup.name) {

        context("has ${IAssertionGroup::name.name} and ${IAssertionGroup::subject.name}") {
            mapOf(
                "object: ${assertionGroupClass.simpleName}" to anonymousAssertionGroupType,
                "${assertionGroupType::class.simpleName}" to assertionGroupType
            ).forEach { typeRepresentation, type ->
                context("formatting an ${IAssertionGroup::class.simpleName} of type $typeRepresentation") {
                    it("does not include ${IAssertionGroup::name.name} or ${IAssertionGroup::subject.name}") {
                        val assertionGroup = AssertionGroup(type, TestDescription.TEST_NAME, testSubject, listOf())
                        val methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
                        testee.formatGroup(assertionGroup, methodObject, { _, _ -> sb.append(testString) })
                        verbs.checkImmediately(sb.toString())
                            .containsNotDefaultTranslationOf(TestDescription.TEST_NAME)
                            .containsNot(testSubject)
                            .contains(testString)
                    }
                }
            }
        }
    }
}) {
    enum class TestDescription(override val value: String) : ISimpleTranslatable {
        TEST_NAME("testName"),
    }
}
