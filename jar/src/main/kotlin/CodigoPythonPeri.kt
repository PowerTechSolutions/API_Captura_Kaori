
import java.io.File
object CodigoPythonPeri {

    fun execpython(servicos: MutableList<ServicosMonitorados>) {

        val servicoCadastradoRepositorio = ServicoCadastradoRepositorio()
        servicoCadastradoRepositorio.iniciar()

        var componenteDISCO = 0

        for (servico in servicos) {

            var apelido = servicoCadastradoRepositorio.buscarComponente(servico.FKComponente_cadastrado)

            when (apelido) {
                "DISCO" -> {
                    componenteDISCO = servico.IDComponente_monitorado
                }
            }
        }

        var codigoPython ="""
import time
import psutil
import mysql.connector

disco = psutil.disk_usage('/')

try:
    mydb = mysql.connector.connect(host='localhost', user='root', password='@Icecubes123', database='PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s, %s, %s, %s, $componenteDISCO)'
        valDISCO = [disco.total, disco.used, disco.free, disco.percent]
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
"""

        val nomeArquivoPyDefault = "CodigoPythonPeriKaori.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("python $nomeArquivoPyDefault")

        println("Disco capturado")
    }
}
