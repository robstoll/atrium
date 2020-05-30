package test.test

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Untranslatable
import test.CD1
import test.Expect
import test.domain.KotlinDomain
import test.domain.RootExpect
import test.ext.size
import test.*

fun main(args: Array<String>) {
    fun <T> expect(subject: T): Expect<T> =
        RootExpect(Some(subject)).withImplFactory<CD1> { MyCollectionAssertions.factory(it) }

    expect(listOf(1, 2)).isEmpty().size.toBe(1)
}

class MyCollectionAssertions(oldFactory: () -> CD1) : CD1 by oldFactory() {
    override fun <E> isEmpty(domain: KotlinDomain<Collection<E>>): Assertion =
        BasicDescriptiveAssertion(Untranslatable("is"), Text("empty")) {
            domain.maybeSubject.fold(trueProvider) { !it.isEmpty() }
        }

    companion object {
        fun factory(oldFactory: () -> CD1): () -> CD1 = { MyCollectionAssertions(oldFactory) }
    }
}
