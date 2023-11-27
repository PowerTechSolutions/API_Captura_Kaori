import java.io.File

object CodigoPythonConst {
    fun execpythonKaori(idmaquina: Int) {

        val codigoPython = """
import psutil
import mysql.connector
import time
from datetime import datetime, timedelta

def get_system_downtime():
    # Obtém informações sobre o tempo de inicialização do sistema e o tempo total de atividade
    boot_time = psutil.boot_time()
    current_time = time.time()

    # Calcula o tempo de paralisação
    downtime_start = datetime.fromtimestamp(boot_time)
    downtime_end = datetime.fromtimestamp(current_time)
    downtime_duration = downtime_end - downtime_start

    # Verifica se a máquina está ligada há menos de 3 minutos
    if downtime_duration.total_seconds() > 180:  # 180 segundos = 3 minutos
        return None
    else:
        return downtime_start, downtime_duration

# Obtém informações sobre o tempo de paralisação e tempo total de atividade
downtime_info = get_system_downtime()

try:
    conn = pyodbc.connect(
        'Driver=ODBC Driver 17 for SQL Server;'
        'Server=ec2-34-194-127-191.compute-1.amazonaws.com;'
        'Database=PowerTechSolutions;'
        'UID=sa;'
        'PWD=myLOVEisthe0506'
    )
    cursor = conn.cursor()
    sql_querryTempoExec = f'INSERT INTO Tempo_de_Execucao (Data_Hora, Total_captura, FKTempo_maquina) VALUES ({downtime_start},{down_time_total},$idmaquina)'
    cursor.execute(sql_querryTempoExec)
    conn.commit()
finally:
    cursor.close()
    conn.close()

try:
    mydb = mysql.connector.connect(host='localhost', user='root', password='@Icecubes123', database='PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()

        # Verifica se a máquina está ligada antes de imprimir os resultados
        if downtime_info is not None:
            downtime_start, downtime_duration = downtime_info
            down_time_total = downtime_duration - timedelta(minutes=3)
            print(f"Data e Hora de Início da Última Paralisação: {downtime_start}")
            print(f"Duração da Última Paralisação: {downtime_duration}")
            print(f"Duração da Última Paralisação Menos 3 Minutos: {down_time_total}")

            mycursor.execute('''
                INSERT INTO Tempo_de_Execucao (Data_Hora, Total_captura, FKTempo_maquina)
                VALUES (%s, %s, ${idmaquina})
                ''', (downtime_start, down_time_total))

            mydb.commit()
        else:
            print("A máquina está ligada há mais de 3 minutos ou está em execução.")
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
"""

        val nomeArquivoPyDefault = "CodigoPythonConstKaori.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("python $nomeArquivoPyDefault")

        println("Python executado para Tempo de execução")
    }
}
