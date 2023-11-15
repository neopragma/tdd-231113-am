package com.gildedrose;

public class Item {
    public ItemCategory category;
    public String name;
    public int sellIn;
    public int quality;
    public Item(String name, int sellIn, int quality) {
        this(name, sellIn, quality, ItemCategory.STANDARD);
    }
    public Item(String name, int sellIn, int quality, ItemCategory category) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.category = category;
    }
   @Override
   public String toString() {
        return this.name + ", "
                + this.sellIn + ", "
                + this.quality + ", "
                + this.category;
    }
}
