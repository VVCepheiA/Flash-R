import os
import datetime
import serial

ser = serial.Serial("COM4", 9600)
time = str(datetime.datetime.now().time())[:5]
while True:
    if time != str(datetime.datetime.now().time())[:5]:
        os.system('adb pull /sdcard/test/ C:\Users/seth/Desktop')
        time = str(datetime.datetime.now().time())[:5]
        compare_time = time[:2] + time[3:]
        file = open('C:\Users/seth/Desktop/test.txt', 'r')
        string = file.read()
        if time[-1] == "5":
            ser.write('1')
        else:
            ser.write('0')
        print string
        print time