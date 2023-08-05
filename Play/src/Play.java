import java.util.Scanner;

public class Play {
    public static final String BACKGROUND_BRIGHT_RED = "\u001b[41;1m ";
    public static final String BACKGROUND_BRIGHT_YELLOW = "\u001b[43;1m "; //s
    public static final String BACKGROUND_BRIGHT_GREEN = "\u001b[42;1m ";
    public static final String RESET = " \u001b[0m ";

    static String[] board = new String[10];
    static int i = 0;
    static Scanner scanner = new Scanner(System.in);
    static String str = "";
    static int a=5; //bu githabda prosti tekshirish uchun.


    public static void main(String[] args) {

        String player1 = "X";
        String player2 = "O";

        while (true) {
            System.out.println("1=>Play , 0=>Exit ");
            System.out.print("Tanlang:");
            if (scanner.nextLine().equals("0")) {
                System.out.println("Game over");
                return;
            }
            play(player1, player2);
        }
    }

    /**
     * playGround --> methodda biz String tipidagi belgilarni qabul qiluvchi massivni
     * 1 dan 9 gacha sonlarni yashil ranga boyab  board  massiviga saqlab qoyyapmiz.
     */
    public static void playGround() {
        for (int i = 1; i < board.length; i++) {
            board[i] = BACKGROUND_BRIGHT_GREEN + i + RESET;
        }
    }

    /**
     * printBoard --> methodda biz playGround --> methodi yordamida toldirilgan massivni ichidan
     * elementlarini olib  X va O  oyini uchun kerak boladigan maydonni yasab oldik.
     */
    public static void printBoard() {
        for (int i = 1; i < board.length; i++) {
            System.out.print(board[i]);
            if (i % 3 == 0) {
                System.out.println("\n");
            }
        }

    }

    /**
     * step --> methodda bizdan X yoki O  ni printBoard()--> da yasab olingan
     * maydonni qaysi raqamini orniga qo'yishimizni  soraydi.
     *
     * @param player
     */
    public static void step(String player) {
        System.out.print(player + ":");
        String cell = scanner.nextLine();
        if (Integer.parseInt(cell) > 0 && Integer.parseInt(cell) <= 9) {
            replace(cell, player);
        } else {
            System.out.println("                           [1,9] oraliqda qiymat kiriting!!!!  ");
            step(player);
        }

    }


    /**
     * replace --> methodda biz  step--> methodi yordamida soralgan X yoki O ni orniga
     * agar X: soralgan bolsa biz 2 ni bersak 2 raqamini orniga qizil rangga boyalgan
     * X ni board massivini ichidan 2 elementi oriniga qizil ranga boyalgan X ni almashtirib
     * qoyadi. Agar O: soralgan bolsa biz 4 ni bersak 4 raqamini orniga sariq rangga boyalgan
     * O ni board massivini ichidan 4 elementi oriniga sariq ranga boyalgan X ni almashtirib
     * qoyish uchun  qollaymiz.
     *
     * @param player
     * @param cell
     */
    public static void replace(String cell, String player) {
        if (board[Integer.parseInt(cell)].contains(BACKGROUND_BRIGHT_GREEN)) {
            str = "";
            String color = player.equals("X") ? BACKGROUND_BRIGHT_RED + player + RESET : BACKGROUND_BRIGHT_YELLOW + player + RESET;
            board[Integer.parseInt(cell)] = color;
            i += 1;
            str += player;
            printBoard();

        } else {
            System.out.println("TOLDIRILGAN!!!");
            step(player);

        }
    }


    /**
     * winCondition()- methoddda biz X va O oyinini yutish va yutmaslik yoki
     * durrang bolish shartlarini barcha hollatlar uchun birma bir tekshirib
     * chiqqanmiz.
     *
     * @return
     */
    public static boolean winCondition() {
        if ((board[1].equals(board[2]) && board[2].equals(board[3]))
                || (board[4].equals(board[5]) && board[5].equals(board[6]))
                || (board[7].equals(board[8]) && board[8].equals(board[9]))
                || (board[1].equals(board[4]) && board[4].equals(board[7]))
                || (board[2].equals(board[5]) && board[5].equals(board[8]))
                || (board[3].equals(board[6]) && board[6].equals(board[9]))
                || (board[1].equals(board[5]) && board[5].equals(board[9]))
                || (board[7].equals(board[5]) && board[5].equals(board[3]))) {
            System.out.println(str + "-yutdi.");
            i = 10;
            return true;
        } else if (i == 9) {
            System.out.println("Durang!!");
            return true;
        }
        return false;
    }

    /**
     * Bu method yani play()-Method bizga oyinda X yoki O ni yutgan paytida toxtatish
     * yoki durrang paytida toxtatish va O'yin tugagach yana oyinni davom ettirish
     * yoki oyinni butkul toxtatish uchun xizmat qilyapti.
     *
     * @param player1
     * @param player2
     */
    public static void play(String player1, String player2) {
        playGround();
        printBoard();

        i = 0;
        while (i <= 8 && !winCondition()) {
            if (!winCondition()) {
                step(player1);
            }
            if (!winCondition()) {
                step(player2);
            }
        }
    }


}