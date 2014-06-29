import os
import datetime
import serial

ser = serial.Serial("COM4", 9600)
time = "0"
while True:
    if time != str(datetime.datetime.now().time())[:5]:
        #os.system('adb pull //data/data/com.example.flashr/databases/eventManager C:\Users/seth/Desktop')

        time = str(datetime.datetime.now().time())[:5]
        compare_time = time[:2] + time[3:]
        #file = open('C:\Users/seth/Desktop/eventManager', 'r')
        #string = file.read()
        ser.write('1')
        #print string
        print time
