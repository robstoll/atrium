package ch.tutteli.atrium.api.infix.en_GB.creating.encoding

import ch.tutteli.atrium.creating.Expect
import java.nio.charset.Charset
import java.nio.file.Path

/**
 *  Parameter object which combines [path] (as [Path]), [sourceCharset] (as [Charset]), [targetCharset] (as [Charset])
 *  with an [assertionCreator] which defines assertions for a resulting feature of type [E].
 *
 *  Use the function `withEncoding(Path, Charset, Charset) { ... }` to create this representation.
 *
 *  @since 0.13.0
 */
data class EncodingWithCreator<E> internal constructor(val path: Path, val sourceCharset: Charset, val targetCharset: Charset,
                                                       val assertionCreator: Expect<E>)
