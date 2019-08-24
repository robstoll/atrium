package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TextExplanatoryBasedAssertionGroupFormatterSpec<T : ExplanatoryAssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: KClass<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<Assertion>) -> AssertionGroup,
    describePrefix: String = "[Atrium] "
) : Spek({

    @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
    include(object : TextIndentBasedAssertionGroupFormatterSpec<T>(
        testeeFactory,
        assertionGroupTypeClass,
        anonymousAssertionGroupType,
        groupFactory,
        describePrefix
    ) {})
})
