package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator

fun main(args: Array<String>) {
    val root = Root(
        Untranslatable("expected the subject"), "1", listOf(
            SimpleProof(
                Untranslatable("starts with"), "hello"
            ) { false },
            SimpleProof(Untranslatable("ends with"), "test") { true },
            Paragraph(
                Icon.DEBUG_INFO,
                Text2("the closest existing parent directory is /usr/bin")
            ),
            Paragraph(
                Icon.INFORMATION_SOURCE,
                Text2("because, xyz"),
                withIndent = false
            ),

            Feature(
                Untranslatable("name"),
                Text2("robert", Style.FAILURE),
                listOf(
                    SimpleProof(Untranslatable("to equal"), 1) { false },
                    Paragraph(
                        Icon.BANGBANG,
                        Text2("failure at parent path"),
                        withIndent = true
                    ),
                    Feature(
                        Untranslatable("age"),
                        10,
                        listOf(
                            SimpleProof(Untranslatable("to equal"), 2) { false },
                            Paragraph(
                                Icon.BULB,
                                Text2("notice, MyClass has not overridden equals\nbetter use toBeSameInstanceAs or implement equals/hashCode\n")
                            )
                        )
                    )
                )
            )
        )
    )
    val translator = UsingDefaultTranslator()
//    val objectFormatter = DefaultTextObjectFormatter(translator)
    val styler = DefaultStyler(Ansi8Colours())
    val iconStyler = DefaultIconStyler(styler)
    val objFormatter = DefaultTextObjFormatter(styler)
    val sb = DefaultTextReporter(
        DefaultPreRenderController(
            FailingProofsAndOthers,
            sequenceOf(
                DefaultSimpleProofPreRenderer(translator, objFormatter),
                DefaultParagraphPreRenderer(),
                DefaultTextPreRenderer(styler),
                DefaultIconPreRenderer(iconStyler),
                DefaultFeaturePreRenderer(translator, objFormatter, iconStyler),
                DefaultRootPreRenderer(translator, objFormatter)
            ),
            iconStyler
        )
    ).createReport(root)
    println(sb)
}
