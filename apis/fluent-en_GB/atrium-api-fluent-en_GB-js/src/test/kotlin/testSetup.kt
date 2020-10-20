//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

import ch.tutteli.atrium.core.robstoll.dependOn_atrium_core_robstoll
import ch.tutteli.atrium.domain.robstoll.dependOn_atrium_domain_robstoll

fun dependOnAtrium() {
    dependOn_atrium_core_robstoll()
    dependOn_atrium_domain_robstoll()
}

@Suppress("unused")
private val establishDependencyToAtrium = dependOnAtrium()
