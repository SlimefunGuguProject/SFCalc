package io.github.seggan.sfcalc;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.event.Listener;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

@Getter
public class SFCalc extends AbstractAddon implements Listener {

    private static SFCalc instance;
    private final Set<RecipeType> blacklistedRecipes = new HashSet<>();
    private final Set<String> blacklistedIds = new HashSet<>();
    private Calculator calculator;
    private StringRegistry stringRegistry;

    public SFCalc() {
        super("Seggan", "SFCalc", "master", "auto-updates");
    }

    @Nonnull
    static SFCalc inst() {
        return instance;
    }

    @Override
    protected void enable() {
        instance = this;

        stringRegistry = new StringRegistry(getConfig());
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

        getCommand()
            .addSub(new CalcCommand(this))
            .addSub(new NeededCommand(this))
            .addSub(new WebsiteCommand());

    }

    @Override
    protected void disable() {
        instance = null;
    }

    public Calculator getCalc() {
        return calculator;
    }

}
