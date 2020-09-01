package br.com.txt.utils.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.txt.utils.mine.MeuUtil;

/**
 * Classe responsavel por ler o arquivo de entrada
 * 
 * @author tabatista
 * @version 1.2
 */

public class LeitorArquivo {

	/**
	 * O user local da maquina em questao
	 */
	private String userHome;

	/** stream (InputStream) */
	private InputStreamReader inputStream;

	/** manipulador de arquivo */
	private BufferedReader arquivoEntrada;

	public LeitorArquivo() {
		userHome = MeuUtil.getUserHome();
	}

	/**
	 * Abre um arquivo para leitura sequencial.
	 * 
	 * @param caminhoArquivo
	 *            O caminho completo(caminho + nome + extensao) ateh o arquivo.
	 * @throws IOException
	 */
	public void abrirArquivo(String caminhoArquivo) throws IOException {

		// serah necessario salvar um arquivo csv como csv novamente se desejar
		// utilizar o formato ISO-8859-1 e alterar para o return comentado do
		// metodo lerLinha

		 inputStream = new InputStreamReader(new
		 FileInputStream(caminhoArquivo), "ISO-8859-1");
		//inputStream = new InputStreamReader(new FileInputStream(caminhoArquivo), "UTF-8");
		arquivoEntrada = new BufferedReader(inputStream);
	}

	/**
	 * Abre um arquivo para leitura sequencial.
	 * 
	 * @param caminhoArquivo
	 * 
	 * @throws IOException
	 */
	public void abrirArquivo(InputStream arquivo) throws IOException {

		inputStream = new InputStreamReader(arquivo);
		arquivoEntrada = new BufferedReader(inputStream);
	}

	/**
	 * Le uma linha do arquivo.
	 * 
	 * @return A linha lida.
	 * @throws IOException
	 */
	public String lerLinha() throws IOException {

		// try {
		// return new String(arquivoEntrada.readLine().getBytes(),
		// "ISO-8859-1");
		// } catch (NullPointerException e) {
		// return null;
		// }

		return arquivoEntrada.readLine();

	}

	/**
	 * Fecha o arquivo.
	 * 
	 * @throws IOException
	 */
	public void fecharArquivo() throws IOException {

		arquivoEntrada.close();
		inputStream.close();

	}

	/**
	 * Informa quantas linhas tem em determinado arquivo
	 * 
	 * @param pularPrimeiraLinha
	 *            se ele deve contar a primeira linha como parte da quantidade
	 * @param pastaUser
	 *            a pasta do usuario do computador
	 * @param nomeArquivo
	 *            o nome do arquivo, deve incluir a extensao no nome
	 * @return a quantidade de linhas do arquivo
	 * @throws IOException
	 */
	public int quantidadeLinhas(boolean pularPrimeiraLinha, String pastaUser, String nomeArquivo) throws IOException {

		String caminhoArquivo = userHome + "/" + pastaUser + "/" + nomeArquivo;

		try {
			abrirArquivo(caminhoArquivo);

		} catch (IOException e) {
			throw new IOException("Não possível localizar arquivo: " + caminhoArquivo);
		}

		lerLinha();

		int quantidade = 0;

		while (lerLinha() != null) {
			quantidade++;
		}

		fecharArquivo();

		if (!pularPrimeiraLinha && quantidade > 0) {
			quantidade++;
		}

		return quantidade;
	}

	@Override
	public String toString() {
		return "LeitorArquivo [userHome=" + userHome + ", inputStream=" + inputStream + ", arquivoEntrada="
				+ arquivoEntrada + "]";
	}
}
