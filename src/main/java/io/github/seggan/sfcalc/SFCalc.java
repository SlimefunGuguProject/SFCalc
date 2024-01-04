package io.github.seggan.sfcalc;

import io.github.seggan.errorreporter.ErrorReporter;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import lombok.Getter;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

@Getter
public class SFCalc extends JavaPlugin implements Listener {

    public static ErrorReporter REPORTER;

    private static SFCalc instance;
    private final Set<RecipeType> blacklistedRecipes = new HashSet<>();
    private final Set<String> blacklistedIds = new HashSet<>();
    private Calculator calculator;
    private StringRegistry stringRegistry;

    @Override
    public void onEnable() {
        instance = this;

        if (!getServer().getPluginManager().isPluginEnabled("GuizhanLibPlugin")) {
            getLogger().log(Level.SEVERE, "本插件需要 鬼斩前置库插件(GuizhanLibPlugin) 才能运行!");
            getLogger().log(Level.SEVERE, "从此处下载: https://50l.cc/gzlib");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getConfig().getBoolean("auto-updates") && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "SlimefunGuguProject", "SFCalc", "master");
        }

        REPORTER = new ErrorReporter("Seggan", "SFCalc", () ->
            "SFCalc " +
            getDescription().getVersion() +
            "\nSlimefun " +
            Slimefun.getVersion() +
            "\nMinecraft " +
            Slimefun.getMinecraftVersion().getName()
        );
        REPORTER.preSend(obj -> !getDescription().getVersion().equals("UNOFFICIAL"));

        REPORTER.setOn(getConfig().getBoolean("error-reports", true));

        REPORTER.executeOrElseReport(() -> {
            new SFCalcMetrics(this);

            stringRegistry = new StringRegistry(getConfig(), new File(getDataFolder(), "config.yml"));
            calculator = new Calculator(this);

            blacklistedRecipes.add(RecipeType.ORE_WASHER);
            blacklistedRecipes.add(RecipeType.GEO_MINER);
            blacklistedRecipes.add(RecipeType.GOLD_PAN);
            blacklistedRecipes.add(RecipeType.MOB_DROP);
            blacklistedRecipes.add(RecipeType.BARTER_DROP);
            blacklistedRecipes.add(RecipeType.ORE_CRUSHER);
            blacklistedRecipes.add(RecipeType.NULL);

            blacklistedIds.add("UU_MATTER");
            blacklistedIds.add("SILICON");
            blacklistedIds.add("FALLEN_METEOR");
            blacklistedIds.add("RUBBER");
            blacklistedIds.add("VOID_BIT");
            if (getConfig().getBoolean("options.use-carbon-instead-of-coal", true)) {
                blacklistedIds.add("CARBON");
            }

            TabExecutor calcCommand = new CalcCommand(this);
            getCommand("sfcalc").setExecutor(calcCommand);
            getCommand("sfcalc").setTabCompleter(calcCommand);
        });
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public Calculator getCalc() {
        return calculator;
    }

}
