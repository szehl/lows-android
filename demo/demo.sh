#!/bin/bash

while true
do

echo "NOW EMERGENCY MODE!"
cd /home/zehl/demo/demo/test_scenario1/
python test-tkinter.py &
ssh root@192.168.68.20 'hostapd_cli change_ssid ^!RE^'
ssh root@192.168.68.10 'hostapd_cli change_ssid ^!RD^'
sleep 35
sudo killall -9 python

echo "NOW Physical Service Announcement "
cd /home/zehl/demo/demo/test_scenario2/
python test-tkinter.py &
ssh root@192.168.68.20 'hostapd_cli change_ssid ^?ED^'
for i in `seq 1 2`; do
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U5^'
	sleep 6
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U4^'
	sleep 6
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U3^'
	sleep 6
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U2^'
	sleep 6
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U1^'
	sleep 6 
	ssh root@192.168.68.10 'hostapd_cli change_ssid ^?U0^'
	sleep 6
done
sudo killall -9 python

echo "NOW Advertisements "
cd /home/zehl/demo/demo/test_scenario3/
python test-tkinter.py &

ssh root@192.168.68.20 'hostapd_cli change_ssid ^?sW^'
ssh root@192.168.68.10 'hostapd_cli change_ssid ^?FM^'
sleep 35
sudo killall -9 python

done
