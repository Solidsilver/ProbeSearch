import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class Main {
    public static void main (String[] args) throws Exception {
        String[] wordList = new String[0];
        System.out.println("Loading Dictionary...");
        wordList = fileToString(wordList, "usa2.txt");
        System.out.println("Dictionary Loaded!!\n\n");
        //Arrays.sort(wordList);
        int wordLength = 0;
        String strIn;
        Scanner kb = new Scanner(System.in);
        System.out.print("WordLen: ");
        wordLength = getKbInt();
        String[] refined = trimList(wordList, wordLength);
        int menuIn;
        do {
            printArr(refined);
            System.out.print("Todo:\n" + 
                                "1) Refine Word      2) Add Non-Used Letters" + 
                                "\n3) Reset\n~~> ");
            menuIn = getKbInt();
            if (menuIn == 1) {
                refined = refineList(refined);
            } else if(menuIn == 2) {
                System.out.print("Enter Letter to Remove: ");
                refined = remNonLettrs(refined, kb.nextLine());
            } else if (menuIn == 3) {
                System.out.print("WordLen: ");
            wordLength = getKbInt();
            refined = trimList(wordList, wordLength);
            }
        } while (menuIn != 0);

    }

    private static String[] refineList(String[] arr) {
        Scanner kb = new Scanner(System.in);
        //printArr(arr);
        System.out.print("\nRefine Word:");
        String strIn = kb.nextLine();
        return refineMatch(arr, strIn);
    }

    private static String[] remNonLettrs(String[] arrIn, String letter) {
        String[] cache = new String[0];
        for (int ix = 0; ix < arrIn.length; ix++) {
            if (!containsLetter(arrIn[ix], letter)) {
                cache = push(cache, arrIn[ix]);
            }
        }
        return cache;
    }

    private static int getKbInt() {
        Scanner kb = new Scanner(System.in);
        int in = 0;
        while (in < 1) {
            try {
                in = kb.nextInt();
                kb.nextLine();
            } catch (Exception e) {
                System.out.println("You must enter an integer.");
                kb.nextLine();
            }
        }
        return in;
    }

    private static void printArr(String[] arr) {
        for (int ix = 1; ix <= arr.length; ix++) {
            System.out.println(arr[arr.length - ix]);
        }
    }

    private static String[] fileToString(String[] str, String fname) throws Exception {
        String[] cache = new String[0];
        Scanner fin = new Scanner(new File(fname));
        while (fin.hasNextLine()) {
            cache = push(cache, fin.nextLine());
        }
        return cache;
    }

    private static String[] refineMatch(String[] arrIn, String match) {
        String[] cache = new String[0];
        for (int ix = 0; ix < arrIn.length; ix++) {
            if (stringMatches(arrIn[ix], match)) {
                cache = push(cache, arrIn[ix]);
            }
        }
        return cache;
    }

    private static String[] trimList(String[] lst, int len) {
        String[] cache = new String[0];
        for (int ix = 0; ix < lst.length; ix++) {
            if (lst[ix].length() == len) {
                cache = push(cache, lst[ix]);
            }
        }
        return cache;
    }

    private static boolean stringMatches(String exp, String in) {
        String matchIn;
        String matchOut;
        for (int ix = 0; ix < exp.length(); ix++) {
            matchIn = in.substring(ix, ix+1);
            matchOut = exp.substring(ix, ix+1);
            if (!matchIn.equals("?")) {
                if (!matchIn.equals(matchOut)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean containsLetter(String in, String lettr) {
        String matchIn;
        for (int ix = 0; ix < in.length(); ix++) {
            matchIn = in.substring(ix, ix+1);
            if (matchIn.equals(lettr)) {
                return true;
            }
        }
        return false;
    }

    private static String[] push(String[] arr, String itm) {
        String[] cache = new String[arr.length + 1];
        for (int ix = 0; ix < cache.length - 1; ix++) {
            cache[ix] = arr[ix];
        }
        cache[cache.length - 1] = itm;
        return cache;
    }
}
