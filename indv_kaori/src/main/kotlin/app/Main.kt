package app

import CodigoPythonConst
import CodigoPythonPeri
import MaquinasRepositorio
import Monitoramento_RAWRepositorio
import ServicoCadastradoRepositorio
import ServicoMonitoradoRepositorio
import ServicosMonitorados
import Usuario
import UsuarioRepositorio
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            var dataAtual = LocalDateTime.now()

            val usuario_repositorio = UsuarioRepositorio()
            val maquina_repositorio = MaquinasRepositorio()
            val servicoMonitoradorepositorio = ServicoMonitoradoRepositorio()
            val servicoCadastradorepositorio = ServicoCadastradoRepositorio()

            servicoCadastradorepositorio.iniciarSql()
            servicoMonitoradorepositorio.iniciarSql()
            maquina_repositorio.iniciarSql()
            usuario_repositorio.iniciarSql()

            val sn = Scanner(System.`in`)

            println("Bem vindo a PowerTech Por favor realize o login para utilizar nosso sistema")

            println("Insira seu Cpf: ")
            var Cpf:String = sn.next()

            if (usuario_repositorio.autenticar(Cpf)){

                var funcionario:Usuario = usuario_repositorio.resgatarinfo(Cpf)

                var maquinas:String = maquina_repositorio.pegarMaquinas(funcionario.IDUsuario)
                println("Qual a numeração da maquina e está que está instalando o serviço? $maquinas")

                var maquinaEscolhida = sn.next().toInt()

                var servicos:MutableList<ServicosMonitorados> = servicoMonitoradorepositorio.buscarComponentes(maquinaEscolhida)

                var funcoes:MutableList<String> = mutableListOf()

                for (servico in servicos){

                    var apelido:String = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)

                    when(apelido){

                        "DISCO" -> funcoes.add("D")
                        else -> {
                            funcoes.add("CR")
                        }

                    }

                }

                Captura(funcoes,servicos,maquinaEscolhida,dataAtual)

            }

        }

        fun Captura(funcoes:MutableList<String>,servicos: MutableList<ServicosMonitorados>, maquinaEscolhida: Int, dataAtual: LocalDateTime){

            var capturaDISCO = 0

            for (servico in funcoes){
                when(servico){
                    "D" -> capturaDISCO = 1
                }
            }

            var repositorio = Monitoramento_RAWRepositorio()
            repositorio.iniciar()

            var dataDisco = LocalDateTime.now()

            var verificacaoDiaria:Int = 0
            var verificacaoSemanal:Int = 0
            var dia_semana:Int = 1

            var verificacaoDisco = false

            println("Captura Iniciada, para para o processo use [ctrl+c]")

            while(true){

                if (capturaDISCO == 1){
                    if(verificacaoSemanal == 0){
                        if (verificacaoDisco){
                            dataDisco = repositorio.buscarData(maquinaEscolhida,"DISCO")
                            if (dataDisco.dayOfWeek == dataAtual.dayOfWeek){
                                CodigoPythonPeri.execpython(servicos)
                            }
                        }else{
                            CodigoPythonPeri.execpython(servicos)
                            verificacaoDisco = true
                        }
                        verificacaoSemanal += 1
                    }
                }

                var dataAtual = LocalTime.now()

                if (dataAtual.equals("23:59:59")){
                    verificacaoDiaria = 0
                    dia_semana += 1
                }

                if (dia_semana == 7){
                    verificacaoSemanal = 0
                    dia_semana = 1
                }

                CodigoPythonConst.execpythonKaori(maquinaEscolhida)

                Thread.sleep(5000)
            }

        }
    }
}






//package app
//
//import Captura
//import CodigoPythonConst
//import Conexao
//import MaquinasRepositorio
//import Monitoramento_RAWRepositorio
//import ServicoCadastradoRepositorio
//import ServicoMonitoradoRepositorio
//import ServicosMonitorados
//import Usuario
//import UsuarioRepositorio
//import java.time.LocalDateTime
//import java.time.LocalTime
//import java.util.*
//import javax.swing.JOptionPane
//
//open class Main {
//    companion object {
//        @JvmStatic fun main(args: Array<String>) {
//
//            var dataAtual = LocalDateTime.now()
//
//            val usuario_repositorio = UsuarioRepositorio()
//            val maquina_repositorio = MaquinasRepositorio()
//            val servicoMonitoradorepositorio = ServicoMonitoradoRepositorio()
//            val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
//
//            servicoCadastradorepositorio.iniciar()
//            servicoMonitoradorepositorio.iniciar()
//            maquina_repositorio.iniciar()
//            usuario_repositorio.iniciar()
//
//            val sn = Scanner(System.`in`)
//
//            println("Bem vindo a PowerTech Por favor realize o login para utilizar nosso sistema")
//
//            println("Insira seu Cpf: ")
//            var Cpf:String = sn.next()
//
//            if (usuario_repositorio.autenticar(Cpf)){
//
//                var funcionario:Usuario = usuario_repositorio.resgatarinfo(Cpf)
//
//                var maquinas:String = maquina_repositorio.pegarMaquinas(funcionario.IDUsuario)
//                println("Qual a numeração da maquina e está que está instalando o serviço? $maquinas")
//
//                var maquinaEscolhida = sn.next().toInt()
//
//                CodigoPythonConst.execpythonKaori(maquinaEscolhida)
//
//                var servicos:MutableList<ServicosMonitorados> = servicoMonitoradorepositorio.buscarComponentes(maquinaEscolhida)
//
//                var funcoes:MutableList<String> = mutableListOf()
//
//                for (servico in servicos){
//
//                    var apelido:String = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)
//
//                    when(apelido){
//
//                        "DISCO" -> funcoes.add("D")
//                        else -> {
//                            funcoes.add("CR")
//                        }
//
//                    }
//
//                }
//
//                Captura(funcoes,servicos,maquinaEscolhida,dataAtual)
//
//            }
//
//        }
//
//        fun Captura(funcoes:MutableList<String>,servicos: MutableList<ServicosMonitorados>, maquinaEscolhida: Int,
//                    dataAtual: LocalDateTime){
//
//
//            var capturaDISCO = 0
//
//            for (servico in funcoes){
//                when(servico){
//                    "D" -> capturaDISCO = 1
//                }
//            }
//
//            var repositorio = Monitoramento_RAWRepositorio()
//            repositorio.iniciar()
//
//            var captura = Captura
//
//            var dataDisco = LocalDateTime.now()
//
//            var verificacaoDiaria:Int = 0
//            var verificacaoSemanal:Int = 0
//            var dia_semana:Int = 1
//
//            var verificacaoDisco = false
//
//            while(true){
//
//                if (capturaDISCO == 1){
//                    if(verificacaoSemanal == 0){
//                        if (verificacaoDisco){
//                            dataDisco = repositorio.buscarData(maquinaEscolhida,"DISCO")
//                            if (dataDisco.dayOfWeek == dataAtual.dayOfWeek){
//                                CodigoPythonPeri.execpython(servicos)
//                            }
//                        }else{
//                            CodigoPythonPeri.execpython(servicos)
//                            verificacaoDisco = true
//                        }
//                        verificacaoSemanal += 1
//                    }
//                }
//
//                var dataAtual = LocalTime.now()
//
//                if (dataAtual.equals("23:59:59")){
//                    verificacaoDiaria = 0
//                    dia_semana += 1
//                }
//
//                if (dia_semana == 7){
//                    verificacaoSemanal = 0
//                    dia_semana = 1
//                }
//
//                CodigoPythonConst.execpythonKaori(maquinaEscolhida)
//
//                Thread.sleep(5000)
//
//
//            }
//
//        }
//    }
//}