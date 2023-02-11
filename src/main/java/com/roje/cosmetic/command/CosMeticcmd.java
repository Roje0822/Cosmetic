package com.roje.cosmetic.command;

import com.roje.cosmetic.data.CosMeticData;
import com.roje.rojelib.utils.InventoryUtil;
import com.roje.rojelib.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CosMeticcmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("§c버킷에서는 명령어를 사용 할 수 없습니다");
            return true;
        } else {

            Player player = (Player) sender;
            CosMeticData cosmeticData;
            String name;
            Integer CustomModelData;
            Material material;
            Inventory inventory;
            InventoryUtil inventoryUtil;
            ItemUtil itemUtil;

            if (args.length == 0) {
                player.sendMessage("§c사용법: /코스튬 <명령어>");
                return true;
            }
            switch (args[0]) {
                case "help", "도움말" -> {
                    player.sendMessage("§f명령어: 도움말, 목록,  설정,  삭제");
                    return true;
                }
                case "list", "목록" -> {

                    cosmeticData = new CosMeticData();

                    cosmeticData.getCosmeticList().forEach(cosmetic -> player.sendMessage("§f" + cosmetic));
                    return true;
                }
                case "create", "생성" -> {
                    if (!player.isOp()) return true;
                    if (args.length < 3) {
                        player.sendMessage("§c사용법: /코스튬 생성 <이름> <모델데이터>");
                        return true;
                    }

                    material = player.getInventory().getItemInMainHand().getType();
                    name = args[1];
                    CustomModelData = Integer.parseInt(args[2]);
                    cosmeticData = new CosMeticData(name, material, CustomModelData);

                    cosmeticData.createCosmetic();
                    return true;
                }
                case "delete", "삭제" -> {
                    if (!player.isOp()) return true;
                    if (args.length < 2) {
                        player.sendMessage("§c사용법: /코스튬 삭제 <이름>");
                        return true;
                    }

                    name = args[1];
                    cosmeticData = new CosMeticData(name);

                    cosmeticData.deleteCosmetic();
                    return true;
                }
                case "book", "책" -> {
                    if (!player.isOp()) return true;
                    if (args.length < 2) {
                        player.sendMessage("§c사용법: /코스튬 책 <이름>");
                        return true;
                    }

                    name = args[1];
                    cosmeticData = new CosMeticData(name);

                    player.getInventory().addItem(cosmeticData.getBook());
                    return true;
                }
                case "equip", "장착" -> {
                    if (args.length < 2) {
                        player.sendMessage("§c사용법: /코스튬 장착 <이름>");
                        return true;
                    }
                    cosmeticData = new CosMeticData();

                    inventoryUtil = new InventoryUtil(player);
                    inventoryUtil.openInventory("코스튬 장착", 54);

                    List<String> cosmeticList = cosmeticData.getCosmeticList();
                    cosmeticList.forEach(cosmetic -> {
                        player.getOpenInventory().getTopInventory().addItem(cosmeticData.getItem(cosmetic));
                    });
                }
            }
        }

        return false;
    }
}

