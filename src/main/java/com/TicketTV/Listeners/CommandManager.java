package com.TicketTV.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("ALL")
public class CommandManager extends ListenerAdapter {
    Button verifyButton = Button.secondary("verifyButton", "✅");

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String version = "v.1.0.0";
        boolean isAdmin = false;
        String command = event.getName();

        EmbedBuilder accessDeniedEmbed = new EmbedBuilder()
                .setAuthor("Ticket TV", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                .setFooter("Powered by Truthy#4163", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                .setColor(Color.red)
                .setTitle("Access denied")
                .setDescription("You don't have the permission to use this command!");

        if (command.equals("panel")) {
            if (event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
                event.reply("Creating panel").setEphemeral(true).queue();

                EmbedBuilder panelEmbed = new EmbedBuilder()
                        .setAuthor("Open a ticket!", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                        .setFooter("Powered by Truthy#4163", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                        .setColor(new Color(100, 200, 150))
                        .setTitle("Open a ticket!")
                        .setDescription("Press on the button below to open a ticket!");

                Button buyButton = Button.secondary("create_button", "💌");

                Message panelMessage = event.getChannel().sendMessageEmbeds(panelEmbed.build())
                        .addActionRow(buyButton)
                        .complete();
            } else {
                event.replyEmbeds(accessDeniedEmbed.build()).setEphemeral(true).queue();
            }
        }

        if (command.equals("category")) {
            if (event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {

            }
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("panel", "Create a ticket panel!").addOptions());

        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
