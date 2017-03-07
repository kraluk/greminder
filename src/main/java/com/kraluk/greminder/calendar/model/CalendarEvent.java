package com.kraluk.greminder.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Simple Calendar Event model used in the application
 *
 * @author lukasz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarEvent implements Serializable {
    private static final long serialVersionUID = 5259056767867235021L;

    private String leader;
    private String phoneNumber;
    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
