package br.com.txt.utils.io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsavel por gerenciar os arquivos de uma maneira geral
 * 
 * @author Thais
 * @version 1.1
 */
public class GerenciadorArquivo {

	/**
	 * Exclui arquivos que comecem com a variavel <b>comeco</b> e terminem com
	 * <b>fim</b>.
	 * 
	 * Muita <b>atencao</b> ao utilizar este metodo, ele pode deletar arquivos
	 * indesejados e estes nao vao para a lixeira - sao excluidos diretamente
	 * 
	 * @param comeco
	 * @param fim    geralmente a extensao do arquivo. Necessário colocar ponto
	 */
	public static void excluirArquivosComecoFim(String caminho, String comeco, String fim) {

		// diretório que será listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		File[] files = baseFolder.listFiles();

		for (File arq : files) {

			if (arq.getName().startsWith(comeco) && arq.getName().endsWith(fim)) {
				arq.delete();
				System.out.println("Arquivo " + arq.getName() + " excluído!");
			}
		}

		System.out.println("\nTodos os arquivos [" + comeco + "] [" + fim + "] de " + caminho + " foram excluídos!");

	}

	/**
	 * Le o nome dos arquivos de um diretório
	 */
	public static List<String> lerNomesArquivosDiretorio(String caminho) {
		// lista com nomes
		List<String> nomes = new ArrayList<>();

		// diretório que será listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		File[] arquivos = baseFolder.listFiles();

		for (File arq : arquivos) {

			nomes.add(arq.getAbsolutePath());
			// nomes.add(arq.getName());
		}

		return nomes;

	}

	public static List<File> lerArquivosDiretorio(String caminho) {
		// diretorio que serah listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		return Arrays.asList(baseFolder.listFiles());
	}

	/**
	 * Move arquivo
	 * 
	 * @return
	 */
	public static void moverArquivo(String arquivo, String diretorio) {

		// arquivo a ser movido
		File arq = new File(arquivo);

		// diretorio de destino
		File dir = new File(diretorio);

		// move o arquivo para o novo diretorio
		boolean ok = arq.renameTo(new File(dir, arq.getName()));

		if (ok) {
			System.out.println("Arquivo foi movido com sucesso");
		} else {
			System.out.println("Nao foi possivel mover o arquivo");
		}
	}

	/**
	 * Cria um diretorio se nao existir
	 */
	public static void criarDiretorio(String diretorio) {

		File arq = new File(diretorio);

		if (!arq.exists()) {
			arq.mkdirs();
		}
	}

	public static boolean validarDiretorio(String diretorio) {
		File arq = new File(diretorio);

		if ((arq.isDirectory() || arq.isFile()) && (arq.canWrite() || arq.canRead())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Exclui arquivos *sem extensao e *sem conteudo
	 */
	public static void excluirArquivosIndefinidos(String caminho) {
		// diretório que será listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		File[] files = baseFolder.listFiles();

		for (File arq : files) {

			int i = 0;

			try {
				// uma pasta eh um arquivo sem extensao, mas pode ter conteudo
				// dentro, se houver, nao serah excluida
				i = arq.listFiles().length;

			} catch (NullPointerException e) {

			}

			// -1 = nao localiza a extensao do arquivo, do contrário haverá um ponto em
			// qualquer parte de seu nome, e ele nao será excluido
			if (arq.getName().lastIndexOf(".") == -1 && i == 0) {
				arq.delete();
				System.out.println("Arquivo " + arq.getName() + " excluído!");
			}
		}

		System.out.println("\nTodos os arquivos *sem extensão (e sem ponto) e sem conteúdo foram excluídos!");

	}

	/**
	 * Renomeia o arquivo
	 * 
	 * @param nomeArquivo o nome atual do arquivo
	 * @param novoNome    o novo nome a ser definido
	 * @return se foi renomeado com sucesso
	 */
	public boolean renomearArquivo(String nomeArquivo, String novoNome) {

		return new File(nomeArquivo).renameTo(new File(novoNome));
	}

	/**
	 * Apaga um arquivo com contagem. OBS: ha espaco entre o nome e o contador
	 * 
	 * @param caminho     o caminho onde esta arquivo
	 * @param nomeArquivo o nome do arquivo
	 * @param extensao    a extasao do arquivo. Nao precisa de ponto
	 * @return se apagou com sucesso
	 */
	public boolean excluirArquivo(String caminho, String nomeArquivo, String extensao) {
		return new File(caminho + nomeArquivo + "." + extensao).delete();
	}

	/**
	 * Exclui um arquivo
	 * 
	 * @param caminhoArq caminho completo do arquivo + arquivo + extensao
	 * @return se o arquivo foi excluido
	 */
	public static boolean excluirArquivo(String caminhoArq) {
		return new File(caminhoArq).delete();
	}

	/**
	 * Metodo responsavel por abrir em tela um arquivo
	 * 
	 * @param caminho     o caminho onde esta arquivo
	 * @param nomeArquivo o nome completo do arquivo, incluindo a extensao
	 * @throws IOException
	 */
	public static void abrirArquivo(String caminho, String nomeArquivoExtensao) throws IOException {

		File arquivo = new File(caminho + nomeArquivoExtensao);

		System.out.println("Caminho Excel --> " + arquivo);

		Desktop desktop = Desktop.getDesktop();

		// Abre o Arquivo
		desktop.open(arquivo);
	}

}
