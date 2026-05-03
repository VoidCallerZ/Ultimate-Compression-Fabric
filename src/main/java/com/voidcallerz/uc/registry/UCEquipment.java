package com.voidcallerz.uc.registry;

import com.voidcallerz.uc.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class UCEquipment {

    public static final Map<String, Item> ALL_EQUIPMENT = new LinkedHashMap<>();

    private static final Object[][] TOOL_TIERS = {
        { "wood",      UCToolTiers.COMPRESSED_WOOD      },
        { "stone",     UCToolTiers.COMPRESSED_STONE     },
        { "iron",      UCToolTiers.COMPRESSED_IRON      },
        { "gold",      UCToolTiers.COMPRESSED_GOLD      },
        { "diamond",   UCToolTiers.COMPRESSED_DIAMOND   },
        { "netherite", UCToolTiers.COMPRESSED_NETHERITE },
    };

    public static void register() {
        // Tools
        for (Object[] entry : TOOL_TIERS) {
            String      mat  = (String)      entry[0];
            UCToolTiers tier = (UCToolTiers) entry[1];
            String      pre  = "compressed_" + mat;
            reg(pre + "_sword",   new SwordItem(tier,   new Item.Properties()));
            reg(pre + "_pickaxe", new PickaxeItem(tier, new Item.Properties()));
            reg(pre + "_axe",     new AxeItem(tier,     new Item.Properties()));
            reg(pre + "_shovel",  new ShovelItem(tier,  new Item.Properties()));
            reg(pre + "_hoe",     new HoeItem(tier,     new Item.Properties()));
        }

        // Armor
        armorSet("iron",      UCArmorMaterials.COMPRESSED_IRON);
        armorSet("gold",      UCArmorMaterials.COMPRESSED_GOLD);
        armorSet("diamond",   UCArmorMaterials.COMPRESSED_DIAMOND);
        armorSet("netherite", UCArmorMaterials.COMPRESSED_NETHERITE);
    }

    private static void armorSet(String mat, Holder<ArmorMaterial> material) {
        String pre = "compressed_" + mat;
        for (ArmorItem.Type type : new ArmorItem.Type[]{
                ArmorItem.Type.HELMET, ArmorItem.Type.CHESTPLATE,
                ArmorItem.Type.LEGGINGS, ArmorItem.Type.BOOTS}) {
            reg(pre + "_" + type.getName(),
                new ArmorItem(material, type,
                    new Item.Properties().durability(
                        UCArmorMaterials.getDurability(material, type))));
        }
    }

    private static void reg(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name), item);
        ALL_EQUIPMENT.put(name, item);
    }
}