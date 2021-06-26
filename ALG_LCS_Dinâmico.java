import java.util.HashMap;
import java.util.Map;
 
class Main
{
    // Função para encontrar o comprimento da maior subsequência comum de substring
    public static int LCSLength(String X, String Y, int m, int n,
                                Map<String, Integer> lookup)
    {
        // Cria um nó folha, se o final de qualquer string for alcançado
        if (m == 0 || n == 0) {
            return 0;
        }
 
        // Chave de mapa única a partir de elementos dinâmicos da entrada
        String key = m + "|" + n;
 
        // Se o subproblema for visto pela primeira vez, resolva-o e armazene seu resultado em um mapa
        if (!lookup.containsKey(key))
        {
            // Se o caractere das strings comparados forem iguais
            if (X.charAt(m - 1) == Y.charAt(n - 1)) {
                lookup.put(key, LCSLength(X, Y, m - 1, n - 1, lookup) + 1);
            }
            else {
                // Se os caracteres forem diferentes, cria duas ramificações,
                // comparando o caractere atual de ambas strings com os ateriores
                lookup.put(key, Integer.max(LCSLength(X, Y, m, n-1, lookup), LCSLength(X, Y, m - 1, n, lookup)));
            }
        }
 
        // Retorna a solução do subproblema do mapa
        return lookup.get(key);
    }
 
    public static void main(String[] args)
    {
        String X = "ABCBDAB", Y = "BDCABA";
 
        // Mapa para armazenar soluções dos subproblemas
        Map<String, Integer> lookup = new HashMap<>();
 
        System.out.print("The length of the LCS is "
                + LCSLength(X, Y, X.length(), Y.length(), lookup));
    }
}
