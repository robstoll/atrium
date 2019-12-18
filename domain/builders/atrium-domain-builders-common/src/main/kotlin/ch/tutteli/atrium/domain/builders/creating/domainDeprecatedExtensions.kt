@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder

@Deprecated("Do no longer use Assert, use Expect instead - this interface was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
inline val <T> SubjectProviderDomain<T>.changeSubject: SubjectChangerBuilder.DeprecatedKindStep<T>
    get() = SubjectChangerBuilder.create(subjectProvider)
