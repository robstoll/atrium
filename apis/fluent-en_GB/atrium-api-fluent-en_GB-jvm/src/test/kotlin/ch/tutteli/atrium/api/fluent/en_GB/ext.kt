package test.ext

import test.Expect
import test.domain.KotlinDomain
import test.domain.RootExpect
import test.domain._domain

// extension
interface CD2 {
    fun size(domain: KotlinDomain<Collection<*>>): Expect<Int>
}

val KotlinDomain<Collection<*>>.size get() = _impl.size(this)

@PublishedApi
internal inline val <E> KotlinDomain<Collection<E>>._impl
    get() = getImpl(CD2::class) { DefaultCollectionAssertions() }

class DefaultCollectionAssertions : CD2 {
    override fun size(domain: KotlinDomain<Collection<*>>): Expect<Int> =
        RootExpect(domain.maybeSubject.map { it.size })
}


val <T : Collection<*>> Expect<T>.size get(): Expect<Int> = _domain { size }
