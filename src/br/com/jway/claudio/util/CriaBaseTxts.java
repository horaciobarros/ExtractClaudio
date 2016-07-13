package br.com.jway.claudio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author jway
 *
 */
public class CriaBaseTxts {

	public static void main(String args[]) {
		List<String> files = Arrays.asList("dados_guia", "dados_livro_prestador", "dados_livro_tomador",
				"dados_cadastro_atividade", "dados_cadastro");

		File file, fileOut;
		FileReader fr;
		FileWriter fw;
		BufferedWriter bw;
		BufferedReader br;

		fileOut = new File("c:/TEMP/lagoa/" + "cria_base_txts_lagoa.sql");
		try {
			fw = new FileWriter(fileOut);
			bw = new BufferedWriter(fw);

			for (String table : files) {
				file = new File("c:/TEMP/lagoa/" + table + ".txt");
				try {
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					try {
						String headerLine = br.readLine();
						String[] campos = headerLine.split("\\|");

						bw.write("DROP TABLE IF EXISTS `" + table + "`;\n");
						bw.write("CREATE TABLE `" + table + "` (\n");
						for (int i = 0; i < campos.length; i++) {
							bw.write("  " + campos[i] + " varchar(100),\n");
						}
						bw.write(") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;\n\n");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			bw.close();
			fw.close();
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
