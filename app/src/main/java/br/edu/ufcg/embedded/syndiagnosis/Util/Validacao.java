package br.edu.ufcg.embedded.syndiagnosis.Util;

import java.util.InputMismatchException;

/**
 * Created by Nicolas on 26/08/2015.
 */
public class Validacao {

    public static boolean validaCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }


    public static boolean validaEmail(String email)  {
        if (email == null)
            return false;
        String regex = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
        String regex2 = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+";
        String regex3 = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]++\\.[A-Za-z]+";
        boolean ok = email.matches(regex);
        if (!ok)
            ok = email.matches(regex2);
        if (!ok)
            ok = email.matches(regex3);
        if (!ok) {
            return false;
        }
        // tem que checar dps se o email já é cadastrado
        //if (emailJaCadastrado(email)) {
        ///  return false;
        ///}
        return ok;
    }

    public static boolean checaEntradaEmBranco(String s) {
        int tam = s.length();
        for (int i = 0; i < tam; i++) {
            if (!Character.isWhitespace(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean validaTamanhoSenha(String senha) {
        if (senha == null)
            return false;
        else if (checaEntradaEmBranco(senha))
            return false;
        else if (senha.length() < 6 || senha.length() > 12) {
            return false;
        }
        return true;
    }

    public static boolean validaNome(String nome) {
        if (nome == null || nome.equals("") || checaEntradaEmBranco(nome))
            return false;
        return true;
    }
}
