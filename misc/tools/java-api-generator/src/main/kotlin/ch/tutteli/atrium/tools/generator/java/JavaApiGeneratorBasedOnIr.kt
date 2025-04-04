package ch.tutteli.atrium.tools.generator.java

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class JavaApiGeneratorBasedOnIr(
    private val messageCollector: MessageCollector,
    private val generationDataMap: MutableMap<String, GenerationData>
) : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        moduleFragment.accept(JavaApiGeneratorVisitor(messageCollector, generationDataMap), null)
    }
}

// TODO add additionalTypeParameters
data class GenerationData(val sb : StringBuilder, val javaType: String)
