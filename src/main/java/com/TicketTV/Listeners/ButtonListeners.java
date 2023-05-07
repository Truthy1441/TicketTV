package com.TicketTV.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.EnumSet;

public class ButtonListeners extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        long guildID = event.getGuild().getIdLong();
        Button closeButton = Button.danger("close-button", "Close Ticket");


        if (event.getButton().getId().equals("create_button")) {

            TextChannel ticket = event.getGuild().getCategoryById(event.getChannel().asTextChannel().getParentCategoryId())
                    .createTextChannel("ticket " + event.getUser().getAsTag().split("#")[0])
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))

                    .complete();

            EmbedBuilder panelEmbed = new EmbedBuilder()
                    .setAuthor("Ticket TV", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                    .setFooter("Powered by Truthy#4163", "https://cdn.discordapp.com/attachments/1104507180427792457/1104509010411004026/1000_F_282211183_CUSE4vkvSeuqdKt5lyXKXwop0ZVu5RI8.jpg")
                    .setColor(new Color(100, 200, 150))
                    .setTitle("Selling Account")
                    .setDescription("Ticket created!");
            ticket.sendMessage(event.getUser().getAsMention() + event.getGuild().getOwner().getAsMention()).queue();
            ticket.sendMessageEmbeds(panelEmbed.build())
                    .addActionRow(closeButton).queue();
            event.reply("Ticket Created! " + ticket.getAsMention()).setEphemeral(true).queue();
        }
        if (event.getButton().getId().equals("close-button")) {
            Button confirm = Button.success("Confirm", "Confirm");
            Button cancel = Button.danger("Cancel", "Cancel");
            EmbedBuilder confirmEmbed = new EmbedBuilder()
                    .setTitle("Close Ticket")
                    .addField("Confirm", "Are you sure you want to close this ticket?", false);

            event.replyEmbeds(confirmEmbed.build())
                    .addActionRow(confirm, cancel)
                    .queue();

        }
        if (event.getButton().getId().equals("Confirm")) {
            event.reply("Closing Ticket!").queue();
            event.getChannel().delete().queue();
        }
        if (event.getButton().getId().equals("Cancel")) {
            event.reply("Canceled!").setEphemeral(true).queue();
            event.getMessage().delete().queue();
        }
        }
    }
