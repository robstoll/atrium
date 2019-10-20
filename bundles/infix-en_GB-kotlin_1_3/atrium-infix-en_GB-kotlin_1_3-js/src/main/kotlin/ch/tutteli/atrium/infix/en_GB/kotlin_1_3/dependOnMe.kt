package ch.tutteli.atrium.infix.en_GB.kotlin_1_3

import ch.tutteli.atrium.core.robstoll.dependOn_atrium_core_robstoll
import ch.tutteli.atrium.domain.robstoll.dependOn_atrium_domain_robstoll

/**
 * Dummy function in order that other modules can define a dependency on atrium-fluent-en_GB-kotlin_1_3-js
 *
 * Moreover it has the side effect that a dependency on core-robstoll and domain-robstoll is established.
 * This is necessary, as it has only a loosely coupled dependency (via serviceLoader).
 */
fun dependOnAtrium() {
    dependOn_atrium_core_robstoll()
    dependOn_atrium_domain_robstoll()
}
