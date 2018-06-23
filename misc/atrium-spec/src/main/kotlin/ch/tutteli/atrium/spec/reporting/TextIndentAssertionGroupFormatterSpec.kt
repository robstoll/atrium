package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultIndentAssertionGroupType
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory
import kotlin.reflect.KClass

@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 1.0.0")
abstract class TextIndentAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextIndentBasedAssertionGroupFormatterSpec<IndentAssertionGroupType>(
    verbs,
    testeeFactory,
    IndentAssertionGroupType::class,
    object : IndentAssertionGroupType {},
    { EmptyNameAndRepresentationAssertionGroup(DefaultIndentAssertionGroupType, it) },
    describePrefix
)
