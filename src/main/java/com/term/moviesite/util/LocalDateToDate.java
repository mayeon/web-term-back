package com.term.moviesite.util;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateToDate {
	public static Date localDateToDate(LocalDate localdate) {
		return java.sql.Date.valueOf(localdate);
	}
}
