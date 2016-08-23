package br.com.jway.claudio.util;


public class MainTest {
	
	public static void main(String args[]) {
		Util util = new Util();
		String cpf = util.getCpfCnpj("04.395.677/0001-27");
		String cpf2 = "04.395.677/0001-27".replaceAll("\\.", "");
		System.out.println("cpf:" + cpf2);
	}
	

}
