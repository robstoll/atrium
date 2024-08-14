package ch.tutteli.atrium.reporting

import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.rendering.TextColors.cyan
import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Padding
import kotlin.test.Test


class Main {
    @Test
    fun foo() {

        val t = Terminal(ansiLevel = AnsiLevel.TRUECOLOR)

        val message: com.github.ajalt.mordant.rendering.TextStyle = TextColors.red
        val a = message.invoke("bla")



        t.println(a)
        error("stop")
        t.println(
            table {
                body {
                    this.align = TextAlign.LEFT
                    this.cellBorders = Borders.ALL
                    row {
                        cell("I expected the subject") { columnSpan = 2 }
                        cells(":", "1")
                    }
                    row {
                        cell(red("x"))
                        cells("to equal", ":", "2")
                    }
                    row {
                        cell("x")
                        cell(table {
                            this.padding = Padding(0, 0, 0, 0)
                            body {
                                row {
                                    cell("->")
                                    cell(cyan("name")) { columnSpan = 2 }
                                    cells(":", "Robert")
                                }
                                row {
                                    cells("", "x", "to start with", ":", "rob")
                                }
                                row {
                                    cells("", "x", "to end with", ":", "rob")
                                }
                            }
                        }) {
                            padding = Padding(0, 0, 0, 0)
                            columnSpan = Int.MAX_VALUE
                        }
                    }
                }
            }
        )
//        val style = (bold + black + strikethrough)
//        throw AtriumError.create(
//
//            NoOpAtriumErrorAdjuster
//        )
    }
}
