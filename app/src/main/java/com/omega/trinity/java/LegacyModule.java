package com.omega.trinity.java;

// MÓDULO LEGADO: O Passado (Java)
// Status: Obsoleto, Verboso e Inseguro
public class LegacyModule {

    private String data;

    public void processData() {
        // Risco Clássico: Se 'data' for null, o app CRASHA aqui.
        // O Kotlin proíbe isso nativamente. O Java permite o erro.
        if (data != null) {
            System.out.println("Processando dados de forma lenta...");
        } else {
            System.out.println("ERRO CRÍTICO: NullPointer detectado.");
        }
    }

    // Boilerplate: Muito código para fazer pouca coisa
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
