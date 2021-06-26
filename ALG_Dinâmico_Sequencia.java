/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

class Main {

    private static int seq = 0;
    private static int melhorSeq = 0;
    private static int Nrseq = 0;
    private static String descricaoSequencia = "";
    private static String descricaoSequenciaAtual = "";
    private static int G[][] = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    public static int verDireita(int[][] m, int l, int c) {
        int retorno = 0;
        if (c + 1 <= m[l].length - 1) {
            if ((m[l][c] - m[l][c + 1]) == -1 || (m[l][c] - m[l][c + 1]) == 1) {
                retorno = 1;
            } else {
                retorno = 0;
            }
        } else {
            retorno = 0;
        }
        return retorno;
    }

    public static int verBaixo(int[][] m, int l, int c) {
        int retorno = 0;
        if (l + 1 <= m[l].length - 1) {// Verificando se a coluna a frente é menor que o final do que a matriz
            if ((m[l][c] - m[l + 1][c]) == -1 || (m[l][c] - m[l + 1][c]) == 1) {
                retorno = 1;
            } else {
                retorno = 0;
            }
        } else {
            retorno = 0;
        }
        return retorno;
    }

    public static int verSolMelhor(int[][] m, int l, int c) throws NumberFormatException {
        int retDireita = 0;
        int retBaixo = 0;
        int retorno = 0;
        int melhorDireita = 0;
        int melhorBaixo = 0;
        //analisando a direita

        int[][] N = new int[m.length][m.length];
        N = m;

        // indo para a direita
        c++;
        for (int i = l; i <= m.length - 1;) {
            for (int j = c; j <= m.length - 1;) {
                retDireita = verDireita(m, i, j);
                retBaixo = verBaixo(m, i, j);
                if (retBaixo == 0 && retDireita == 0) {
                    retorno = 0;
                    i = G.length + 1;
                    j = G.length + 1;
                } else {
                    if (j == m.length - 1) {
                        //   i++;
                    }

                    if (retBaixo == 1 && retDireita == 1) {
                        if ((N[i + 1][j] > N[i][j + 1])) { // se der empate e puder ir para os dois lados
                            i++; // linha recebe a solução
                        } else {
                            j++; // linha recebe a solução
                        }
                        melhorDireita++;
                    } else if (retBaixo == 1) { // se a opção que for escolhida for ade baixo
                        i++; // linha recebe a solução
                        melhorDireita++;
                    } else if (retDireita == 1) {// se a opção que for escolhida for ade baixo
                        j++; // linha recebe a solução
                        melhorDireita++;
                    }

                }
            }
        }
        // System.out.println("teste");
        l++;
        for (int i = l; i <= m.length - 1;) {
            for (int j = c; j <= m.length - 1;) {

                retDireita = verDireita(m, i, j);
                retBaixo = verBaixo(m, i, j);

                if (retBaixo == 0 && retDireita == 0) {
                    retorno = 0;
                    i = G.length + 1;
                    j = G.length + 1;
                } else {
                    if (retBaixo == 1 && retDireita == 1) {
                        if ((N[i + 1][j] > N[i][j + 1])) { // se der empate e puder ir para os dois lados
                            i++; // linha recebe a solução
                        } else {
                            j++; // linha recebe a solução
                        }
                        melhorBaixo++;

                    } else if (retBaixo == 1) { // se a opção que for escolhida for ade baixo
                        i++; // linha recebe a solução
                        melhorBaixo++;
                    } else if (retDireita == 1) {// se a opção que for escolhida for ade baixo
                        j++; // linha recebe a solução
                        melhorBaixo++;
                    }
                }
            }
        }
        if (melhorDireita > melhorBaixo) {
            retorno = 1;
        } else {
            retorno = 0;
        }

        return retorno;
    }

    public static void teste(int[][] m, int posRetomadaL, int posRetomadaC) {
        int cp = 0;
        int lp = 0;
        int retDireita = 0;
        int retBaixo = 0;
        seq = 0;
        int posicaoSequencia = 0;
        descricaoSequenciaAtual = "";
        //int[][] N = new int[m.length][m.length];
        for (int l = posRetomadaL; l < m.length;) {
            for (int c = posRetomadaC; c < m[l].length;) {

                cp = c;
                if (cp <= m[l].length - 1) {// Verificando se a coluna a frente é menor que o final do que a matriz
                    cp++; // olhando para a próxima coluna
                }
                lp = l;
                if (lp <= m[l].length - 1) {// Verificando se a coluna a frente é menor que o final do que a matriz
                    lp++; // olhando para a próxima linha
                }
                //Verifica a direita
                retDireita = verDireita(m, l, c);
                retBaixo = verBaixo(m, l, c);

                if (retBaixo == 0 && retDireita == 0) {
                    if (seq != 0) {
                        if (seq > 0) {
                            
                            System.out.println("Sequência = " + seq);
                            //G = N;
                        }

                        return;// fim do algoritmo
                    } else {
                        c = cp;
                    }
                } else {
                    if (retBaixo == 1 && retDireita == 1) {

                        if (verSolMelhor(m, l, c) == 1) {
                            c = cp; // linha recebe a solução
                            G[l][c] = 1;
                        } else {
                            l = lp; // linha recebe a solução
                            G[l][c] = 1;
                        }
                        // buscar melhor caminho
                        if ((m[lp][c] > m[l][cp])) { // se der empate e puder ir para os dois lados
                            // é comparado com o maior numero 
                            if (seq != 0) {
                                l = lp; // linha recebe a solução
                                G[l][c] = 1;
                            }
                        } else {
                            if (seq != 0) {
                                c = cp; // linha recebe a solução
                                G[l][c] = 1;
                            }

                        }
                    } else if (retBaixo == 1) { // se a opção que for escolhida for ade baixo
                        if (seq != 0) {
                            l = lp; // linha recebe a solução
                            G[l][c] = 1;
                        }
                    } else if (retDireita == 1) {// se a opção que for escolhida for ade baixo
                        if (seq != 0) {
                            c = cp; // linha recebe a solução
                            G[l][c] = 1;
                        }
                    }
                    seq++;

                    System.out.println("___________ ");
                    System.out.println("Numero da posição Linha= " + l + "Coluna= " + c + "posição = " + m[l][c]);
                    if (seq == 1) {
                        G[l][c] = 1;
                    }
                    descricaoSequenciaAtual
                            = descricaoSequencia = String.valueOf(m);
                    //System.out.println("Descrição =" + seq);
                }

            }
        }

    }

    public static void main(String[] args) {
        int m[][] = {
            {7, 5, 2, 3, 1},
            {3, 4, 1, 4, 7},
            {1, 5, 6, 5, 8},
            {3, 4, 3, 6, 9},
            {3, 2, 2, 3, 4}
        };
        int zero = 0;
        int nDiferenteZero = 0;

        for (int i = 0; i <= m.length - 1; i++) {
            for (int j = 0; j <= m[i].length - 1; j++) {

                nDiferenteZero = G[i][j];

                if (nDiferenteZero == 0) {
                    int direta = verDireita(m, i, j);
                    int baixo = verBaixo(m, i, j);
                    int linhaG = 0;
                    int colulaG = 0;
                    //fazer ultima linha e ultima coluna do G
                    if (i == G.length - 1) {
                        linhaG = 0;
                    } else {
                        linhaG = 1;
                    }
                    if (j == G.length - 1) {
                        colulaG = 0;
                    } else {
                        colulaG = 1;
                    }

                    if (G[i + linhaG][j] == 0) {
                        if (G[i][j + colulaG] == 0) {

                            if (direta != 0 || baixo != 0) {

                                teste(m, i, j);

                                if (Nrseq > 1) {
                                    if (seq > melhorSeq) {
                                        melhorSeq = seq;
                                    }
                                } else if (Nrseq == 0) {
                                    melhorSeq = seq;
                                    //  t = G;
                                }
                                Nrseq = Nrseq + 1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(
                "Melhor sequencia contém " + melhorSeq + " casas ");
        System.out.println(
                "Numero de sequencia é igual a = " + Nrseq);

    }
}
