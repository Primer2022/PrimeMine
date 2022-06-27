package ru.primer.Mc.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Utils {

    public static void breakEvent(ItemStack itemStack, Player player) {
        int durability = itemStack.getEnchantmentLevel(Enchantment.DURABILITY);

        Random random = new Random();

        if (random.nextInt(100) < (100 / (1 + durability)))
            itemStack.setDurability((short) (itemStack.getDurability() + 1));

        if (itemStack.getDurability() >= itemStack.getType().getMaxDurability()) {
            player.getInventory().removeItem(itemStack);
            return;
        }
    }

    public enum Yaw {
        NORTH, SOUTH, EAST, WEST;

        public static Yaw getYaw(Player player) {
            float yaw = player.getLocation().getYaw();
            yaw = (yaw % 360 + 360) % 360;
            if (yaw >= 135 && yaw <= 225) {
                return Yaw.NORTH;
            }

            if (yaw >= 226 && yaw <= 315) {
                return Yaw.EAST;
            }

            if (yaw >= 45 && yaw <= 134) {
                return Yaw.WEST;
            }

            return Yaw.SOUTH;
        }
    }

    public enum Pitch {
        UP, DOWN, NONE;

        public static Pitch getPitch(Player player) {
            float pitch = player.getLocation().getPitch();
            pitch = (pitch % 180 + 180) % 180;
            if (pitch >= 90 && pitch <= 135) {
                return UP;
            }

            if (pitch >= 35 && pitch <= 90) {
                return DOWN;
            }

            return NONE;
        }
    }
}

