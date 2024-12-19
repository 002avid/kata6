package org.ulpgc.is2.view;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ulpgc.is2.control.CommandFactory;
import org.ulpgc.is2.control.commands.WorkingDateCommand;
import org.ulpgc.is2.control.commands.WorkingDaysCommand;
import org.ulpgc.is2.view.adapters.WorkingDateAdapter;
import org.ulpgc.is2.view.adapters.WorkingDaysAdapter;

public class WorkingDaysService {
    private final int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
        factory.register("working-days", WorkingDaysBuilder());
        factory.register("working-date", workingDateBuilder());
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res){
        factory.with(req, res).build(command).execute();
    }

    public void stop(){
        app.stop();
    }


    private CommandFactory.Builder workingDateBuilder() {
        return (req, res) -> new WorkingDateCommand(WorkingDateAdapter.inputOf(req), WorkingDateAdapter.outputOf(res));
    }

    private CommandFactory.Builder WorkingDaysBuilder() {
        return (req, res) -> new WorkingDaysCommand(WorkingDaysAdapter.inputOf(req), WorkingDaysAdapter.outputOf(res));
    }

    public void start() {
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .start(port);
    }
}
