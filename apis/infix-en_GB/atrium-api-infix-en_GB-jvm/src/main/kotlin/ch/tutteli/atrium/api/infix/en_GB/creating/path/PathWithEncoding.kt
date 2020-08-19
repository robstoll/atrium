package ch.tutteli.atrium.api.infix.en_GB.creating.path

import java.nio.charset.Charset
import java.nio.file.Path

/**
 *  Parameter object which combines [path] (as [Path]) with a [sourceCharset] and [targetCharset].
 *
 *  Use the function `withEncoding(Path, Charset, Charset)` to create this representation.
 *
 *  @since 0.13.0
 */
data class PathWithEncoding internal constructor(
    val path: Path,
    val sourceCharset: Charset,
    val targetCharset: Charset
)
