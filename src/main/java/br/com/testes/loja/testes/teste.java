package br.com.testes.loja.testes;

import java.util.Random;

public class teste {

    public static void main(String[] args) {
	int[] A, B;
	int n = 10;
	A = criaVetorAleatorio(n);
	B = A.clone();
	System.out.println(buscaIntuitiva(A));
	System.out.println(buscaInteligente(B));
    }

    static boolean buscaIntuitiva(int[] A) {
	for (int i = 0; i < A.length; i++) {
	    for (int j = i + 1; j < A.length; j++) {
		for (int k = 0; k < A.length; k++) {
		    if (k != i && k != j) {
			if (A[k] == A[i] + A[j]) {
			    System.out.println(A[k] + " = " + A[i] + " + " + A[j]);
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }

    static boolean buscaInteligente(int[] A) {
	for (int i = 0; i < A.length; i++) {
	    for (int j = i + 1; j < A.length; j++) {
		for (int k = 0; k < A.length; k++) {
		    buscaBinaria(A, i, A.length, k);
		}
	    }
	}
	return false;

    }

    static int buscaBinaria(int[] A, int i, int f, int chave) {
	if (i <= f) {
	    int m = (i + f) / 2;
	    if (A[m] == chave)
		return m;
	    if (A[m] > chave)
		return buscaBinaria(A, i, m - 1, chave);
	    if (A[m] < chave)
		return buscaBinaria(A, m + 1, f, chave);
	}
	return -1;
    }

    static int[] criaVetorAleatorio(int n) {
	Random randomGenerator = new Random();
	int[] A = new int[n];
	for (int i = 0; i < n; i++) {
	    A[i] = randomGenerator.nextInt(100000 * n);
	}
	return A;
    }

}
