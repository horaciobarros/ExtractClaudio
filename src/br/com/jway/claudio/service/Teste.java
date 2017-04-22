package br.com.jway.claudio.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Teste {

	public List<String> lerArquivosClaudio(String arquivoIn) {
		BufferedReader br;
		List<String> dadosList = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("c:/tmp/Integra20170116204037.bth")));
			while (br.ready()) {
				String linha = br.readLine();
				System.out.println(linha);
			}
			br.close();
			return dadosList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static void main(String args[]){
		new Teste().lerArquivosClaudio("");
	}
}
