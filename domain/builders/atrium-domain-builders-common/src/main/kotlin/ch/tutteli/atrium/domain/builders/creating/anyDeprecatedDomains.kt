@file:Suppress("DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.creating.impl.AssertDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AssertionPlantNullableDomainImpl

@Deprecated("Do no longer use Assert, use Expect instead - this interface was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
interface AssertDomain<T : Any> {
    val assert: Assert<T>
}

@Deprecated("Do no longer use Assert, use Expect instead - this interface was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
interface AssertionPlantNullableDomain<T> {
    val assert: AssertionPlantNullable<T>
}

@Deprecated("Do no longer use Assert, use Expect instead - this property was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
val <T : Any> Assert<T>._domain: AssertDomain<T>
    get() = AssertDomainImpl(this)


@Deprecated("Do no longer use Assert, use Expect instead - this property was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
val <T> AssertionPlantNullable<T>._domain: AssertionPlantNullableDomain<T>
    get() = AssertionPlantNullableDomainImpl(this)
