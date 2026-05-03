package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * In Fabric 1.21.1, ArmorMaterial is registered via Registry.register
 * just like any other registry object.
 */
public class UCArmorMaterials {

    public static final int IRON_DUR      = 15 * 9;
    public static final int GOLD_DUR      = 7  * 9;
    public static final int DIAMOND_DUR   = 33 * 9;
    public static final int NETHERITE_DUR = 37 * 9;

    public static Holder<ArmorMaterial> COMPRESSED_IRON;
    public static Holder<ArmorMaterial> COMPRESSED_GOLD;
    public static Holder<ArmorMaterial> COMPRESSED_DIAMOND;
    public static Holder<ArmorMaterial> COMPRESSED_NETHERITE;

    public static void register() {
        COMPRESSED_IRON = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_iron"),
            new ArmorMaterial(defenseMap(3, 7, 6, 3), 11,
                SoundEvents.ARMOR_EQUIP_IRON,
                () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_iron_ingot")),
                List.of(new ArmorMaterial.Layer(
                    ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_iron"))),
                1.0f, 0.0f));

        COMPRESSED_GOLD = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_gold"),
            new ArmorMaterial(defenseMap(3, 6, 4, 2), 27,
                SoundEvents.ARMOR_EQUIP_GOLD,
                () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_gold_ingot")),
                List.of(new ArmorMaterial.Layer(
                    ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_gold"))),
                0.0f, 0.0f));

        COMPRESSED_DIAMOND = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_diamond"),
            new ArmorMaterial(defenseMap(4, 8, 7, 4), 12,
                SoundEvents.ARMOR_EQUIP_DIAMOND,
                () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_diamond")),
                List.of(new ArmorMaterial.Layer(
                    ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_diamond"))),
                3.0f, 0.0f));

        COMPRESSED_NETHERITE = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_netherite"),
            new ArmorMaterial(defenseMap(4, 9, 8, 4), 18,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_netherite_ingot")),
                List.of(new ArmorMaterial.Layer(
                    ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "compressed_netherite"))),
                4.0f, 0.2f));
    }

    public static int getDurability(Holder<ArmorMaterial> material, ArmorItem.Type type) {
        if (material == COMPRESSED_IRON)      return type.getDurability(IRON_DUR);
        if (material == COMPRESSED_GOLD)      return type.getDurability(GOLD_DUR);
        if (material == COMPRESSED_DIAMOND)   return type.getDurability(DIAMOND_DUR);
        if (material == COMPRESSED_NETHERITE) return type.getDurability(NETHERITE_DUR);
        return 100;
    }

    private static Map<ArmorItem.Type, Integer> defenseMap(
            int helmet, int chestplate, int leggings, int boots) {
        Map<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.HELMET,     helmet);
        map.put(ArmorItem.Type.CHESTPLATE, chestplate);
        map.put(ArmorItem.Type.LEGGINGS,   leggings);
        map.put(ArmorItem.Type.BOOTS,      boots);
        return map;
    }
}