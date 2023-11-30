import psutil
import time
import mysql.connector
import pymssql
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

try:
    # MSSQL Connection
    conn_mssql = pymssql.connect(
        server='34.194.127.191',
        user='sa',
        password='myLOVEisthe0506',
        database='PowerTechSolutions'
    )
    cursor_mssql = conn_mssql.cursor()

    # MySQL Connection
    mydb = mysql.connector.connect(
        host='localhost',
        user='root',
        password='@Icecubes123',
        database='PowerTechSolutions'
    )
    mycursor = mydb.cursor()

    # Call the function to get downtime_start and downtime_duration
    downtime_info = get_system_downtime()

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

            hours, remainder = divmod(down_time_total.seconds, 3600)
            minutes, seconds = divmod(remainder, 60)
            formatted_down_time_total = "{:02}:{:02}:{:02}".format(hours, minutes, seconds)

            print(f"Duração da Última Paralisação Menos 3 Minutos: {formatted_down_time_total}")

            mycursor.execute('''
                INSERT INTO Tempo_de_Execucao (Data_Hora, Total_captura, FKTempo_maquina)
                VALUES (%s, %s, %s)
                ''', (downtime_start, formatted_down_time_total, ${idmaquina}))

            mydb.commit()

            # MSSQL Insert
            cursor_mssql.execute(
                "INSERT INTO Tempo_de_Execucao (Data_Hora, Total_captura, FKMaquina) VALUES (%s, %s, %s)",
                (downtime_start, formatted_down_time_total, ${idmaquina})
            )
            conn_mssql.commit()

        else:
            print("A máquina está ligada há mais de 3 minutos ou está em execução.")

finally:
    cursor_mssql.close()
    conn_mssql.close()

    if mydb.is_connected():
        mycursor.close()
        mydb.close()
