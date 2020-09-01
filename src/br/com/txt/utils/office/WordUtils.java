package br.com.txt.utils.office;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.txt.utils.io.GerenciadorArquivo;
import br.com.txt.utils.io.LeitorArquivo;

public final class WordUtils {

	public static void formatTextInWord(String pathFolderTxt) throws IOException {

		List<File> arquivos = GerenciadorArquivo.lerArquivosDiretorio(pathFolderTxt);
		String diretorioDocx = pathFolderTxt + "/docx/";
		GerenciadorArquivo.criarDiretorio(diretorioDocx);
		arquivos.forEach(arq -> {

			try {
				// Blank Document
				XWPFDocument document = new XWPFDocument();

				// Write the Document in file system
				FileOutputStream out = new FileOutputStream(
						new File(diretorioDocx + arq.getName().replace(".txt", "") + ".docx"));

				LeitorArquivo la = new LeitorArquivo();
				la.abrirArquivo(arq.getAbsolutePath());

				String linha = la.lerLinha();

				// create paragraph
				XWPFParagraph paragraph = document.createParagraph();
				XWPFRun paragraphRun = paragraph.createRun();
				paragraphRun.setFontSize(14);

				while (linha != null) {
					paragraphRun.setText(linha);
					paragraphRun.addBreak();
					linha = la.lerLinha();
				}

				document.write(out);
				out.close();
				document.close();
				la.fecharArquivo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		System.out.println("Files written successully");
	}

}
