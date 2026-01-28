package com.example.tpimod.mixins;

import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DebugHud.class)
public abstract class CopyReachAroundCommandMixin {

    @ModifyVariable(
            method = "getLeftText",
            at = @At("STORE"),
            ordinal = 0
    )
    private String modifyCopyCommand(String original) {
        if (original.startsWith("/execute in ")) {
            String[] parts = original.split(" ");
            String dimension = parts[2];
            String x = parts[6];
            String y = parts[7];
            String z = parts[8];

            String simpleDimension = dimension
                    .replace("minecraft:overworld", "overworld")
                    .replace("minecraft:the_nether", "the_nether")
                    .replace("minecraft:the_end", "the_end");

            return String.format(
                    "/tpi to %s %s %s %s",
                    x, y, z, simpleDimension
            );
        }
        return original;
    }
}