package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.BattleHandler;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Xenon extends BattleMap implements IBattleMap, Listener {

    public Xenon() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "xenon";
    String fullName = "Xenon";
    String creators = "kalikakitty";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, 33, 2, 25);
        Location blueSpawn = new Location(w, 73, 2, -14);

        redSpawns.add(redSpawn);

        blueSpawns.add(blueSpawn);
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 33, 2, 25);
        Location blueSpawn = new Location(w, 73, 2, -14);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 33, 2, -13));
        FFASpawns.add(new Location(w, 73, 2, 25));
        FFASpawns.add(new Location(w, 53, 3, 47));
        FFASpawns.add(new Location(w, 17, 2, 5));
        FFASpawns.add(new Location(w, 53, 3, -36));
        FFASpawns.add(new Location(w, 53, 11, 5));
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(9, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 7;
    public int y1 = 66;
    public int z1 = 52;

    //Bottom right corner.
    public int x2 = 93;
    public int y2 = 0;
    public int z2 = -37;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void preventenderpearldamage(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        TeleportCause cause = event.getCause();
        Location to = event.getTo();
        if (event.getPlayer().getWorld().getName().equals(name)) {
            //Just a reminder, this applies to all maps. (Rogue method)
            if (cause == TeleportCause.ENDER_PEARL) {
                event.setCancelled(true);
                player.teleport(to);
            }
        }
    }

    @EventHandler
    public void onFireBow(EntityShootBowEvent event) {
        if (BattleHandler.activeArena.equals(name)) {

            if (event.getEntityType() == EntityType.PLAYER) {

                Player player = (Player) event.getEntity();
                if (player.getInventory().contains(Material.ARROW)) {
                    event.setCancelled(true);
                    player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
                    player.launchProjectile(EnderPearl.class).setVelocity(event.getProjectile().getVelocity());
                } else {
                    event.setCancelled(true);
                }
            }
        }

    }
}