package org.kaoden.ws.homework;

import org.kaoden.ws.homework.service.Finder;
import org.kaoden.ws.homework.service.Reader;

public class Main {
    public static void main( String[] args ) {
        Reader reader = new Reader();
        Finder finder = new Finder(reader.readEntriesFromJson(args[0]));
        Menu menu = new Menu(finder);
        menu.selectSearchMode();
    }
}
