package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class EmptyNameAndSubjectAssertionGroupFormatterSpec<T : AssertionGroupType>(
    verbs: AssertionVerbFactory,
    testeeFactory: (AssertionFormatterController) -> AssertionFormatter,
    assertionGroupClass: KClass<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory(coreFactory.newAssertionFormatterController())

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val testString = "bla blu"
    val testSubject = "testSubject"
    describeFun(AssertionFormatter::formatGroup.name) {

        context("has ${AssertionGroup::description.name} and ${AssertionGroup::representation.name}") {
            mapOf(
                "object: ${assertionGroupClass.simpleName}" to anonymousAssertionGroupType,
                "${assertionGroupType::class.simpleName}" to assertionGroupType
            ).forEach { typeRepresentation, type ->
                context("formatting an ${AssertionGroup::class.simpleName} of type $typeRepresentation") {
                    it("does not include ${AssertionGroup::description.name} nor ${AssertionGroup::representation.name}") {
                        val assertionGroup = AssertImpl.builder.customType(type)
                            .withDescriptionAndRepresentation(TestDescription.TEST_NAME, testSubject)
                            .withAssertions(listOf())
                            .build()
                        val parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
                        testee.formatGroup(assertionGroup, parameterObject, { _, _ -> sb.append(testString) })
                        verbs.checkImmediately(sb.toString())
                            .containsNot(TestDescription.TEST_NAME.getDefault())
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
