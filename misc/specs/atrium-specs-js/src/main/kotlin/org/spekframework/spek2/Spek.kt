//TODO remove as soon as https://github.com/spekframework/spek/issues/706 is fixed
package org.spekframework.spek2

import org.spekframework.spek2.dsl.Root

@Suppress("UnnecessaryAbstractClass")
abstract class Spek(val root: Root.() -> Unit)
