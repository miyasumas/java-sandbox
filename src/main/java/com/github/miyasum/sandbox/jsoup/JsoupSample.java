package com.github.miyasum.sandbox.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupSample {

    public static void main(String[] args) {
        String html = "" +
                "<a href=\"https://example.com/\">[com]リンクをクリックしてください。</a>" +
                "<br>" +
                "<a href=\"https://example.net/\">[net]リンクをクリックしてください。</a>" +
                "<br>" +
                "<a href=\"https://example.org/\">[org]リンクをクリックしてください。</a>";
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]::text");
        System.out.println(links);
    }
}
