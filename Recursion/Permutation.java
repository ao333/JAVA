public class Permutation {

    public static void main(String[] args) { permutation("ABC"); }

    static void permutation(String str) { permutate("", str); }

    static void permutate(String prefix, String suffix ) {
        if (suffix.length() == 0) { System.out.println(prefix); }
        else {
            for (int i = 0; i < suffix.length(); i++) {
                String newPrefix = prefix + suffix.charAt(i);
                String suffix1 = suffix.substring(0, i) ;
                String suffix2 = suffix.substring(i + 1);
                permutate(newPrefix, suffix1 + suffix2);
            }
        }
    }
}