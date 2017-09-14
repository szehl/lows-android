#!/bin/bash


echo "Stopping Demo"
ssh root@192.168.68.10 'hostapd_cli change_ssid -'
ssh root@192.168.68.20 'hostapd_cli change_ssid -'
sudo killall -9 python
