package br.bkwblz.kpi.arch.utils

import java.time.LocalDate

class DateInterval (val begin: LocalDate, val end: LocalDate){
    fun contains(date: LocalDate) = begin.isBefore(date) && end.isAfter(date)
}