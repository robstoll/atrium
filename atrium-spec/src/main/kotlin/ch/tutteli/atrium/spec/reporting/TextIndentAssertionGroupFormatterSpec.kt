package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType
import ch.tutteli.atrium.assertions.IndentAssertionGroup
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextIndentAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextIndentBasedAssertionGroupFormatterSpec<IIndentAssertionGroupType>(
    verbs,
    testeeFactory,
    IIndentAssertionGroupType::class.java,
    object : IIndentAssertionGroupType {},
    { IndentAssertionGroup(it) },
    describePrefix
)
