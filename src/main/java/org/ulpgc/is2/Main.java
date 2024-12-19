package org.ulpgc.is2;

import org.ulpgc.is2.control.CommandFactory;
import org.ulpgc.is2.view.WorkingDaysService;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        new WorkingDaysService(7070, factory).start();
    }
}
