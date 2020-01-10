@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultIndentAssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import kotlin.reflect.KClass

//TODO remove with 0.10.0 - no need to migrate to spek2
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 0.10.0")
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
