package ch.tutteli.atrium.tools.generator.java

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

class JavaApiGeneratorPluginComponentRegistrar(private val generationDataMap: MutableMap<String, GenerationData>) : CompilerPluginRegistrar() {
    override val supportsK2 = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        val messageCollector = configuration.get(CLIConfigurationKeys.ORIGINAL_MESSAGE_COLLECTOR_KEY, MessageCollector.Companion.NONE)
        IrGenerationExtension.Companion.registerExtension(JavaApiGeneratorBasedOnIr(messageCollector, generationDataMap))
    }
}
