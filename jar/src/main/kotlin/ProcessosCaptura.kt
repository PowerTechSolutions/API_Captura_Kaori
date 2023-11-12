import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import org.springframework.jdbc.core.JdbcTemplate

class ProcessosCaptura {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }
    var looca: Looca = Looca()

    var processos: ProcessoGrupo = looca.grupoDeProcessos

    var processo: MutableList<Processo> = processos.processos


//    fun loop(){
//        var i = 0
//        while(true){
//            if (i < processo.size){
//                println(processo[i].pid);
//                println(processo[i].nome);
//                println(processo[i].usoMemoria);
//                println(processo[i].usoCpu);
//            }else{
//                break
//            }
//            i++
//        }
//    }

    fun inserirBanco(maquinaescolhis: Int): Int {

        var i = 0
        var inserts = 0
        while(i < processo.size){
            var pid = processo[i].pid;
            var nome = processo[i].nome;
            var usoMemoria = processo[i].usoMemoria;


        inserts += jdbcTemplate.update(
            "INSERT INTO Processos_Grupo (pid, nome, usoMemoria, FKMaquina) VALUES (?,?,?,?)",
            pid, nome, usoMemoria, maquinaescolhis
        )
            i += 1
    }
        return inserts
    }
}

//fun main() {
//    var capturaProcessos = ProcessosCaptura()
//
//    capturaProcessos.loop()
//
//}