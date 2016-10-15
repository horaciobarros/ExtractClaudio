package br.com.jway.claudio.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.Prestadores;

public class ThreadAjustesClaudio implements Runnable{
	
	private String linha;
	private Util util;
	private NotasFiscaisOrigem nfOrigem;
	private int contador;
	private int limite;
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private NotasFiscais nf;
	private Prestadores pr;
	
	public ThreadAjustesClaudio(String linha, Util util, NotasFiscaisOrigem nfOrigem, int contador, int limite, NotasFiscais nf, Prestadores pr) {
		this.contador = contador;
		this.limite = limite;
		this.linha = linha;
		this.nf = nf;
		this.pr = pr;
		this.nfOrigem = nfOrigem;
		this.pr = pr;
		this.util = util;
	}
	
	@Override
	public void run() {
		try {
			List<String> arrayAux = util.splitRegistro(linha);
			
			try {

				nfOrigem = new NotasFiscaisOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2),
						arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7),
						arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12),
						arrayAux.get(13), arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17),
						arrayAux.get(18), arrayAux.get(19), arrayAux.get(20), arrayAux.get(21), arrayAux.get(22),
						arrayAux.get(23), arrayAux.get(24), arrayAux.get(25), arrayAux.get(26), arrayAux.get(27),
						arrayAux.get(28), arrayAux.get(29), arrayAux.get(30), arrayAux.get(31), arrayAux.get(32),
						arrayAux.get(33), arrayAux.get(34), arrayAux.get(35), arrayAux.get(36), arrayAux.get(37),
						arrayAux.get(38), arrayAux.get(39), arrayAux.get(40), arrayAux.get(41), arrayAux.get(42),
						arrayAux.get(43), arrayAux.get(44), arrayAux.get(45), arrayAux.get(46), arrayAux.get(47),
						arrayAux.get(48), arrayAux.get(49));

				contador++;
				limite++;

				
				if (nfOrigem.getNotaFiscalAvulsa().equalsIgnoreCase("t")) { // n√£o
					return;
				}

				nf = notasFiscaisDao.findByIdOrigem(Long.parseLong(nfOrigem.getId()));
				
				
				if (!nf.getInscricaoPrestador().equals(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()))) {
					System.out.println("Prestador origem n„o bate com prestador da nota:" + pr.getInscricaoPrestador() + "-" + nfOrigem.getId());
					return;
				}

				nf.setValorPisPasep(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getPis())));
				
				if (nf.getValorPisPasep().doubleValue() == 0) {
					return;
				}

				List<BigDecimal> lista = Arrays.asList(nf.getValorCofins(), nf.getValorCsll(), nf.getValorInss(),
						nf.getValorIr(), nf.getValorOutrasRetencoes(), nf.getValorPisPasep());
				BigDecimal descontos = util.getSumOfBigDecimal(lista);

				nf.setValorLiquido(util.getSubtract(
						BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())),
						descontos));

				nf = notasFiscaisDao.update(nf);

			} catch (Exception e2) {
				System.out.println("Nota n„o gravada:" + linha);
				e2.printStackTrace();
			}
			nf = null;
			nfOrigem = null;
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	
}
