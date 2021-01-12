import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;



public class CreateEvents {

    private static void createItemEvent(ItemEvent itemEvent, LocalDateTime startDate, String ql, String workbench) {
        itemEvent.setQl(ql);
        itemEvent.setWorkbench(workbench);
        itemEvent.setSite(String.valueOf(Site.LAHR));
        System.out.println(startDate.getSecond() + "|" + itemEvent.getWorkbench() + "|" + itemEvent.getQl());

    }

    private static void createItemScannedEvents( LocalDateTime startDate, String ql, String workbench) {
        var itemScannedEvent = new ItemEvent();
        createItemEvent(itemScannedEvent, startDate, ql, workbench);
    }

    private static void createItemDroppedEvents( LocalDateTime startDate, String ql, String workbench, String dropset_id) {
        var itemDroppedEvent = new ItemEvent();
        itemDroppedEvent.setId(dropset_id);
        createItemEvent(itemDroppedEvent, startDate, ql, workbench);
    }


    private static LocalDateTime createDropset(LocalDateTime startDate, String dropset_id, Type type) {

        int rand = new Random().nextInt(3) + 1;
        rand = 3;

        String ql = "";
        String workbench = "";

        for (int j = 0; j < rand; j++) {
            switch (type) {
                case Type.OK:
                    ql = RandomValueFixture.ql();
                    workbench = RandomValueFixture.workbench();
                    createItemDroppedEvents(startDate, ql, workbench, dropset_id);
                    createItemScannedEvents(startDate, ql, workbench);
                    break;
                case Type.SUPERSET:
                    for (int i = 0; i < rand; i++) {
                        ql = RandomValueFixture.ql();
                        workbench = RandomValueFixture.workbench();
                        createItemDroppedEvents(startDate, ql, workbench, dropset_id);
                    }
                    createItemScannedEvents(startDate, ql, workbench);
                    break;
                case Type.SUBSET:
                    ql = RandomValueFixture.ql();
                    workbench = RandomValueFixture.workbench();
                    createItemDroppedEvents(startDate, ql, workbench, dropset_id);
                    for (int i = 0; i < rand; i++) {
                        createItemScannedEvents(startDate, ql, workbench);
                        ql = RandomValueFixture.ql();
                        workbench = RandomValueFixture.workbench();
                    }
                    break;
                case Type.SUPERANDSUBSET:
                    for (int i = 0; i < rand; i++) {
                        ql = RandomValueFixture.ql();
                        workbench = RandomValueFixture.workbench();
                        createItemDroppedEvents(startDate, ql, workbench, dropset_id);
                    }
                    for (int i = 0; i < rand; i++) {
                        ql = RandomValueFixture.ql();
                        workbench = RandomValueFixture.workbench();
                        createItemScannedEvents(startDate, ql, workbench);
                    }
                    break;
            }

            startDate = startDate.plusSeconds(2);
        }
        return startDate;
    }

    static void createCombinedEvents() {

        LocalDateTime time = LocalDateTime.now();
        Type type = Type.SUPERSET;

        for (int i = 0; i < 3; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            var id = time.format(formatter) + "_" + i;
            System.out.println("Dropset ->" + id + " Type ->" + type);
            time = createDropset(time, id, type);
            time = time.plusMinutes(1);
        }
    }

    public static void main(String[] args) {
        createCombinedEvents();
    }

}
