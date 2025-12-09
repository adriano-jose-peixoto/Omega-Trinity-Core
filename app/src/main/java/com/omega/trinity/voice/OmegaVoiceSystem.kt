package com.omega.trinity.voice

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Locale

/**
 * SISTEMA DE VOZ OMEGA
 * Responsável por Sintetizar fala (TTS) e Reconhecer comandos (STT).
 */
class OmegaVoiceSystem(private val context: Context) {

    private var tts: TextToSpeech? = null

    // 1. INICIALIZAÇÃO DA SÍNTESE (A Boca)
    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("pt", "BR")
            }
        }
    }

    // Função para o App "Se Apresentar"
    fun announceProtocol() {
        val speech = "Sistemas Omega Online. Detectando arquitetura obsoleta. Recomendando migração imediata para Kotlin."
        tts?.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    // 2. RECONHECIMENTO DE COMANDO (Os Ouvidos)
    // Usa 'Flow' do Kotlin para transformar voz em texto em tempo real
    fun listenToOperator(): Flow<String> = callbackFlow {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Aguardando ordem, Operador...")
        }

        // Aqui, em um app real, lançaríamos a Activity de voz.
        // Simulando a resposta para demonstração de código:
        trySend("Iniciando escuta ativa...")
        
        // Simulação: Se o Operador falasse "Relatório"
        // trySend("Comando detectado: Gerar Relatório Tático")
        
        awaitClose { /* Limpeza de recursos */ }
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
