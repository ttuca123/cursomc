package com.nelioalves.cursomc.services.validation.utils;



public class BR {

	
	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	  

	   public static boolean validaCNPJ(String cnpj) {
	      if ((cnpj==null)||(cnpj.length()!=14)) return false;

	      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
	      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
	      return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	
		
	}
	
	
	public static boolean validaCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
             return false;

        try {
             Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
         return false;
        }

    return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
   }
	
	 private static boolean isCPFPadrao(String cpf) {
	        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
	      || cpf.equals("33333333333")
	      || cpf.equals("44444444444")
	      || cpf.equals("55555555555")
	      || cpf.equals("66666666666")
	      || cpf.equals("77777777777")
	      || cpf.equals("88888888888")
	      || cpf.equals("99999999999")) {

	             return true;
	        }

	    return false;
	   }

	   private static String calcDigVerif(String num) {
	        Integer primDig, segDig;
	    int soma = 0, peso = 10;
	    for (int i = 0; i < num.length(); i++)
	         soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

	    if (soma % 11 == 0 | soma % 11 == 1)
	         primDig = new Integer(0);
	        else
	             primDig = new Integer(11 - (soma % 11));

	    soma = 0;
	        peso = 11;
	        for (int i = 0; i < num.length(); i++)
	             soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

	        soma += primDig.intValue() * 2;
	        if (soma % 11 == 0 | soma % 11 == 1)
	             segDig = new Integer(0);
	        else
	             segDig = new Integer(11 - (soma % 11));

	        return primDig.toString() + segDig.toString();
	   }
}
