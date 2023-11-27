
import time
import psutil
import mysql.connector

disco = psutil.disk_usage('/')

try:
    mydb = mysql.connector.connect(host='localhost', user='root', password='@Icecubes123', database='PowerTechSolutions')
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
