package com.voidcallerz.uc.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

// Identical to Forge/NeoForge — Tier interface is vanilla
public enum UCToolTiers implements Tier {

    COMPRESSED_WOOD(531, 2.5f, 0.5f,
        BlockTags.INCORRECT_FOR_WOODEN_TOOL, 15,
        () -> Ingredient.of(UCBlocks.ALL_BLOCKS.get("compressed_oak_planks"))),

    COMPRESSED_STONE(1179, 4.5f, 1.5f,
        BlockTags.INCORRECT_FOR_STONE_TOOL, 5,
        () -> Ingredient.of(UCBlocks.ALL_BLOCKS.get("compressed_cobblestone"))),

    COMPRESSED_IRON(2250, 6.5f, 2.5f,
        BlockTags.INCORRECT_FOR_IRON_TOOL, 14,
        () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_iron_ingot"))),

    COMPRESSED_GOLD(288, 12.5f, 0.5f,
        BlockTags.INCORRECT_FOR_GOLD_TOOL, 22,
        () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_gold_ingot"))),

    COMPRESSED_DIAMOND(14049, 8.5f, 3.5f,
        BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 10,
        () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_diamond"))),

    COMPRESSED_NETHERITE(18279, 9.5f, 4.5f,
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 15,
        () -> Ingredient.of(UCItemRegistry.ALL_ITEMS.get("compressed_netherite_ingot")));

    private final int durability;
    private final float speed, attackDamageBonus;
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int enchantability;
    private final java.util.function.Supplier<Ingredient> repairIngredient;

    UCToolTiers(int dur, float speed, float atk, TagKey<Block> tag, int ench,
                java.util.function.Supplier<Ingredient> repair) {
        this.durability = dur; this.speed = speed; this.attackDamageBonus = atk;
        this.incorrectBlocksForDrops = tag; this.enchantability = ench;
        this.repairIngredient = repair;
    }

    @Override public int getUses()                { return durability; }
    @Override public float getSpeed()             { return speed; }
    @Override public float getAttackDamageBonus() { return attackDamageBonus; }
    @Override public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
    @Override public int getEnchantmentValue()    { return enchantability; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
}