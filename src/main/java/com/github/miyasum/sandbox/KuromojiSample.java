package com.github.miyasum.sandbox;

import java.util.List;
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.atilika.kuromoji.Tokenizer.Mode;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

/**
 * kuromoji で読み仮名を作る
 * @author MIYASAKA Yasumasa
 * @since 2014/03/06
 */
public class KuromojiSample {

	public static void main(String[] args) {
		System.out.println(createRuby("東京都"));
		System.out.println(createRuby("街区"));
		System.out.println(createRuby("１００"));
		System.out.println(createRuby("２"));
	}

	private static Function<Character, String> KATAKANA2HIRAGANA = new Function<Character, String>() {
		@Override
		public String apply(Character c) {
			if (c >= 'ァ' && c <= 'ン') {
				return "" + (char) (c - 'ァ' + 'ぁ');
			} else if (c == 'ヵ') {
				return "か";
			} else if (c == 'ヶ') {
				return "け";
			} else if (c == 'ヴ') {
				return "う゛";
			}
			return "";
		}
	};

	private static String createRuby(String word) {
		Tokenizer tokenizer = Tokenizer.builder().mode(Mode.NORMAL).build();
		List<Token> tokens = tokenizer.tokenize(word);
		return Joiner.on("").join(Lists.transform(tokens, new Function<Token, String>() {
			@Override
			public String apply(Token input) {
				return Joiner.on("").join(
					Lists.transform(Chars.asList(input.getReading().toCharArray()),
						KATAKANA2HIRAGANA));
			}
		}));
	}
}
