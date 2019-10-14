package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.result


//TODO copy KDoc from another assertion which includes a check, e.g. listAssertions.kt -> get
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> = ExpectImpl.result.isSuccess()
