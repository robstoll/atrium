@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.api.infix.en_GB.creating.path

import ch.tutteli.atrium.logic.utils.VarArgHelper

/**
 *  Parameter object which collects directory entries (as [String]s).
 *  Use the function `directoryEntry(String, vararg String)` to create this representation.
 *
 *  @since 0.14.0
 */
class DirectoryEntries(override val expected: String, override val otherExpected: Array<out String>) :
    VarArgHelper<String>

