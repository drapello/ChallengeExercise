package com.example.refactor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import static com.example.refactor.RandomValueFixture.*;
import static com.example.refactor.Type.*;
import static com.example.refactor.Site.*;


public class CreateEventsRefactored {

    public static ItemEvent createItemEvent(LocalDateTime startDate, String ql, String workbench, String dropset_id) {
        var itemEvent = new ItemEvent();
        if (dropset_id != null) {
            itemEvent.setId(dropset_id);
        }
        itemEvent.setQl(ql);
        itemEvent.setWorkbench(workbench);
        itemEvent.setSite(String.valueOf(LAHR));
        System.out.println(startDate.getSecond() + "|" + itemEvent.getWorkbench() + "|" + itemEvent.getQl());
        return itemEvent;
    }

    public static LocalDateTime createDropset(ArrayList<ItemEvent> itemEvents,LocalDateTime startDate, String dropset_id, Type type) {

        int rand = new Random().nextInt(3) + 1;
        rand = 3;

        String ql = "";
        String workbench = "";

        for (int j = 0; j < rand; j++) {
            switch (type) {
                case OK:
                    ql = ql();
                    workbench = workbench();
                    itemEvents.add(createItemEvent(startDate, ql, workbench, dropset_id));
                    itemEvents.add(createItemEvent(startDate, ql, workbench, null));
                    break;
                case SUPERSET:
                    for (int i = 0; i < rand; i++) {
                        ql = ql();
                        workbench = workbench();
                        itemEvents.add(createItemEvent(startDate, ql, workbench, dropset_id));
                    }
                    itemEvents.add(createItemEvent(startDate, ql, workbench,null));
                    break;
                case SUBSET:
                    ql = ql();
                    workbench = workbench();
                    itemEvents.add(createItemEvent(startDate, ql, workbench, dropset_id));
                    
                    for (int i = 0; i < rand; i++) {
                        itemEvents.add(createItemEvent(startDate, ql, workbench,null));
                        ql = ql();
                        workbench = workbench();
                    }
                    break;
                case SUPERANDSUBSET:
                    for (int i = 0; i < rand; i++) {
                        ql = ql();
                        workbench = workbench();
                        itemEvents.add(createItemEvent(startDate, ql, workbench, dropset_id));
                    }
                    for (int i = 0; i < rand; i++) {
                        ql = ql();
                        workbench = workbench();
                        itemEvents.add(createItemEvent(startDate, ql, workbench,null));
                    }
                    break;
            }

            startDate = startDate.plusSeconds(2);
        }
        return startDate;
    }

    public static void createCombinedEvents() {

        LocalDateTime time = LocalDateTime.now();
        Type type = Type.SUPERSET;
        ArrayList<ItemEvent> itemEvents = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            var id = time.format(formatter) + "_" + i;
            System.out.println("Dropset ->" + id + " Type ->" + type);
            time = createDropset(itemEvents, time, id, type);
            time = time.plusMinutes(1);
        }
    }

    public static void main(String[] args) {
        createCombinedEvents();
    }

}

