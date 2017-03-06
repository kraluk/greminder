package com.kraluk.greminder.calendar.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Simple Calendar Event model used in the application
 *
 * @author lukasz
 */
@Data
public class CalendarEvent implements Serializable {
    private static final long serialVersionUID = 5259056767867235021L;

    private String attendee;
    private String leader;
    private String phoneNumber;
    private String email;
    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
