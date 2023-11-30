
import java.io.File
object CodigoPythonPeri {

    fun execpython(servicos: MutableList<ServicosMonitorados>) {

        val servicoCadastradoRepositorio = ServicoCadastradoRepositorio()
        servicoCadastradoRepositorio.iniciarSql()

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
import pymssql

disco = psutil.disk_usage('/')

try:
    # MSSQL Connection
    conn_mssql = pymssql.connect(
        server='34.194.127.191',
        user='sa',
        password='myLOVEisthe0506',
        database='PowerTechSolutions'
    )
    cursor_mssql = conn_mssql.cursor()

    cursor_mssql.execute(
        "INSERT INTO Monitoramento_RAW (Total, Free, Uso, Porcentagem, FKComponente_Monitorado) VALUES (%s, %s, %s, %s, %s)",
        (disco.total, disco.free, disco.used, disco.percent, ${componenteDISCO})  # Replace 1 with the actual value for FKComponente_Monitorado
    )
    conn_mssql.commit()

    # MySQL Connection
    mydb = mysql.connector.connect(
        host='localhost',
        user='root',
        password='@Icecubes123',
        database='PowerTechSolutions'
    )
    mycursor = mydb.cursor()

    if mydb.is_connected():
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW (Total, Free, Uso, Porcentagem, FKComponente_Monitorado) VALUES ( %s, %s, %s, %s, %s)'
        valDISCO = [disco.total, disco.used, disco.free, disco.percent, ${componenteDISCO}]  # Replace 1 with the actual value for FKComponente_Monitorado
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()

finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
"""

        val nomeArquivoPyDefault = "CodigoPythonPeriKaori.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("python3 $nomeArquivoPyDefault")

        println("Disco capturado")
    }
}
