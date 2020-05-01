package com.app.idnbin.MarketAnalysis;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "rss",strict = false)
public class Newsfeed {
    @Path("channel")
    @ElementList(name = "item", entry = "item", inline = true, required = false)
    ArrayList<ItemCount> itemCounts;


    public ArrayList<ItemCount> getItemCounts() {
        return itemCounts;
    }
}
