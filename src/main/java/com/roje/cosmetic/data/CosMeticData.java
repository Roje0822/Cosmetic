package com.roje.cosmetic.data;

import com.roje.cosmetic.CosMeticMain;
import com.roje.rojelib.data.Config;
import com.roje.rojelib.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CosMeticData {

    private String name;
    private int customModelData;
    private Config config;
    private Material material;
    private ItemUtil itemUtil;

    public CosMeticData(String name, Material material, int CustomModelData) {
        this.name = name;
        this.customModelData = CustomModelData;
        this.material = material;
        this.config = new Config("Cosmetic/" + name, CosMeticMain.getPlugin());
    }


    public CosMeticData(String name) {
        this.name = name;
        this.config = new Config("Cosmetic/" + name, CosMeticMain.getPlugin());
    }


    public CosMeticData() {
        this.config = new Config("Cosmetic/", CosMeticMain.getPlugin());
    }


    public void createCosmetic() {
        config.setInt("cosmetic.custommodeldata", customModelData);
        config.setString("cosmetic.name", name);
        config.setString("cosmetic.material", material.toString());
    }


    public void deleteCosmetic() {
        config.deleteFile();
    }


    public ItemStack getBook() {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        ItemUtil itemUtil = new ItemUtil(item);
        itemUtil.setDisplayName("§f" + name);
        itemUtil.setLore(List.of("§6코스튬 북"));
        return item;
    }


    public ItemStack getItem(String name) {
        config = new Config("Cosmetic/" + name, CosMeticMain.getPlugin());
        ItemStack item = new ItemStack(Material.valueOf(config.getString("cosmetic.material")));
        item.getItemMeta().setDisplayName("asdf");
        return item;
    }


    public int getCustumModelData(String name) {
        config = new Config("Cosmetic/" + name, CosMeticMain.getPlugin());
        return config.getInt("cosmetic.custommodeldata");
    }


    public String getMaterial(String name) {
        config = new Config("Cosmetic/" + name, CosMeticMain.getPlugin());
        return config.getString("cosmetic.material");
    }


    public List<String> getCosmeticList() {
        return config.fileListName();
    }


}
