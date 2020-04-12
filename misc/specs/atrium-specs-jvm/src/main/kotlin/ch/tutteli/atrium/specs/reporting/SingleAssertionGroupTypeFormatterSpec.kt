package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.isEmpty
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.describeFunTemplate
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class SingleAssertionGroupTypeFormatterSpec<out T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    supportedAssertionGroupTypeClass: KClass<T>,
    supportedAssertionGroupType: T,
    supportedAnonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory(
        mapOf(), coreFactory.newAssertionFormatterController(),
        ToStringObjectFormatter, UsingDefaultTranslator()
    )

    val unsupportedAssertion = object : Assertion {
        override fun holds() = false
    }
    val unsupportedAssertionGroup = ExpectImpl.builder.customType(object : AssertionGroupType {})
        .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    val supportedAssertionGroupWithAnonymousType = ExpectImpl.builder.customType(supportedAnonymousAssertionGroupType)
        .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    val supportedAnonymousAssertionGroupWithAnonymousType = object : AssertionGroup {
        override val description = Untranslatable("test")
        override val type = supportedAnonymousAssertionGroupType
        override val representation = 1
        override val assertions: List<Assertion> = emptyList()
    }
    val supportedAssertionGroup = ExpectImpl.builder.customType(supportedAssertionGroupType)
        .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
        .withAssertions(listOf())
        .build()
    val supportedAnonymousAssertionGroup = object : AssertionGroup {
        override val description = Untranslatable("test")
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
            expect(result).toBe(true)
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroupWithAnonymousType)
            expect(result).toBe(true)
        }
        it("returns true for an anonymous ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroup)
            expect(result).toBe(true)
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroup)
            expect(result).toBe(true)
        }

        it("returns false for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertionGroup)
            expect(result).toBe(false)
        }

        it("returns false for an object : ${Assertion::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertion)
            expect(result).toBe(false)
        }
    }

    describeFun(testee::formatNonGroup.name) {
        it("throws an UnsupportedOperationException") {
            expect {
                testee.formatNonGroup(unsupportedAssertion, parameterObject)
            }.toThrow<UnsupportedOperationException> { messageContains(supportedAssertionGroupTypeClass.qualifiedName!!) }
            expect(sb).isEmpty()
        }
    }

    describeFun(testee::formatGroup.name) {
        val doNotFormatChildren: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit = { _, _ -> }

        it("throws an UnsupportedOperationException for an ${AssertionGroup::class.simpleName} with type object: ${AssertionGroupType::class.simpleName}") {
            expect {
                testee.formatGroup(unsupportedAssertionGroup, parameterObject, doNotFormatChildren)
            }.toThrow<UnsupportedOperationException> { messageContains(supportedAssertionGroupTypeClass.qualifiedName!!) }
            expect(sb).isEmpty()
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
