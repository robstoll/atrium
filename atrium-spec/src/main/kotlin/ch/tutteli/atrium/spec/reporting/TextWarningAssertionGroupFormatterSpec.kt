package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.AssertionGroupBuilder
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextWarningAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<WarningAssertionGroupType>(
    verbs,
    testeeFactory,
    WarningAssertionGroupType::class.java,
    WarningAssertionGroupType,
    { AssertionGroupBuilder.explanatory.withWarning.create(it) },
    describePrefix
)
