package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.AssertionGroupBuilder
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory

abstract class TextWarningAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<WarningAssertionGroupType>(
    verbs,
    testeeFactory,
    WarningAssertionGroupType::class.java,
    WarningAssertionGroupType,
    { AssertionGroupBuilder.explanatory.withWarning.create(it) },
    describePrefix
)
