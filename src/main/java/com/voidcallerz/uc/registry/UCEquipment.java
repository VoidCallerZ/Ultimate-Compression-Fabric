package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.LinkedHashMap;
import java.util.Map;

public class UCEquipment {

    public static final Map<String, Item> ALL_EQUIPMENT = new LinkedHashMap<>();

    private static final Object[][] TOOL_TIERS = {
        { "wood",      UCToolTiers.COMPRESSED_WOOD,      5.0f, -3.2f },
        { "stone",     UCToolTiers.COMPRESSED_STONE,     6.0f, -3.2f },
        { "copper",    UCToolTiers.COMPRESSED_COPPER,    5.0f, -3.1f },
        { "iron",      UCToolTiers.COMPRESSED_IRON,      7.0f, -3.1f },
        { "gold",      UCToolTiers.COMPRESSED_GOLD,      5.0f, -3.2f },
        { "diamond",   UCToolTiers.COMPRESSED_DIAMOND,   8.0f, -3.0f },
        { "netherite", UCToolTiers.COMPRESSED_NETHERITE, 9.0f, -3.0f },
    };

    public static void register() {
        for (Object[] entry : TOOL_TIERS) {
            String       mat      = (String)       entry[0];
            ToolMaterial material = (ToolMaterial) entry[1];
            float        axeDmg   = (float)        entry[2];
            float        axeSpd   = (float)        entry[3];
            String       pre      = "compressed_" + mat;
            final float fd = axeDmg, fs = axeSpd;

            // In 1.21.5, SwordItem/PickaxeItem/AxeItem/ShovelItem/HoeItem/ArmorItem removed
            // Use Item with props method instead
            reg(pre + "_sword",   name -> new Item(props(name).sword(material, 3, -2.4f)));
            reg(pre + "_pickaxe", name -> new Item(props(name).pickaxe(material, 1, -2.8f)));
            reg(pre + "_axe",     name -> new AxeItem(material, fd, fs,         props(name)));
            reg(pre + "_shovel",  name -> new ShovelItem(material, 1.5f, -3.0f, props(name)));
            reg(pre + "_hoe",     name -> new HoeItem(material, 0, -3.0f,       props(name)));
        }

        armorSet("copper",    UCArmorMaterials.COMPRESSED_COPPER);
        armorSet("iron",      UCArmorMaterials.COMPRESSED_IRON);
        armorSet("gold",      UCArmorMaterials.COMPRESSED_GOLD);
        armorSet("diamond",   UCArmorMaterials.COMPRESSED_DIAMOND);
        armorSet("netherite", UCArmorMaterials.COMPRESSED_NETHERITE);
    }

    private static void armorSet(String mat, ArmorMaterial material) {
        String pre = "compressed_" + mat;
        for (ArmorType type : new ArmorType[]{
                ArmorType.HELMET, ArmorType.CHESTPLATE,
                ArmorType.LEGGINGS, ArmorType.BOOTS}) {
            String name = pre + "_" + type.getName();
            reg(name, n -> new Item(props(n).humanoidArmor(material, type)));
        }
    }

    private static Item.Properties props(String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
        return new Item.Properties().setId(key);
    }

    private static void reg(String name, java.util.function.Function<String, Item> factory) {
        Item item = factory.apply(name);
        Registry.register(BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
        ALL_EQUIPMENT.put(name, item);
    }
}