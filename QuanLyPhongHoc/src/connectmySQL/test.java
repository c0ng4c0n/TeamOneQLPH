package connectmySQL;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        int i;
        String s;
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap s = ");
        s = in.nextLine();
        for (i = 0; i < s.length(); ++i)
            s = s.substring(0,i ) + String.valueOf(s.charAt(i)).toLowerCase() + s.substring(i+1,s.length());
        i = 0;
        s = s.trim();
        while (i < s.length() - 1) {
            if (s.charAt(i) == ' ' & s.charAt(i + 1) == ' ') s = s.substring(0,i) + s.substring(i + 1, s.length());
            else i = i + 1;
        }
        s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1,s.length());
        for (i = 1; i < s.length(); ++i)
            if (s.charAt(i-1) == ' ')
                s = s.substring(0, i) + String.valueOf(s.charAt(i)).toUpperCase() + s.substring(i+1, s.length());
        System.out.println(s);
    }

}
