package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainNonNullableImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainOnlyNullableImpl
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.AnyDomainNonNullable
import ch.tutteli.atrium.domain.creating.AnyDomainOnlyNullable
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import kotlin.reflect.KClass

val <T> Expect<T>._domain: AnyDomain<T> get() = AnyDomainImpl(this)
val <T : Any> Expect<T>._domain: AnyDomainNonNullable<T> get() = AnyDomainNonNullableImpl(this, AnyDomainImpl(this))

// we cannot use the same name since:
// - Kotlin has a bug in JS and the same mangled identifier results as for <T: Any> above
// - isA would choose this overload instead of <T>
val <T : Any> Expect<T?>._domainNullable: AnyDomainOnlyNullable<T> get() = AnyDomainOnlyNullableImpl(this)


/**
 * Convenience method for nullable-types which delegates to [AnyDomain.isA].
 */
fun <T : Any> AnyDomainOnlyNullable<T>.notToBeNull(subType: KClass<T>) = expect._domain.isA(subType)

//TODO move next to AnyDomain with 0.10.0 when we fuse the domain modules
/**
 * Returns [SubjectChangerBuilder] - helping you to change the subject of the assertion.
 * In detail, its an `inline` property which returns [SubjectChangerBuilder]
 * which inter alia delegates to the implementation of [SubjectChanger].
 *
 * In case you want to extract a feature (e.g. get the first element of a `List`) instead of changing the subject
 * into another representation (e.g. down-cast `Person` to `Student`) then you should use
 * [feature.extractor][NewFeatureAssertionsBuilder.extractor] instead.
 */
inline val <T> AnyDomain<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder.create(expect)
