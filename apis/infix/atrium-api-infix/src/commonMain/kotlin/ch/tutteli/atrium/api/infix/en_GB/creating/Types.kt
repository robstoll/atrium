package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.utils.VarArgHelper
import kotlin.reflect.KClass

/**
 * Represents a [Group] of multiple values.
 *
 * Use the function `types(kclass, ...)` to create this representation.
 *
 * @since 1.1.0
 */
class Types internal constructor(
    override val expected: KClass<*>,
    override val otherExpected: Array<out KClass<*>>
) : VarArgHelper<KClass<*>>
