package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT

fun <E, T : Iterator<E>> _hasNext(expect: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(expect, DescriptionBasic.HAS, NEXT_ELEMENT) { it.hasNext() }

fun <E, T : Iterator<E>> _hasNotNext(expect: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(expect, DescriptionBasic.HAS_NOT, NEXT_ELEMENT) { !it.hasNext() }

