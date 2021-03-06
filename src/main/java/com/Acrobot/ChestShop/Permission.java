package com.Acrobot.ChestShop;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Acrobot
 */
public enum Permission {
    SHOP_CREATION_BUY("ChestShop.shop.create.buy"),
    SHOP_CREATION_BUY_ID("ChestShop.shop.create.buy."),

    SHOP_CREATION_SELL("ChestShop.shop.create.sell"),
    SHOP_CREATION_SELL_ID("ChestShop.shop.create.sell."),

    SHOP_CREATION("ChestShop.shop.create"),
    SHOP_CREATION_ID("ChestShop.shop.create."),

    BUY("ChestShop.shop.buy"),
    BUY_ID("ChestShop.shop.buy."),

    SELL_ID("ChestShop.shop.sell."),
    SELL("ChestShop.shop.sell"),

    ADMIN("ChestShop.admin"),
    MOD("ChestShop.mod"),
    OTHER_NAME("ChestShop.name."),
    GROUP("ChestShop.group."),

    NOFEE("ChestShop.nofee"),
    DISCOUNT("ChestShop.discount."),

    NOTIFY_TOGGLE("ChestShop.toggle");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public static boolean has(CommandSender sender, Permission permission) {
        return has(sender, permission.permission);
    }

    public static boolean has(CommandSender sender, String node) {
        return sender.hasPermission(node) || sender.hasPermission(node.toLowerCase());
    }

    public static boolean otherName(Player player, String name) {
        if (has(player, Permission.ADMIN)) {
            return true;
        }

        return hasPermissionSet(player, OTHER_NAME + name) || hasPermissionSet(player, OTHER_NAME + name.toLowerCase());
    }

    private static boolean hasPermissionSet(CommandSender sender, String permission) {
        return sender.isPermissionSet(permission) && sender.hasPermission(permission);
    }

    public static org.bukkit.permissions.Permission getPermission(Permission permission) {
        org.bukkit.permissions.Permission bukkitPerm = Bukkit.getServer().getPluginManager().getPermission(permission.permission);
        if (bukkitPerm == null) {
            bukkitPerm = permission.getPermission();
            try {
                Bukkit.getServer().getPluginManager().addPermission(bukkitPerm);
            } catch (IllegalArgumentException ignored) {} // this should never happen
        }
        return bukkitPerm;
    }

    public org.bukkit.permissions.Permission getPermission() {
        return new org.bukkit.permissions.Permission(permission);
    }

    public String toString() {
        return permission;
    }
}
