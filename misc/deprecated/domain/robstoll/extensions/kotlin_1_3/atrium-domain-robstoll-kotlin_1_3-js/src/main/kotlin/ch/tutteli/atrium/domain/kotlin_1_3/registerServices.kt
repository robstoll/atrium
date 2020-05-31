@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.kotlin_1_3

import ch.tutteli.atrium.core.polyfills.registerService

@Suppress("unused" /* here in order that the code is executed when module is loaded */)
private val register = run {

    registerService<ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions> { ch.tutteli.atrium.domain.robstoll.kotlin_1_3.creating.ResultAssertionsImpl() }
}
