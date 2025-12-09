package com.omega.trinity.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

// ESTADOS DO NÚCLEO
enum class OmegaState { IDLE, LISTENING, SPEAKING, THINKING }

@Composable
fun OmegaCoreVisualizer(state: OmegaState) {
    // 1. Animação de "Respiração" (Pulsar)
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    
    // O raio do núcleo aumenta e diminui
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    // A rotação (para quando estiver pensando/ouvindo)
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = "rotation"
    )

    // Definição de Cores Baseada no Estado
    val primaryColor = when (state) {
        OmegaState.IDLE -> Color(0xFF00E676)     // Verde Matrix (Calmo)
        OmegaState.SPEAKING -> Color(0xFF00B0FF) // Azul Cyan (Ativo)
        OmegaState.LISTENING -> Color(0xFFFFD600)// Amarelo (Atenção)
        OmegaState.THINKING -> Color(0xFFD500F9) // Roxo (Processando)
    }

    // DESENHANDO O NÚCLEO NA TELA
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val baseRadius = size.minDimension / 3

        // Círculo Externo (Orbitando)
        if (state == OmegaState.THINKING || state == OmegaState.LISTENING) {
            drawCircle(
                color = primaryColor.copy(alpha = 0.3f),
                radius = baseRadius * 1.5f,
                style = Stroke(width = 4.dp.toPx())
            )
        }

        // O Núcleo (Alma)
        drawCircle(
            color = primaryColor.copy(alpha = 0.8f),
            radius = baseRadius * pulseScale // Ele pulsa aqui
        )
        
        // Efeito de Brilho (Glow)
        drawCircle(
            color = primaryColor.copy(alpha = 0.2f),
            radius = baseRadius * pulseScale * 1.2f
        )
    }
}
