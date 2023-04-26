package cloud.dreamcare.buncord.dsl

import cloud.dreamcare.buncord.internal.util.BuilderRegister
import dev.kord.core.Kord
import dev.kord.rest.builder.interaction.*

public val globalCommands: GlobalMultiApplicationCommandBuilder = GlobalMultiApplicationCommandBuilder()

public fun createGlobalChatInputCommand(name: String, description: String, construct: GlobalChatInputCreateBuilder.() -> Unit): Command = Command(name, description, construct)

public class Command(private val name: String, private val description: String, private val collector: GlobalChatInputCreateBuilder.() -> Unit): BuilderRegister {
    override fun register(kord: Kord) {
        globalCommands.also {
            it.input(name, description, collector)
        }
    }
}