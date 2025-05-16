package ru.moonmag;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpaceRGBFix extends JavaPlugin implements Listener {

    private static final Pattern HEX_PATTERN = Pattern.compile("(&#|<#)([0-9a-fA-F]{6})(>|&)?");

    @Override
    public void onEnable() {
        getLogger().info("§x§f§f§7§c§0§0╔");
        getLogger().info("§x§f§f§7§c§0§0║ §fЗапуск плагина...");
        getLogger().info("§x§f§f§7§c§0§0║ §x§0§0§f§f§1§7Плагин запустился! §fКодер: §x§f§f§6§e§0§0SpaceDev");
        getLogger().info("§x§f§f§7§c§0§0║ §x§0§0§f§5§f§fh§x§0§0§f§4§f§ft§x§0§0§f§3§f§ft§x§0§0§f§2§f§fp§x§0§0§f§1§f§fs§x§0§0§e§f§f§f:§x§0§0§e§e§f§f/§x§0§0§e§d§f§f/§x§0§0§e§c§f§ft§x§0§0§e§b§f§f.§x§0§0§e§a§f§fm§x§0§0§e§9§f§fe§x§0§0§e§8§f§f/§x§0§0§e§7§f§fs§x§0§0§e§5§f§fp§x§0§0§e§4§f§fa§x§0§0§e§3§f§fc§x§0§0§e§2§f§fe§x§0§0§e§1§f§fs§x§0§0§e§0§f§ft§x§0§0§d§f§f§fu§x§0§0§d§e§f§fd§x§0§0§d§c§f§fi§x§0§0§d§b§f§fo§x§0§0§d§a§f§fm§x§0§0§d§9§f§fc");
        getLogger().info("§x§f§f§7§c§0§0╚");
        getServer().getPluginManager().registerEvents(this, this);
        new ru.moonmag.Metrics(this, 25597);
        getServer().getPluginManager().registerEvents(new ChatFormatFixer(), this);
        new UpdateChecker(this).checkForUpdates();
    }

    @Override
    public void onDisable() {
        getLogger().info("§x§f§f§7§c§0§0╔");
        getLogger().info("§x§f§f§7§c§0§0║ §fОтключение плагина...");
        getLogger().info("§x§f§f§7§c§0§0║ §x§f§f§0§0§0§0Плагин отключился! §fКодер: §x§f§f§6§e§0§0SpaceDev");
        getLogger().info("§x§f§f§7§c§0§0║ §x§0§0§f§5§f§fh§x§0§0§f§4§f§ft§x§0§0§f§3§f§ft§x§0§0§f§2§f§fp§x§0§0§f§1§f§fs§x§0§0§e§f§f§f:§x§0§0§e§e§f§f/§x§0§0§e§d§f§f/§x§0§0§e§c§f§ft§x§0§0§e§b§f§f.§x§0§0§e§a§f§fm§x§0§0§e§9§f§fe§x§0§0§e§8§f§f/§x§0§0§e§7§f§fs§x§0§0§e§5§f§fp§x§0§0§e§4§f§fa§x§0§0§e§3§f§fc§x§0§0§e§2§f§fe§x§0§0§e§1§f§fs§x§0§0§e§0§f§ft§x§0§0§d§f§f§fu§x§0§0§d§e§f§fd§x§0§0§d§c§f§fi§x§0§0§d§b§f§fo§x§0§0§d§a§f§fm§x§0§0§d§9§f§fc");
        getLogger().info("§x§f§f§7§c§0§0╚");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        message = applyHexFormatting(message);
        message = ChatColor.translateAlternateColorCodes('&', message);

        event.setMessage(message);
    }

    private String applyHexFormatting(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hexColor = matcher.group(2);
            matcher.appendReplacement(buffer, "§x" + convertHex(hexColor));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private String convertHex(String hex) {
        StringBuilder builder = new StringBuilder();
        for (char c : hex.toCharArray()) {
            builder.append("§").append(c);
        }
        return builder.toString();
    }
}