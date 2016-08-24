package br.com.jway.claudio.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MainTest {
	
	public static void main(String args[]) {
		new MainTest().geraRelatorioGuiasXNotas();
	}
	
	public List<String> geraRelatorioGuiasXNotas() {
		try {
			BufferedReader br;

			OutputStreamWriter bo = new OutputStreamWriter(new FileOutputStream("c://temp//claudio//guias_notas_fiscais_err.txt"), "UTF-8");
			List<String> dadosList = new ArrayList<String>();
			String linha = "";
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream("c://temp//claudio//zzz_guias_notas_fiscais_err.txt"), "UTF-8"));
				br.readLine(); // cabeï¿½alho
				while (br.ready()) {					
					linha = br.readLine();
					if (linha == null || linha.trim().isEmpty()|| !linha.startsWith("erro")){
						continue;
					}
					String info = linha.substring(linha.indexOf("\""));
					info = info.substring(0,info.indexOf(" - con")-4);
					//String inscricaoPrestador = linha.substring(linha.indexOf("inscricaoPrestador"));
					//inscricaoPrestador = inscricaoPrestador.substring(0,inscricaoPrestador.indexOf(","));
					//String dataVencimento = linha.substring(linha.indexOf("dataVencimento"));
					//dataVencimento = dataVencimento.substring(0,dataVencimento.indexOf(","));
					//System.out.println(info +" - "+inscricaoPrestador+" - "+dataVencimento);
					bo.write(info+"\n");
				}
				br.close();
				bo.close();
				return dadosList;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(linha);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
