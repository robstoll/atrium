@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.core.robstoll

import ch.tutteli.atrium.core.polyfills.registerService

@Suppress("unused" /* here in order that the code is executed when module is loaded */)
private val register = run {

    registerService<ch.tutteli.atrium.core.CoreFactory> { ch.tutteli.atrium.core.robstoll.CoreFactoryImpl() }
}
