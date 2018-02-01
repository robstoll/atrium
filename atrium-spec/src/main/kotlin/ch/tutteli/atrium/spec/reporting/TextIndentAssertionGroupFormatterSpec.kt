package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory

@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 1.0.0")
abstract class TextIndentAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextIndentBasedAssertionGroupFormatterSpec<IndentAssertionGroupType>(
    verbs,
    testeeFactory,
    IndentAssertionGroupType::class.java,
    object : IndentAssertionGroupType {},
    { EmptyNameAndSubjectAssertionGroup(DefaultIndentAssertionGroupType, it) },
    describePrefix
)
