package jreti.eti.br.jrcalc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by José on 22/04/2016.
 */
public class Calculadora {

    public String calcular(String expressao) {
        String exppos = this.in2pos(expressao);
        return this.calculaPos(exppos);
    }

    private String calculaPos(String expressao) {
        Stack<String> pilha = new Stack();
        String aux = "";
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '.') {
                pilha.push(aux);
                aux = "";
            } else if (isOperador(c)) {
                String op1s = pilha.pop();
                String op2s = pilha.pop();
                if (isNumeric(op1s) && isNumeric(op2s)) {
                    double op2 = Double.parseDouble(op1s);
                    double op1 = Double.parseDouble(op2s);
                    double res = 0;
                    switch (c) {
                        case '/':
                            if (op2 == 0)
                                return "Expressão Inválida! Divisão por Zero!";
                            else res = op1 / op2;
                            break;
                        case '*':
                            res = op1 * op2;
                            break;
                        case '+':
                            res = op1 + op2;
                            break;
                        case '-':
                            res = op1 - op2;
                            break;
                    }
                    pilha.push(Double.toString(res));
                }else return "Expressão Inválida! Operadores Não Númericos";
            } else aux += c;
        }
        return pilha.pop();
    }

    private String in2pos(String expressao) {
        Stack<Character> pilha = new Stack();
        String resultado = "";
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (!isOperador(c))
                resultado += c;
            else {
                resultado += '.';
                while (!pilha.empty() && prioridade(pilha.lastElement()) >= prioridade(c))
                    resultado += pilha.pop();
                pilha.push(c);
            }
        }
        resultado += '.';
        while (!pilha.empty())
            resultado += pilha.pop();
        return resultado;
    }

    private static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean isOperador(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private static int prioridade(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '/':
            case '*':
                return 2;
            default:
                return 0;
        }
    }
}
