package br.com.txt.utils.mine;

import java.io.IOException;
import java.util.Scanner;

/**
 * Classe util para todas as outras classes
 * 
 * @author tabatista
 *
 */
public class MeuUtil {

	/**
	 * Devolve o user atual do computador Exemplo (sem aspas):
	 * "C:\Users\nomeUsuario"
	 * 
	 * @return o user
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	/**
	 * Encerra todos os processos do nome definido no argumento
	 * 
	 * @param nomeProcesso
	 *            o nome do processo, nao precisa do .exe
	 * @throws IOException
	 */
	public static void matarProcessos(String nomeProcesso) throws IOException {
		Process process = Runtime.getRuntime().exec("taskkill /f /im " + nomeProcesso + ".exe");
		Scanner leitor = new Scanner(process.getInputStream());
		while (leitor.hasNextLine()) {
			System.out.println(leitor.nextLine());
		}

		leitor.close();
	}

	/**
	 * Desliga o computador. Atenção ao uso do System.exit() antes do uso deste
	 * método.
	 * 
	 * @throws IOException
	 */
	public void desligarPcWindows() throws IOException {
		Runtime.getRuntime().exec("shutdown -s");
	}

	public static String converterBooleanSimNao(boolean valor) {

		if (valor)
			return "Sim";

		else
			return "Não";

	}

	public static String converterBooleanAtivo(boolean valor) {

		if (valor)
			return "Ativo";

		else
			return "Inativo";

	}

	public static String converterBooleanDefinido(boolean valor) {

		if (valor)
			return "Definido";

		else
			return "Indefinido";

	}

	/**
	 * Remove tudo o que nao eh numero
	 * 
	 * @param texto
	 *            que contenha os numeros
	 * @return numeros
	 */
	public static int recuperarNumeros(String texto) {

		String num = texto.replaceAll("[^0-9]", "");

		if ("".equals(num) || num.isEmpty() || num == null) {
			return 0;
		}

		return Integer.parseInt(num);
	}
	
	/**
	 * Remove tudo o que nao eh numero
	 * 
	 * @param texto
	 *            que contenha os numeros
	 * @return numeros
	 */
	public static long recuperarNumerosL(String texto) {

		String num = texto.replaceAll("[^0-9]", "");

		if ("".equals(num) || num.isEmpty() || num == null) {
			return 0;
		}

		return Long.parseLong(num);
	}

}
