@file:Suppress("ObjectPropertyName", "DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.impl.SubjectProviderDomainImpl

@Deprecated("Do no longer use Assert/AssertionPlantNullable, use Expect instead - this interface was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
interface SubjectProviderDomain<T> {
    val subjectProvider: SubjectProvider<T>

    @Deprecated("Do no longer use Assert, use Expect instead - this interface was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
    val changeSubject: SubjectChangerBuilder.DeprecatedKindStep<T>
        get() = SubjectChangerBuilder.create(subjectProvider)

}

@Deprecated("Do no longer use Assert/AssertionPlantNullable, use Expect instead - this property was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
val <T> SubjectProvider<T>._domain: SubjectProviderDomain<T>
    get() = SubjectProviderDomainImpl(this)

