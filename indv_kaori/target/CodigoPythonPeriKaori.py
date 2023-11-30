import time
import psutil
import mysql.connector
import pyodbc

disco = psutil.disk_usage('/')

try:
    conn = pyodbc.connect(
        'Driver=ODBC Driver 17 for SQL Server;'
        'Server=ec2-34-194-127-191.compute-1.amazonaws.com;'
        'Database=PowerTechSolutions;'
        'UID=sa;'
        'PWD=myLOVEisthe0506'
    )
    cursor = conn.cursor()
    sql_querryDISCO = f'INSERT INTO Monitoramento_RAW (Total, Free, Uso, Porcentagem_Uso, FKComponente_Monitorado) VALUES ({disco.total},{disco.free},{disco.used},{disco.percent},3)'
    cursor.execute(sql_querryDISCO)
    conn.commit()
    
finally:
    cursor.close()
    conn.close()

try:
    mydb = mysql.connector.connect(host='localhost:3306', user='root', password='@Icecubes123', database='PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s, %s, %s, %s, 3)'
        valDISCO = [disco.total, disco.used, disco.free, disco.percent]
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
