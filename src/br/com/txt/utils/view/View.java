package br.com.txt.utils.view;

import java.io.IOException;

import br.com.txt.utils.mine.MeuUtil;
import br.com.txt.utils.office.WordUtils;

public class View {

	public static void main(String[] args) {

		try {
			WordUtils.formatTextInWord(MeuUtil.getUserHome() + "/Desktop/poetry");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
