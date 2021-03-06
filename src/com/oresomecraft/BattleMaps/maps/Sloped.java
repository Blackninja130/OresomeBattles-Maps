package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Sloped extends BattleMap implements IBattleMap, Listener {

    public Sloped() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_BOOTS, Material.BOW, Material.LEATHER_CHESTPLATE, Material.GOLD_LEGGINGS});
    }

    // Map details
    String name = "sloped";
    String fullName = "Sloped";
    String creators = "_Moist, niceman506 and psgs.";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 11, 65, 90, 0, 0));
        redSpawns.add(new Location(w, 7, 65, 90, 0, 0));
        redSpawns.add(new Location(w, 8, 65, 90, 0, 0));
        redSpawns.add(new Location(w, 3, 65, 90, 0, 0));
        redSpawns.add(new Location(w, 2, 65, 90, 0, 0));
        redSpawns.add(new Location(w, -1, 65, 90, 0, 0));
        redSpawns.add(new Location(w, -5, 65, 90, 0, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 11, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, 7, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, 8, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, 3, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, 2, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, -1, 65, 0, 0, 0));
        blueSpawns.add(new Location(w, -5, 65, 0, 0, 0));

        setKoTHMonument(new Location(w, 2, 33, 45));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 2, 34, 45, 2, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack CARROT = new ItemStack(Material.CARROT_ITEM, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack SHEARS = new ItemStack(Material.SHEARS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS});

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        BOW.addEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
        LEATHER_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 4);
        SHEARS.addEnchantment(Enchantment.DIG_SPEED, 10);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

        i.setItem(0, DIAMOND_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, SHEARS);
        i.setItem(3, CARROT);
        i.setItem(4, GOLDEN_APPLE);
        i.setItem(27, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -15;
    public int y1 = 74;
    public int z1 = -6;

    //Bottom right corner.
    public int x2 = 18;
    public int y2 = 28;
    public int z2 = 96;

}