package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class TheBowl extends BattleMap implements IBattleMap, Listener {

    public TheBowl() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
    }

    // Map details
    String name = "bowl";
    String fullName = "The Bowl";
    String creators = "_Moist, psgs, niceman506 and broddikill";
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.KOTH};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 10, 82, -36, 0, 0);
        Location blueSpawn = new Location(w, 4, 84, 38, 179, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 21, 72, -26, 0, 0));
        redSpawns.add(new Location(w, -7.5, 70, -28, -35, 0));
        redSpawns.add(new Location(w, -21, 72, -12, -30, 0));
        redSpawns.add(new Location(w, 30, 72, -16, 90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 10, 74, 42, -140, 0));
        blueSpawns.add(new Location(w, 29, 70, 31, 90, 0));
        blueSpawns.add(new Location(w, -20, 76, 20, -30, 0));
        blueSpawns.add(new Location(w, -7, 68, 16, 90, 0));

        setKoTHMonument(new Location(w, 7, 82, -1));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 10, 82, -36, 0, 0));
        FFASpawns.add(new Location(w, 4, 84, 38, 179, 0));
        FFASpawns.add(new Location(w, 21, 72, -26, 0, 0));
        FFASpawns.add(new Location(w, -7.5, 70, -28, -35, 0));
        FFASpawns.add(new Location(w, -21, 72, -12, -30, 0));
        FFASpawns.add(new Location(w, 30, 72, -16, 90, 0));
        FFASpawns.add(new Location(w, 10, 74, 42, -140, 0));
        FFASpawns.add(new Location(w, 29, 70, 31, 90, 0));
        FFASpawns.add(new Location(w, -20, 76, 20, -30, 0));
        FFASpawns.add(new Location(w, -7, 68, 16, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(35, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 20, 2));

    }

    // Top left corner.
    public int x1 = -8;
    public int y1 = 164;
    public int z1 = 16;

    //Bottom right corner.
    public int x2 = -85;
    public int y2 = 62;
    public int z2 = 99;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(name)) {
            if (getMode().equals(Gamemode.INFECTION)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(name)) {
            if (getMode().equals(Gamemode.INFECTION)) {
                event.setCancelled(true);
            }
        }
    }
}
