package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.containsNotDefaultTranslationOf
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class EmptyNameAndSubjectAssertionGroupFormatterSpec<T : IAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController) -> IAssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController())

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val testString = "bla blu"
    val testSubject = "testSubject"
    prefixedDescribe("fun ${IAssertionFormatter::formatGroup.name}") {

        context("has ${IAssertionGroup::name.name} and ${IAssertionGroup::subject.name}") {
            mapOf(
                "object: ${assertionGroupClass.simpleName}" to anonymousAssertionGroupType,
                "${assertionGroupType::class.simpleName}" to assertionGroupType
            ).forEach { typeRepresentation, type ->
                context("formatting an ${IAssertionGroup::class.simpleName} of type $typeRepresentation") {
                    it("does not include ${IAssertionGroup::name.name} or ${IAssertionGroup::subject.name}") {
                        val assertionGroup = AssertionGroup(type, TestDescription.TEST_NAME, testSubject, listOf())
                        val methodObject = AssertionFormatterMethodObject(sb, "", 0, alwaysTrueAssertionFilter)
                        testee.formatGroup(assertionGroup, methodObject, { sb.append(testString) })
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
