package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType
import ch.tutteli.atrium.assertions.IndentAssertionGroup
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextIndentAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : IndentBasedAssertionGroupFormatterSpec<IIndentAssertionGroupType>(
    verbs,
    testeeFactory,
    IIndentAssertionGroupType::class.java,
    object : IIndentAssertionGroupType {},
    { IndentAssertionGroup(it) },
    describePrefix
)
