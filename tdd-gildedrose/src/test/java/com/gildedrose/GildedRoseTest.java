package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private GildedRose app;
    private Item[] items;

    @Test
    void non_legendary_items_decrement_sell_in_date() {
        setupAndGo(new Item[] { new Item("Aged Brie", 5, 50) });
        assertEquals(4, app.items[0].sellIn);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForQualityChecks")
    void it_adjusts_quality_correctly(Item[] items, int expectedQuality) {
        setupAndGo(items);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    private static Stream<Arguments> provideValuesForQualityChecks() {
        return Stream.of(
                Arguments.of(new Item[] {new Item("Sulfuras, Hand of Ragnaros", 0, 80,
                        ItemCategory.LEGENDARY)}, 80),
                Arguments.of(new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", -1, 50, ItemCategory.INCREASING_VALUE)}, 0),
                Arguments.of(new Item[] {new Item("Aged Brie", -1, 20, ItemCategory.INCREASING_VALUE)}, 22),
                Arguments.of(new Item[] {new Item("foo", -1, 20, ItemCategory.STANDARD)}, 18),
                Arguments.of(new Item[] {new Item("foo", -1, 0, ItemCategory.STANDARD)}, 0),
                Arguments.of(new Item[] {new Item("conjured item", -1, 2, ItemCategory.CONJURED)}, 0)
        );
    }
    private void setupAndGo(Item[] items) {
        app = new GildedRose(items);
        app.updateQuality();
    }

}
