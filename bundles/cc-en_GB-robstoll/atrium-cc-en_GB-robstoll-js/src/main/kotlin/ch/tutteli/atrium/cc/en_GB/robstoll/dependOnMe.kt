package ch.tutteli.atrium.cc.en_GB.robstoll

import ch.tutteli.atrium.domain.robstoll.dependOn_atrium_domain_robstoll
import ch.tutteli.atrium.core.robstoll.dependOn_atrium_core_robstoll

/**
 * Dummy function in order that other modules can define a dependency on atrium-cc-en_GB-robstoll-js
 *
 * Moreover it has the side effect that a dependency on core-robstoll and domain-robstoll is established
 * This is necessary as it has only a loosly coupled dependency (via serviceLoader).
 */
fun dependOnAtrium(){
    dependOn_atrium_core_robstoll()
    dependOn_atrium_domain_robstoll()
}
