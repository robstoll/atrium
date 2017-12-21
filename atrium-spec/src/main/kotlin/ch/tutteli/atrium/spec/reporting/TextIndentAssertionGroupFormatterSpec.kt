package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.IndentAssertionGroup
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextIndentAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextIndentBasedAssertionGroupFormatterSpec<IndentAssertionGroupType>(
    verbs,
    testeeFactory,
    IndentAssertionGroupType::class.java,
    object : IndentAssertionGroupType {},
    { IndentAssertionGroup(it) },
    describePrefix
)
