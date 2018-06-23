package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory
import kotlin.reflect.KClass

abstract class TextWarningAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<WarningAssertionGroupType>(
    verbs,
    testeeFactory,
    WarningAssertionGroupType::class,
    WarningAssertionGroupType,
    { AssertImpl.builder.explanatoryGroup.withWarningType.withAssertions(it).build() },
    describePrefix
)
