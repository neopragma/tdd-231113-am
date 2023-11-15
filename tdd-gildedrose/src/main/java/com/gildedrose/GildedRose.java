package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item.category == ItemCategory.LEGENDARY) {
                continue;
            }
            if (item.category == ItemCategory.STANDARD
                || item.category == ItemCategory.CONJURED) {
                decrementQuality(item);
            } else {
                incrementQuality(item);
                if (item.category == ItemCategory.BACKSTAGE_PASS) {
                    if (item.sellIn < 11) {
                        incrementQuality(item);
                    }

                    if (item.sellIn < 6) {
                        incrementQuality(item);
                    }
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                pastTheSellInDate(item);
            }
            items[i] = item;
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