package com.omega.trinity.security

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * MÓDULO DE SEGURANÇA (AIRLOCK)
 * Nada sai do dispositivo sem passar por esta limpeza.
 */
object SecurityAirlock {

    // Lista negra de termos que JAMAIS devem sair da empresa
    private val sensitivePatterns = listOf(
        Regex("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b"), // Emails
        Regex("API_KEY_[A-Z0-9]+"), // Chaves de API
        Regex("(?i)senha|password|secret"), // Palavras de perigo
        Regex("Projeto_X_Confidencial") // Nomes de projetos secretos
    )

    fun sanitizePayload(input: String): String {
        var cleanData = input
        
        // Substitui dados sensíveis por [REDACTED]
        sensitivePatterns.forEach { regex ->
            if (regex.containsMatchIn(input)) {
                cleanData = cleanData.replace(regex, "[CLASSIFIED_DATA]")
            }
        }
        
        return cleanData
    }

    // Simula a verificação de segurança antes do envio
    fun scanForLeaks(data: String): Flow<Boolean> = flow {
        // Se detectar algo que não conseguiu limpar, bloqueia o envio
        if (data.contains("SENHA_MESTRA_DO_BANCO")) {
            emit(false) // ABORTAR MISSÃO
        } else {
            emit(true) // SEGURO PARA ENVIO
        }
    }
}
