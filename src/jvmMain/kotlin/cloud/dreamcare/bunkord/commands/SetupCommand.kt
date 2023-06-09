package cloud.dreamcare.bunkord.commands

import cloud.dreamcare.bunkord.dsl.Command
import cloud.dreamcare.bunkord.dsl.createGlobalChatInputCommand
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import dev.kord.rest.builder.interaction.*

public class SetupCommand {
    public fun register(): Command = createGlobalChatInputCommand("setup", "Bot Setup") {
        defaultMemberPermissions = Permissions { Permission.ManageGuild }
        dmPermission = false

        subCommand("greeter", "configure boost announcement") {
            channel("channel", "Send greeting message to this channel") { required = true }
            boolean("on-join", "Greet member when they join")
            role("on-role", "Greet member when they get this role")
            string("message", "The greeting message")
        }

        group("openai", "Configure OpenAI integration") {
            subCommand("token", "configure the token") {
                string("token", "token") { required = true }
            }

            subCommand("chatgpt", "ChatGPT settings") {
                string("model", "ChatGPT model") { required = true; autocomplete = true }
                string("personality", "The personality of the bot")
            }
        }

        group("roles", "configure roles") {
            subCommand("menu", "Add/Edit menu") {
                string("menu", "Menu") { required = true; autocomplete = true }
                string("title", "Menu title") { required = true }
                string("description", "Menu description")
            }

            subCommand("reaction", "Add/Edit role reaction option") {
                string("menu", "Menu to add a reaction option to") { required = true; autocomplete = true }
                string("emoji", "The emoji to use") { required = true }
                role("role", "The role to assign") { required = true }
                string("description", "Description to display (default: roll name)")
            }
        }
    }
}
