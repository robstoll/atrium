package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.containsNotDefaultTranslationOf
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class EmptyNameAndSubjectAssertionGroupFormatterSpec<T : AssertionGroupType>(
    verbs: AssertionVerbFactory,
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

        context("has ${AssertionGroup::name.name} and ${AssertionGroup::subject.name}") {
            mapOf(
                "object: ${assertionGroupClass.simpleName}" to anonymousAssertionGroupType,
                "${assertionGroupType::class.simpleName}" to assertionGroupType
            ).forEach { typeRepresentation, type ->
                context("formatting an ${AssertionGroup::class.simpleName} of type $typeRepresentation") {
                    it("does not include ${AssertionGroup::name.name} nor ${AssertionGroup::subject.name}") {
                        val assertionGroup = AssertionBuilder.withType(type).create(TestDescription.TEST_NAME, testSubject, listOf())
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
    enum class TestDescription(override val value: String) : StringBasedTranslatable {
        TEST_NAME("testName"),
    }
}
