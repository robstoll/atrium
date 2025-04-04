package ch.tutteli.atrium.tools.generator.java

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.appendln
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.kbox.takeIf
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.backend.js.utils.valueArguments
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.types.toKotlinType
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.statements
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor
import org.jetbrains.kotlin.js.descriptorUtils.getKotlinTypeFqName
import org.jetbrains.kotlin.renderer.render
import org.jetbrains.kotlin.types.checker.SimpleClassicTypeSystemContext.typeConstructor

@OptIn(ObsoleteDescriptorBasedAPI::class)
class JavaApiGeneratorVisitor(
    private val messageCollector: MessageCollector,
    private val generationDataMap: MutableMap<String, GenerationData>
) : IrElementVisitor<Unit, Nothing?> {

    override fun visitElement(element: IrElement, data: Nothing?) {
        element.dump()
        element.acceptChildren(this, data)
    }

    override fun visitFunction(declaration: IrFunction, data: Nothing?) {
        val receiverType = declaration.extensionReceiverParameter?.type

        if (declaration.visibility.isPublicAPI && receiverType != null && Expect::class.qualifiedName == receiverType.classFqName?.render()
        // for debugging a single function
        //&& declaration.name.toString() == "notToEqualNull"
        ) {
            val upperBound = receiverType.toKotlinType().arguments.first().type
            val subjectType = upperBound.getKotlinTypeFqName(printTypeArguments = false)
            generationDataMap.computeIfAbsent(subjectType) { _ ->
                GenerationData(
                    StringBuilder(), when (subjectType) {
                        "kotlin.Any" -> "Object"
                        "kotlin.Array" -> upperBound.arguments.first().toString()
                        else -> subjectType
                    }
                )
            }.sb.append(renderFunction(declaration)).appendln()
        }
    }

    override fun visitProperty(declaration: IrProperty, data: Nothing?) {
        println("ppp> ${declaration.name} getterReturnType:${declaration.getter?.returnType} isExpect:${declaration.isExpect}")
    }

    private fun renderFunction(declaration: IrFunction): String {
        val (parameters, body) = if (declaration.isInline) {
            val call = (declaration.nonNullBody.statements.single() as IrReturn).value as IrCall
            (call.dispatchReceiver as? IrCall)?.takeIf { it.symbol.owner.body != null }?.let { dispatcherCall ->
                val receiver =
                    dispatcherCall.symbol.descriptor.returnType?.getKotlinTypeFqName(printTypeArguments = false)
                takeIf(receiver == FeatureExtractorBuilder.ExecutionStep::class.fullName || receiver == SubjectChangerBuilder.ExecutionStep::class.fullName) {
                    // it's more complex than that, for now we don't have cases were we need to remap but if we should
                    // the we need to take info account whether it is a param of the declaration or another expression.
                    // in case of other we need to rewrite.
//                    val mapParamToPassedArgs =
//                        dispatcherCall.symbol.owner.valueParameters.zip(dispatcherCall.valueArguments)
//                            .associate { (param, arg) ->
//                                param.name.toString() to arg!!
//                            }
                    val dispatcherExpression = renderFunctionBody(
                        dispatcherCall.symbol.owner.nonNullBody, emptyMap()
                    )
                    Pair(
                        renderFunctionParams(declaration.valueParameters)
                            .let { args -> if (args.isNotBlank()) "$args, " else args } +
                            renderFunctionParams(dispatcherCall.symbol.owner.valueParameters),
                        // also this is simplified, if we should have params which are not delegated through, then we
                        // would not need to add it the function
                        "$dispatcherExpression.${call.symbol.descriptor.name}(${
                            renderFnCallArguments(call, emptyMap())
                        })"
                    )
                }
            } ?: Pair("", "")
        } else {
            renderFunctionParams(declaration.valueParameters) to renderFunctionBody(declaration.nonNullBody)
        }
        return """
            |public SelfT ${declaration.name}(${parameters}) {
            |$body
            |}
            """.trimMargin()
    }


    private val IrFunction.nonNullBody
        get() = body ?: run {
            throw IllegalStateException("function $name without body")
        }

    private fun renderFunctionParams(valueParameters: List<IrValueParameter>): String =
        valueParameters.joinToString(", ") {
            "${it.type.toKotlinType().typeConstructor()} ${it.name}"
        }

    private fun renderFunctionBody(body: IrBody, paramsToRewrite: Map<String, IrExpression> = emptyMap()): String {
        return ((body.statements.singleOrNull() as? IrReturn)?.value as? IrCall)?.let { irCall ->
            when {
                irCall.symbol.owner.name.asStringStripSpecialMarkers() == "_logicAppend" -> renderLogicAppendBody(
                    irCall,
                    paramsToRewrite
                )

                (irCall.extensionReceiver as? IrCall)?.symbol?.owner?.name?.asStringStripSpecialMarkers() == "get-_logic" ->
                    """
                    |$turnIntoContainerViaLogic
                    |return ${renderCall(irCall, Some("container"))};
                    """.trimMargin()

                else -> null
            }
        } ?: run {
            messageCollector.report(CompilerMessageSeverity.ERROR, "not supported body\n" + body.dump())
            """
            |// TODO implement
            |throw new UnsupportedOperationException("not automatically generated, needs to be implemented manually")
            """.trimMargin()
        }
    }

    private fun renderLogicAppendBody(irCall: IrCall, paramsToRewrite: Map<String, IrExpression>): String {
        val fnCall =
            ((irCall.valueArguments.single() as IrFunctionExpression).function.nonNullBody.statements.first() as IrReturn).value as IrCall
        return """
        |    $turnIntoContainerViaLogic
        |    return switchCoreExpect(container.append(${fnCall.symbol.owner.fqNameWhenAvailable}(container, ${
            renderFnCallArguments(fnCall, paramsToRewrite)
        })));
        """.trimMargin()
    }

    private fun renderFnCallArguments(fnCall: IrCall, paramsToRewrite: Map<String, IrExpression>): String =
        fnCall.valueArguments.joinToString(",") {
            renderStatement(it ?: error("arg was null"), paramsToRewrite)
        }

    private fun renderStatement(statement: IrStatement, paramsToRewrite: Map<String, IrExpression>): String =
        when (statement) {
            is IrCall -> renderCall(statement, None)

            is IrGetValue -> statement.symbol.descriptor.name.toString()
              //  .let { s -> paramsToRewrite[s]?.let { expression -> renderStatement(expression, emptyMap()) } ?: s }

            else -> {
                messageCollector.report(CompilerMessageSeverity.ERROR, "not supported statement\n" + statement.dump())
                "new UnsupportedOperationException(\"not automatically generated, needs to be implemented manually\")"
            }
        }

    private fun renderCall(irCall: IrCall, receiver: Option<String>): String =
        "${irCall.symbol.owner.fqNameWhenAvailable.toString()}(${
            receiver.fold({ "" }, { "$it, " })
        }${irCall.valueArguments.joinToString(",")})"


    companion object {
        private const val turnIntoContainerViaLogic =
            "AssertionContainer<SubjectT> container = LogicKt.get_logic(coreExpect);"

    }
}
