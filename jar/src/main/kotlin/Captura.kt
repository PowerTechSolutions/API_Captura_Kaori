object Captura {

    fun pegarProcesso(maquinaEscolhida: Int){

        var capturaproceso = ProcessosCaptura()
        capturaproceso.iniciar()

        var inserts = capturaproceso.inserirBanco(maquinaEscolhida)

        println("$inserts Registro(s) inseridos em processos")

    }

}