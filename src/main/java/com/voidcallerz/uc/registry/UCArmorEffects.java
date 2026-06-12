package com.voidcallerz.uc.registry;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Fabric version — uses ServerTickEvents instead of event bus.
 */
public class UCArmorEffects {

    private static final int CHECK_INTERVAL = 20;
    private static final int DURATION       = 40;

    private record ArmorSet(String prefix, List<MobEffectInstance> effects) {}

    private static final List<ArmorSet> ARMOR_SETS = List.of(
        new ArmorSet("compressed_copper", List.of(
            effect(MobEffects.RESISTANCE, 0)
        )),
        new ArmorSet("compressed_iron", List.of(
            effect(MobEffects.STRENGTH, 0)
        )),
        new ArmorSet("compressed_gold", List.of(
            effect(MobEffects.SPEED, 1)
        )),
        new ArmorSet("compressed_diamond", List.of(
            effect(MobEffects.STRENGTH,   1),
            effect(MobEffects.RESISTANCE, 0)
        )),
        new ArmorSet("compressed_netherite", List.of(
            effect(MobEffects.STRENGTH,    1),
            effect(MobEffects.REGENERATION, 0),
            effect(MobEffects.RESISTANCE,  1)
        ))
    );

    private static MobEffectInstance effect(
            net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> e, int amp) {
        return new MobEffectInstance(e, DURATION, amp, false, false, true);
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(UCArmorEffects::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(player -> {
            if (player.tickCount % CHECK_INTERVAL != 0) return;
            for (ArmorSet set : ARMOR_SETS) {
                if (isWearingFullSet(player, set.prefix())) {
                    for (MobEffectInstance template : set.effects()) {
                        MobEffectInstance current = player.getEffect(template.getEffect());
                        if (current == null
                                || current.getAmplifier() < template.getAmplifier()
                                || current.getDuration() < CHECK_INTERVAL) {
                            player.addEffect(new MobEffectInstance(
                                template.getEffect(), DURATION,
                                template.getAmplifier(), false, false, true));
                        }
                    }
                }
            }
        });
    }

    private static boolean isWearingFullSet(Player player, String prefix) {
        return isPiece(player.getItemBySlot(EquipmentSlot.HEAD), prefix + "_helmet")
            && isPiece(player.getItemBySlot(EquipmentSlot.CHEST), prefix + "_chestplate")
            && isPiece(player.getItemBySlot(EquipmentSlot.LEGS), prefix + "_leggings")
            && isPiece(player.getItemBySlot(EquipmentSlot.FEET), prefix + "_boots");
    }

    private static boolean isPiece(ItemStack stack, String registryName) {
        if (stack.isEmpty()) return false;
        Item item = UCEquipment.ALL_EQUIPMENT.get(registryName);
        return item != null && stack.getItem() == item;
    }
}