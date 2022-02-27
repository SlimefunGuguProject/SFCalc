package io.github.seggan.sfcalc;

import io.github.mooy1.infinitylib.commands.SubCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class WebsiteCommand extends SubCommand {

    public WebsiteCommand() {
        super("website", "查看SfCalc在线网站", false);
    }

    @Override
    public void execute(@Nonnull CommandSender commandSender, @Nonnull String[] strings) {
        SFCalc.REPORTER.executeOrElseReport(() -> {
            ClickEvent event = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://sfcalc-online.pages.dev");
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                p.spigot().sendMessage(new ComponentBuilder()
                    .color(ChatColor.YELLOW)
                    .event(event)
                    .append("点击前往网页版SfCalc")
                    .create()
                );
            } else {
                commandSender.sendMessage("https://sfcalc-online.pages.dev");
            }
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender sender, String[] args, List<String> completions) {

    }
}
