import psutil
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

finally:
    # MSSQL Cleanup
    cursor_mssql.close()
    conn_mssql.close()