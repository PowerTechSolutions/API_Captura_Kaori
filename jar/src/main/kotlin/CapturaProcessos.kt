import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import org.springframework.jdbc.core.JdbcTemplate

class CapturaProcessos{

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    var looca: Looca = Looca()

    var processos:ProcessoGrupo = looca.grupoDeProcessos

    var processo:MutableList<Processo> = processos.processos

    fun loop(){
        var i = 0
        while(true){
            if (i < processo.size){
                println(processo[i].pid);
                println(processo[i].nome);
                println(processo[i].usoMemoria);
                println(processo[i].usoCpu);
            }else{
                break
            }
            i++
        }
    }

//    fun inserirBanco(maquinaescolhis:Int):Int{
//
//        var inserts:Int = jdbcTemplate.update(
//            "INSERT INTO Redes_conectadas (Servidor_DNS,FKMaquina) VALUES (?,?)",
//            redeTrasform,maquinaescolhis
//        )
//
//        return inserts
//
//    }

}

fun main() {
    var capturaProcessos = CapturaProcessos()

    capturaProcessos.loop()

}