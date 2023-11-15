package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].category == ItemCategory.LEGENDARY) {
                continue;
            }
            if (items[i].category == ItemCategory.STANDARD
                || items[i].category == ItemCategory.CONJURED) {
                decrementQuality(items[i]);
            } else {
                incrementQuality(items[i]);
                if (items[i].category == ItemCategory.BACKSTAGE_PASS) {
                    if (items[i].sellIn < 11) {
                        incrementQuality(items[i]);
                    }

                    if (items[i].sellIn < 6) {
                        incrementQuality(items[i]);
                    }
                }
            }

            items[i].sellIn = items[i].sellIn - 1;

            if (items[i].sellIn < 0) {
                pastTheSellInDate(items[i]);
            }
        }
    }

    void pastTheSellInDate(Item item) {
        if (item.category == ItemCategory.STANDARD
           || item.category == ItemCategory.CONJURED) {
                decrementQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality = 0;
        } else {
            incrementQuality(item);
        }
    }

    private Item decrementQuality(Item item) {
        int decrementBy = 1;
        if (item.quality > 0) {
            if (item.category == ItemCategory.CONJURED) {
                decrementBy = 2;
            }
            item.quality -= decrementBy;
        }
        return item;
    }
    private Item incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        return item;
    }

}