@file:Suppress("DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.creating.AssertDomain
import ch.tutteli.atrium.domain.builders.creating.AssertionPlantNullableDomain

internal class AssertDomainImpl<T : Any>(override val assert: Assert<T>) : AssertDomain<T>

internal class AssertionPlantNullableDomainImpl<T>(override val assert: AssertionPlantNullable<T>) :
    AssertionPlantNullableDomain<T>

