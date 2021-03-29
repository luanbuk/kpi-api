package br.bkwblz.kpi.goals.users.current

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*


@Qualifier
@Target(CLASS, FIELD, TYPE, CONSTRUCTOR)
@Retention(RUNTIME)
@MustBeDocumented
annotation class Current