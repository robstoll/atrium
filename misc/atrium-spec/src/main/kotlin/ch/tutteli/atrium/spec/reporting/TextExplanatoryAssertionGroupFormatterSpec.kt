package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.AssertionVerbFactory
import kotlin.reflect.KClass

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
    verbs,
    testeeFactory,
    ExplanatoryAssertionGroupType::class,
    object : ExplanatoryAssertionGroupType {},
    { AssertImpl.builder.explanatoryGroup.withDefaultType.withAssertions(it).build() },
    describePrefix
)
