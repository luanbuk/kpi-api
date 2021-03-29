package br.bkwblz.kpi.arch.google

interface OAuthCredentialsFactory {

    fun create(): OAuthCredentials
}