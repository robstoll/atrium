package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.AssertionGroupBuilder
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
    verbs,
    testeeFactory,
    ExplanatoryAssertionGroupType::class.java,
    object : ExplanatoryAssertionGroupType {},
    { AssertionGroupBuilder.explanatory.withDefault.create(it) },
    describePrefix
)
