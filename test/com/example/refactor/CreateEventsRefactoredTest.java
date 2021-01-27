package com.example.refactor;

import com.example.refactor.CreateEventsRefactored;
import com.example.refactor.ItemEvent;
import com.example.refactor.RandomValueFixture;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.refactor.RandomValueFixture.*;
import static com.example.refactor.Type.*;

class CreateEventsRefactoredTest {

    CreateEventsRefactored createEventsRefactored = new CreateEventsRefactored();

    @Test
    void testCreateItemEvent() {
        ItemEvent itemDroppedEvent = new ItemEvent();
        LocalDateTime startDate = LocalDateTime.now();
        String ql = ql();
        String workbench = workbench();
        ItemEvent finalItemDroppedEvent = createEventsRefactored.createItemEvent(startDate, ql, workbench, null);
        assertAll(() -> assertEquals(ql, finalItemDroppedEvent.getQl()),
                () -> assertEquals(workbench, finalItemDroppedEvent.getWorkbench())
                );

    }

    @Test
    void createItemScannedEvents() {
        LocalDateTime startDate = LocalDateTime.now();
        String ql = RandomValueFixture.ql();
        String workbench = RandomValueFixture.workbench();
        ItemEvent finalItemDroppedEvent = createEventsRefactored.createItemEvent(startDate, ql, workbench,null);
        assertAll(() -> assertEquals(ql, finalItemDroppedEvent.getQl()),
                () -> assertEquals(workbench, finalItemDroppedEvent.getWorkbench())
        );
    }

    @Test
    void createItemDroppedEvents() {
        LocalDateTime startDate = LocalDateTime.now();
        String ql = RandomValueFixture.ql();
        String workbench = RandomValueFixture.workbench();
        String id = uuid();
        ItemEvent finalItemDroppedEvent = createEventsRefactored.createItemEvent(startDate, ql, workbench, id);
        assertAll(() -> assertEquals(ql, finalItemDroppedEvent.getQl()),
                () -> assertEquals(workbench, finalItemDroppedEvent.getWorkbench()),
                () -> assertEquals(id, finalItemDroppedEvent.getId())
                );
    }

    @Test
    void createDropsetOK() {
        ArrayList<ItemEvent> itemEvents = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        var id = uuid();
        time = createEventsRefactored.createDropset(itemEvents, time, id, OK);
        assertAll(() -> assertEquals(6, itemEvents.size()),
                () -> assertEquals(3, itemEvents.stream().filter(itemEvent -> itemEvent.getId() == id).count())
        );
    }

    @Test
    void createDropsetSuperSet() {
        ArrayList<ItemEvent> itemEvents = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        var id = uuid();
        time = createEventsRefactored.createDropset(itemEvents, time, id, SUPERSET);
        assertAll(() -> assertEquals(12, itemEvents.size()),
                () -> assertEquals(9, itemEvents.stream().filter(itemEvent -> itemEvent.getId() == id).count())
        );
    }

    @Test
    void createDropsetSubSet() {
        ArrayList<ItemEvent> itemEvents = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        var id = uuid();
        time = createEventsRefactored.createDropset(itemEvents, time, id, SUBSET);
        assertAll(() -> assertEquals(12, itemEvents.size()),
                () -> assertEquals(3, itemEvents.stream().filter(itemEvent -> itemEvent.getId() == id).count())
        );
    }

    @Test
    void createDropsetSuperSetAndSubSet() {
        ArrayList<ItemEvent> itemEvents = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        var id = uuid();
        time = createEventsRefactored.createDropset(itemEvents, time, id, SUPERANDSUBSET);
        assertAll(() -> assertEquals(18, itemEvents.size()),
                () -> assertEquals(9, itemEvents.stream().filter(itemEvent -> itemEvent.getId() == id).count())
        );
    }
    @Test
    void createCombinedEvents() {
    }
}