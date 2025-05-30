//TODO 1.4.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import kotlin.reflect.KClass

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
    testeeFactory,
    ExplanatoryAssertionGroupType::class,
    object : ExplanatoryAssertionGroupType {},
    { assertionBuilder.explanatoryGroup.withDefaultType.withAssertions(it).build() },
    describePrefix
)
