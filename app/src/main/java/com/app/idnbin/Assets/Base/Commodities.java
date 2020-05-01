package com.app.idnbin.Assets.Base;


import com.app.idnbin.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Commodities {
    private List<String> time1 = Collections.singletonList("3:30-5:30 5:30-2:30");
    private List<String> time2 = Collections.singletonList("5:30-2:30");
    private List<String> multiplier1 = Collections.singletonList("x50x100");
    private List<String> multiplier2 = Collections.singletonList("x100");
    private List<String> multiplier3 = Collections.singletonList("x50x100x150");

    private SymbolsData crude_oil_wti = new SymbolsData("Crude Oil WTI", time1, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fcrude_oil_wti.png?alt=media", "West Texas Intermediate (WTI), also known as Texas light sweet, is a grade of crude oil used as a benchmark in oil pricing. This grade is described as light because of its relatively low density, and sweet because of its low sulfur content. It is the underlying commodity of New York Mercantile Exchange's oil futures contracts.West Texas intermediate (WTI), also known as Texas light sweet, is a grade of crude oil used as a benchmark in oil pricing. This grade is described as light crude oil because of its relatively low density, and sweet because of its low sulfur content.");
    private SymbolsData crude_oil_brent = new SymbolsData("Crude Oil Brent", time2, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fcrude_oil_brent.png?alt=media", "Brent Crude is a major trading classification of sweet light crude oil that serves as a major benchmark price for purchases of oil worldwide. This grade is described as light because of its relatively low density, and sweet because of its low sulphur content.");
    private SymbolsData silver = new SymbolsData("Silver", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fsilver.png?alt=media", "Silver (XAG) represents to the Silver spot price. Silver (XAG) is a precious metal used mostly in jewelry, coins and other decorative objects while also used in other applications such as industrial applications, technology and investments. Silver measures wealth and is valued as money. Silver is mined through underground methods and open pits and refined while the precious metal is invested into actual bullion bars.");
    private SymbolsData platinum = new SymbolsData("Platinum", time1, multiplier2, "", "Platinum is a chemical element with the symbol Pt and atomic number 78. It is a dense, malleable, ductile, highly unreactive, precious, silverish-white transition metal. Its name is derived from the Spanish term platino, meaning \"little silver\".");

    private SymbolsData gold = new SymbolsData("Gold", time1, multiplier3, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fgold.png?alt=media","Gold (XAU) refers to the gold spot price. Gold is the world’s most tradable precious metal, which has a great history that dates to ages before Christ as it was mostly used as gold coins for trade purposes. Nowadays, Gold is used vastly in the jewelry market and in trading purposes, it is regarded as a safe haven commodity. Gold was mostly extracted during post-war era and is traded all over the world, typically bought for a premium over spot and sold at a discount to spot in physical exchanges.");

    private ArrayList<SymbolsData> commoditieslist = new ArrayList<>(Arrays.asList(crude_oil_wti, crude_oil_brent, silver, platinum, gold));

    public ArrayList<SymbolsData> getCommoditieslist() {
        return commoditieslist;
    }
}
