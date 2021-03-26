package br.bkwblz.kpi.arch.dates

import java.time.LocalDate

class DateInterval (val begin: LocalDate, val end: LocalDate){
    fun contains(date: LocalDate) = begin.isBefore(date) && end.isAfter(date)
}