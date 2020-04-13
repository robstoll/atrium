@file:Suppress("DEPRECATION" /* TODO remove this suppression with 1.0.0 */)

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultIndentAssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import kotlin.reflect.KClass

abstract class TextIndentAssertionGroupFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextIndentBasedAssertionGroupFormatterSpec<IndentAssertionGroupType>(
    testeeFactory,
    IndentAssertionGroupType::class,
    object : IndentAssertionGroupType {},
    { EmptyNameAndRepresentationAssertionGroup(DefaultIndentAssertionGroupType, it) },
    describePrefix
)
