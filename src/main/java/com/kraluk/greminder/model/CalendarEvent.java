package com.kraluk.greminder.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Simple Calendar Event model
 *
 * @author lukasz
 */
@Getter
@Setter
public class CalendarEvent implements Serializable {
    private static final long serialVersionUID = 5259056767867235021L;

    private String attendee;
    private String phoneNumber;
    private String email;
    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
