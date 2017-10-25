package com.company;

/**
 * Created by M4800 on 06-Nov-16.
 */
public class Printer {
    private int toner;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        if(tonerLevel > -1 && tonerLevel <= 100){
            this.toner = tonerLevel;
        }else {
            this.toner = -1;
        }

        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int tonerAmount(int tonerAmount){
        if(tonerAmount > 0 && tonerAmount <= 100){
            if(this.toner + tonerAmount > 100){
                return -1;
            }
            this.toner += tonerAmount;
            return this.toner;
        }else {
            return -1;
        }
    }

    public int printPages(int pages){
        int pagesToPrint = pages;
        if(this.duplex){
            pagesToPrint /= 2;
            System.out.println("Printing in duplex mode");
        }
        this.pagesPrinted += pagesToPrint;
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}
