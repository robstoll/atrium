//TODO 1.4.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.specs.describeFunTemplate
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class SingleAssertionGroupTypeFormatterSpec<out T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter) -> AssertionFormatter,
    supportedAssertionGroupTypeClass: KClass<T>,
    supportedAssertionGroupType: T,
    supportedAnonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory(
        mapOf(),
        DefaultAssertionFormatterController(),
        ToStringObjectFormatter
    )

    val unsupportedAssertion = object : Assertion {
        override fun holds() = false
    }
    // TODO 1.3.0 replace with representable and remove suppression
    @Suppress("DEPRECATION")
    val unsupportedAssertionGroup = assertionBuilder.customType(object : AssertionGroupType {})
        .withDescriptionAndRepresentation(ch.tutteli.atrium.reporting.translating.Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    // TODO 1.3.0 replace with representable and remove suppression
    @Suppress("DEPRECATION")
    val supportedAssertionGroupWithAnonymousType = assertionBuilder.customType(supportedAnonymousAssertionGroupType)
        .withDescriptionAndRepresentation(ch.tutteli.atrium.reporting.translating.Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    // TODO 1.3.0 replace with representable and remove suppression
    @Suppress("DEPRECATION")
    val supportedAnonymousAssertionGroupWithAnonymousType = object : AssertionGroup {
        override val description = ch.tutteli.atrium.reporting.translating.Untranslatable("test")
        override val type = supportedAnonymousAssertionGroupType
        override val representation = 1
        override val assertions: List<Assertion> = emptyList()
    }
    // TODO 1.3.0 replace with representable and remove suppression
    @Suppress("DEPRECATION")
    val supportedAssertionGroup = assertionBuilder.customType(supportedAssertionGroupType)
        .withDescriptionAndRepresentation(ch.tutteli.atrium.reporting.translating.Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    // TODO 1.3.0 replace with representable and remove suppression
    @Suppress("DEPRECATION")
    val supportedAnonymousAssertionGroup = object : AssertionGroup {
        override val description = ch.tutteli.atrium.reporting.translating.Untranslatable("test")
        override val type = supportedAssertionGroupType
        override val representation = 1
        override val assertions: List<Assertion> = emptyList()
    }

    var sb = StringBuilder()
    var parameterObject = AssertionFormatterParameterObject.new(
        sb,
        alwaysTrueAssertionFilter
    )
    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(
            sb,
            alwaysTrueAssertionFilter
        )
    }

    describeFun(testee::canFormat.name) {
        it("returns true for an anonymous ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroupWithAnonymousType)
            expect(result).toEqual(true)
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroupWithAnonymousType)
            expect(result).toEqual(true)
        }
        it("returns true for an anonymous ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroup)
            expect(result).toEqual(true)
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroup)
            expect(result).toEqual(true)
        }

        it("returns false for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertionGroup)
            expect(result).toEqual(false)
        }

        it("returns false for an object : ${Assertion::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertion)
            expect(result).toEqual(false)
        }
    }

    describeFun(testee::formatNonGroup.name) {
        it("throws an UnsupportedOperationException") {
            expect {
                testee.formatNonGroup(unsupportedAssertion, parameterObject)
            }.toThrow<UnsupportedOperationException> { messageToContain(supportedAssertionGroupTypeClass.fullName) }
            expect(sb).toBeEmpty()
        }
    }

    describeFun(testee::formatGroup.name) {
        val doNotFormatChildren: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit = { _, _ -> }

        it("throws an UnsupportedOperationException for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            expect {
                testee.formatGroup(unsupportedAssertionGroup, parameterObject, doNotFormatChildren)
            }.toThrow<UnsupportedOperationException> { messageToContain(supportedAssertionGroupTypeClass.fullName) }
            expect(sb).toBeEmpty()
        }

        it("does not throw if an anonymous ${AssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroupWithAnonymousType, parameterObject, doNotFormatChildren)
        }

        it("does not throw if an anonymous ${AssertionGroup::class.simpleName} of type ${supportedAssertionGroup::class.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroup, parameterObject, doNotFormatChildren)
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAssertionGroupWithAnonymousType, parameterObject, doNotFormatChildren)
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type  type ${supportedAssertionGroup::class.simpleName}  is passed") {
            testee.formatGroup(supportedAssertionGroup, parameterObject, doNotFormatChildren)
        }
    }
})
