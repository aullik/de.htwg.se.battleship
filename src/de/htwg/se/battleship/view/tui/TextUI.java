/**
 * 
 */
package de.htwg.se.battleship.view.tui;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public abstract class TextUI {

    public static final String STRING_NULL = "String cannot be null";
    public static final String ERROR = "ERROR: ";

    private Scanner       in;
    private PrintWriter   out;

    protected void initInOut(InputStream in, OutputStream out) {
        this.in  = new Scanner(in);
        this.out = new PrintWriter(out, true);
    }

    protected void outputError(String s) {
        outputln(ERROR + s);
    }

    protected Integer nextInt() {
        Integer result = null;
        if (in.hasNextInt()) {
            result = in.nextInt();
        } else {
            in.nextLine();
        }
        return result;
    }

    protected void outputHeader() {

        outputln("");
        outputln("");

        /* Game name as ASCII-Art: http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship */
        /* Original ASCII-Code:
         *      ____        _   _   _           _     _
         *     |  _ \      | | | | | |         | |   (_)
         *     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __
         *     |  _ < / _` | __| __| |/ _ \/ __| '_ \| | '_ \
         *     | |_) | (_| | |_| |_| |  __/\__ \ | | | | |_) |
         *     |____/ \__,_|\__|\__|_|\___||___/_| |_|_| .__/
         *                                             | |
         *                                             |_|
         */
        outputln("* * * * * * * * * * * * * * * * * * * * * * * * * *");
        outputln("*  ____        _   _   _           _     _        *");
        outputln("* |  _ \\      | | | | | |         | |   (_)       *");
        outputln("* | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __   *");
        outputln("* |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\  *");
        outputln("* | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) | *");
        outputln("* |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/  *");
        outputln("*                                         | |     *");
        outputln("*                                         |_|     *");
        outputln("* * * * * * * * * * * * * * * * * * * * * * * * * *");


        outputln("");
    }

    protected void outputln(String s) {
        check(s);
        out.println(s);
    }

    protected void output(String s) {
        check(s);
        out.print(s);
        out.flush();
    }

    private void check(String s) {
        if (s == null) {
            throw new IllegalArgumentException(STRING_NULL);
        }
    }
}
