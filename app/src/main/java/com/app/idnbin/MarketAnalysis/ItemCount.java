package com.app.idnbin.MarketAnalysis;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict = false)
public class ItemCount {

    @Element(name = "title", required = false)
    public String title;

    @Element(name = "description", required = false)
    public String description;


    @Element(name = "link", required = false)
    public String link;

    @Element(name = "pubDate", required = false)
    public String pubDate;

    @Element(name = "enclosure", required = false)
    public Enclosure enclosure;
}

@Root(name = "enclosure", strict = false)
class Enclosure {

    @Attribute(name = "url")
    public String url;

    @Attribute(name = "length")
    public String length;

    @Attribute(name = "type")
    public String type;
}


