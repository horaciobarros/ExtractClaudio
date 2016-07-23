package br.com.jway.claudio.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	public String CODIGO_IBGE_CLAUDIO = "3116605"; // claudio

	public static Long castToLong(Object value, Long defaultValue) {
		if (value != null) {
			if (value instanceof Long) {
				return (Long) value;
			} else if (value instanceof Number) {
				return new Long(((Number) value).longValue());
			} else if (value instanceof String) {
				try {
					return value.equals("") ? defaultValue : new Long((String) value);
				} catch (NumberFormatException exn) {
					return defaultValue;
				}
			} else if (value instanceof BigInteger) {
				return ((BigInteger) value).longValue();
			} else if (value instanceof BigDecimal) {
				return ((BigDecimal) value).longValue();
			}
		}
		return defaultValue;
	}

	public static Long castToLong(Object value) {
		return castToLong(value, null);
	}

	public static boolean isBlank(String text) {
		if (text != null && text.length() > 0) {
			for (int i = 0, iSize = text.length(); i < iSize; i++) {
				if (text.charAt(i) != ' ') {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isNotBlank(String nome) {
		return !isBlank(nome);
	}

	public static Integer castToInteger(Object value, Integer defaultValue) {
		if (value != null) {
			if (value instanceof Integer) {
				return (Integer) value;
			} else if (value instanceof Number) {
				return new Integer(((Number) value).intValue());
			} else if (value instanceof BigInteger) {
				return ((BigInteger) value).intValue();
			} else if (value instanceof BigDecimal) {
				return ((BigDecimal) value).intValue();
			} else if (value instanceof String) {
				try {
					return value.equals("") ? defaultValue : new Integer((String) value);
				} catch (NumberFormatException exn) {
					return defaultValue;
				}
			}
		}
		return defaultValue;
	}

	public static Integer castToInteger(Object value) {
		return castToInteger(value, null);
	}

	public String getNomeMes(String mes) {
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };

		return meses[Integer.valueOf(mes) - 1];
	}

	public Date getStringToDateHoursMinutesSeconds(String data) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = (Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Date getStringToDateHoursMinutes(String data) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = (Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Date getStringToDateHoursMinutesWithBars(String data) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try {
			Date date = (Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Date getStringToDate(String data) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = (Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Date getStringToDate(String data, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		try {
			Date date = (Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getOptantePeloSimplesNacional(String optantePeloSimplesNacional) {
		return optantePeloSimplesNacional.substring(0, 1);
	}

	public String getTipoPessoa(String inscricao) {
		return (inscricao.trim().length() == 11 ? "F" : "J");
	}

	public BigDecimal getSumOfBigDecimal(List<BigDecimal> lista) {

		Double valorAux = Double.valueOf(0);

		for (BigDecimal valor : lista) {
			if (valor != null) { 
				valorAux += valor.doubleValue();
			}
		}
		return BigDecimal.valueOf(valorAux);

	}

	public BigDecimal getSubtract(BigDecimal valor1, BigDecimal valor2) {

		return BigDecimal.valueOf(valor1.doubleValue() - valor2.doubleValue());
	}

	public Date getFirstDayOfMonth(String ano, String mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, (Integer.valueOf(ano)));
		calendar.set(Calendar.MONTH, (Integer.valueOf(mes) - 1));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public Date getLastDayOfMonth(String ano, String mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, (Integer.valueOf(ano)));
		calendar.set(Calendar.MONTH, (Integer.valueOf(mes) - 1));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}


	public String trataSeTiverVazio(String value) {
		if (value == null || value.trim().isEmpty()) {
			return "0";
		}

		return value;
	}

	public static String getDateHourMinutes(Date data) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		return formatter.format(data);
	}

	public Date getDecimoDiaMesPosterior(Date dataFim) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataFim);
		calendar.set(Calendar.MONTH, calendar.get(calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 10);

		return calendar.getTime();
	}

	public static void main(String args[]) {
		new Util().getDecimoDiaMesPosterior(new Date());
	}

	public String getLimpaTelefone(String telefone) {
		if (telefone == null) {
			return telefone;
		}
		telefone = telefone.trim();
		telefone = telefone.replaceAll(" ", "");
		telefone = telefone.replaceAll("-", "");
		telefone = telefone.replaceAll("\\(", "");
		telefone = telefone.replaceAll("\\)", "");
		if (telefone.length()>=11) {
			telefone = telefone.substring(0,9);
		}
		return telefone.trim();
	}

	public String trataEmail(String email) {
		if (email != null) {
			email = email.trim();
			if (email != null && email.trim().isEmpty()) {
				email = null;
				return email;
			}
			if (!email.contains("@")) {
				email = null;
			}
			if (email != null && email.contains(" ")) {
				if (email.contains("@")) {
					int posicaoVazia = email.indexOf(" ");
					if (posicaoVazia > 0) {
						email = email.substring(0, posicaoVazia);
					} else {
						email = email.trim();
					}
					if (email.contains(";br")) {
						email = email.replace(";br", ".br");
					} else if (email.contains(";")) {
						 int posicaoPv = email.indexOf(";");
						 email = email.substring(0, posicaoPv);
					}
				} else {
					email = null;
				}
				
			}
		}
		return email;
	}

	public String completarZerosEsquerda(String conteudo, int qtdeFinalDigitosDaString) {
		while (conteudo.length() < qtdeFinalDigitosDaString) {
			conteudo = "0" + conteudo.trim();
		}
		return conteudo;
	}
	
	public boolean isEmptyOrNull(String content) {
		
		if (content == null) {
			return true;
		} 
		
		if (content.trim().isEmpty()) {
			return true;
		}
		
		return false;
	}

	public String trataCep(String cep) {
		if (!isEmptyOrNull(cep)) {
			cep = cep.replaceAll("-", "");
			if (cep.length() < 8) {
				cep = completarZerosDireita(cep, 8);
				return cep;
			}
			return cep;
		}
		return null;
	}

	public String completarZerosDireita(String conteudo, int qtdeFinalDigitosDaString) {
		while (conteudo.length() < qtdeFinalDigitosDaString) {
			conteudo = conteudo.trim() + "0";
		}
		return conteudo;
	}
	
	public Date getStringToDateHoursMinutesAdd2hours(String data) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = (Date) formatter.parse(data);
			GregorianCalendar gc = new GregorianCalendar();
		    gc.setTime(date);
		    gc.add(Calendar.HOUR,2);
		    return gc.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	public static boolean validarEmail(String email){
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	 }
	
	public BigDecimal getStringToBigDecimal(String valor) {
		valor = valor.replace(",", ".");
		Double value = Double.valueOf(valor);
		return BigDecimal.valueOf(value);
	}
	
	public String getSituacaoTributaria(String situacaoTributaria) {
		try {
			if (situacaoTributaria.substring(0, 1).equals("R")) {
				return "R";
			} else {
				return "N";
			}
		} catch (Exception e) {
			return "N";

		}
	}
	
	public Date getStringXmlToDateHoursMinutes(String data) {
		if (data!=null){
			int dia = 0, mes = 0, ano = 0, horas = 0, minutos = 0;
			dia = Integer.parseInt(data.substring(0,2));
			mes = Integer.parseInt(data.substring(3,5));
			ano = Integer.parseInt(data.substring(6,10));
			horas = Integer.parseInt(data.substring(11,13));
			minutos = Integer.parseInt(data.substring(14));
			Date date = new GregorianCalendar(ano, mes, dia, horas, minutos).getTime();
			
			return date;
		}
		return null;
	}

	public String getCpfCnpj(String cpfCnpj) {
		cpfCnpj = cpfCnpj.replaceAll(".","");
		cpfCnpj = cpfCnpj.replaceAll("-","");
		cpfCnpj = cpfCnpj.replaceAll("/","");
		return cpfCnpj;
	}

	public static boolean validarCpf(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;
		// considera-se erro cpf's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")
				|| (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do cpf em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere
											// numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}

	}

	public static boolean validarCnpj(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;
		int soma = 0, aux, dig;
		String cnpj_calc = cnpj.substring(0, 12);

		if (cnpj.length() != 14)
			return false;

		char[] chr_cnpj = cnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return cnpj.equals(cnpj_calc);
	}
	
	public List<String> splitRegistro(String linha){
		//return Arrays.asList(linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
		StringBuilder builder = new StringBuilder(linha.replace("\";", ""));
		boolean inQuotes = false;
		for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
		    char currentChar = builder.charAt(currentIndex);
		    if (currentChar == '\"') inQuotes = !inQuotes;
		    if (currentChar == ',' && inQuotes) {
		        builder.setCharAt(currentIndex, ';');
		    }
		}
		List<String> result = Arrays.asList(builder.toString().split(","));

		return result;
	}
	
	public double corrigeDouble(Object obj){
		double valor = 0;
		try{
			if (obj!=null){
				valor = Double.valueOf((double) obj);
			}
		}
		catch(Exception e){}
		try{
			if (obj!=null){
				valor = (double) obj;
			}
		}
		catch(Exception e){}
		try{
			if (obj!=null){
				valor = Double.valueOf((String) obj);
			}
		}
		catch(Exception e){}
		try{
			if (obj!=null){
				valor = Double.valueOf((Integer) obj);
			}
		}
		catch(Exception e){}
		
		return valor;
	}

}
