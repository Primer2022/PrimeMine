package ru.primer.Mc.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.primer.Mc.utils.Utils;

import java.util.List;

import static ru.primer.Mc.utils.Utils.breakEvent;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Material material = itemStack.getType();

        if (!material.name().contains("PICKAXE")) {
            return;
        }
        Block block = e.getBlock();

        ItemMeta meta = itemStack.getItemMeta();
        if(meta.getLore() == null) {
            return;
        }
        List<String> lore = meta.getLore();

        if (lore.contains("§7Mining 3x3")) {
            if ((Utils.Yaw.getYaw(player) == Utils.Yaw.WEST || Utils.Yaw.getYaw(player) == Utils.Yaw.EAST) && Utils.Pitch.getPitch(player) == Utils.Pitch.NONE) {
                for (int yOff = -1; yOff <= 1; yOff++) {
                    for (int zOff = -1; zOff <= 1; zOff++) {
                        Block currentBlock = block.getRelative(0, yOff, zOff);
                        if (currentBlock.getType() == Material.AIR) {
                            continue;
                        }
                        breakEvent(itemStack, player);
                        currentBlock.breakNaturally(itemStack);
                    }
                }
                return;
            }

            if ((Utils.Yaw.getYaw(player) == Utils.Yaw.NORTH || Utils.Yaw.getYaw(player) == Utils.Yaw.SOUTH) && Utils.Pitch.getPitch(player) == Utils.Pitch.NONE) {
                for (int xOff = -1; xOff <= 1; xOff++) {
                    for (int yOff = -1; yOff <= 1; yOff++) {
                        Block currentBlock = block.getRelative(xOff, yOff, 0);
                        if (currentBlock.getType() == Material.AIR) {
                            continue;
                        }
                        breakEvent(itemStack, player);
                        currentBlock.breakNaturally(itemStack);
                    }
                }
                return;
            }

            if (Utils.Pitch.getPitch(player) == Utils.Pitch.UP || Utils.Pitch.getPitch(player) == Utils.Pitch.DOWN) {
                for (int xOff = -1; xOff <= 1; xOff++) {
                    for (int zOff = -1; zOff <= 1; zOff++) {
                        Block currentBlock = block.getRelative(xOff, 0, zOff);
                        if (currentBlock.getType() == Material.AIR) {
                            continue;
                        }
                        breakEvent(itemStack, player);
                        currentBlock.breakNaturally(itemStack);
                    }
                }
            }
        }
    }
}
