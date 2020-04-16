package com.github.miyasum.sandbox.jsoup;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupSample {

  public static void main(String[] args) {
    String html = "" +
      "<a href=\"https://example.com/\">[com]リンクをクリックしてください。{{name}}</a>" +
      "<br>" +
      "<a href=\"https://example.net/\">[net]リンクをクリックしてください。{{name}}</a>" +
      "<br>" +
      "<a href=\"https://example.org/\">[org]リンクをクリックしてください。{{name2}}</a>" +
      "<br>" +
      "<a href=\"mailto:test@example.com\">[mailto]リンクをクリックしてください。{{name2}}</a>";

    // 変数置換
    Map<String, String> replacments = Map.of("name", "ほげほげ", "name2", "ふがふが");
    html = replacments.entrySet().stream()
      .reduce(html, (h, e) -> h.replace("{{" + e.getKey() + "}}", e.getValue()), (h1, h2) -> h1);

    System.out.println(html);

    // HTML解析
    Document doc = Jsoup.parse(html);

    // URL 置換
    doc.select("a[href]").stream()
      .filter(e -> e.attr("href").startsWith("http://") || e.attr("href").startsWith("https://"))
      .forEach(e -> {
        String before = e.attr("href");
        e.attr("href", "https://example.com/redirect?url=" + before);
      });
    // 開封イベント埋め込み
    Element open = doc.createElement("img").attr("src", "https://example.com/open");
    doc.body().appendChild(open);
    // できあがり
    Elements result = doc.body().children();

    System.out.println(result);
  }
}
