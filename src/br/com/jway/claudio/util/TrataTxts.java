package br.com.jway.claudio.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TrataTxts {

	String pastaOrigem = "C:\\Temp\\claudio\\";
	String pastaDestino = "C:\\Temp\\claudio\\tratados\\";
	
	
	
	public static void main(String args[]){
		new TrataTxts().processa();
	}
	
	public void processa(){
		File dirOrigem = new File(pastaOrigem);
		File dirDestino = new File(pastaDestino);
		dirDestino.mkdirs();
		File[] listaArquivos = dirOrigem.listFiles();
		for (File arqOrigem : listaArquivos){
			switch (arqOrigem.getName()) {
				case "cnae_servicos_contribuintes.csv":
					lerArquivo(arqOrigem, 13);
					break;
				case "contribuintes.csv":
					lerArquivo(arqOrigem, 26);
					break;
				case "escrituracoes.csv":
					lerArquivo(arqOrigem, 21);
					break;
				case "guias.csv":
					lerArquivo(arqOrigem, 18);
					break;
				case "notas_fiscais.csv":
					lerArquivo(arqOrigem, 50);
					break;
				case "servicos.csv":
					lerArquivo(arqOrigem, 6);
					break;
				case "servicos_escrituracoes.csv":
					lerArquivo(arqOrigem, 6);
					break;
				case "servicos_notas_fiscais.csv":
					lerArquivo(arqOrigem, 6);
					break;
				case "solicitacoes.csv":
					lerArquivo(arqOrigem, 10);
					break;
					
				default:
					break;
			}
		}
	}
	
	public List<String> lerArquivo(File arqOrigem, int qtdeCampos) {
		try {
			BufferedReader br;
			int contaLinhas = 0;
			OutputStreamWriter bo = new OutputStreamWriter(new FileOutputStream(pastaDestino + arqOrigem.getName()), "UTF-8");
			List<String> dadosList = new ArrayList<String>();
			Util util = new Util();
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(arqOrigem), "UTF-8"));
				br.readLine(); // cabe�alho
				while (br.ready()) {
					
					StringBuilder linhaDefinitiva = new StringBuilder();
					List<String> campos = new ArrayList<String>();

					while (campos != null && campos.size() < qtdeCampos) {
						contaLinhas++;

						String linha = br.readLine();
						if (linha == null || linha.trim().isEmpty()){
							continue;
						}
						if (arqOrigem.getName().equals("contribuintes.csv")){
							if (linha.startsWith("\"")){
								linha = linha.substring(1);
							}
						}
						if (linha.endsWith(";;")){
							linha = linha.substring(0,  linha.length()-2);
						}
						if (linha.endsWith(";")){
							linha = linha.substring(0,  linha.length()-1);
						}
						if (linha.endsWith(",")){
							linha = linha +" ";
						}
						
						linha = linha.replace("\"\"", "");
						linha = linha.replace("\";", "");
						linhaDefinitiva = new StringBuilder(linhaDefinitiva.toString() + linha);
						campos = util.splitRegistro(linhaDefinitiva.toString());
					}

					String linhaAux = linhaDefinitiva.toString();
					//linhaAux = linhaAux.replace("\"", "");
					dadosList.add(linhaAux);
					bo.write(linhaAux + "\n");

				}
				br.close();
				bo.close();
				return dadosList;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}




