#!/bin/bash
echo "MOBICOM 2017 LoWS Demo Script"
echo "#############################"
echo "-----------------------------"
interface="wlan1"
gcmd="hostapd_cli -p /var/run/hostapd -i $interface change_ssid "
execute_on_date(){
        echo "New Command received:"
	local execdate="$1"
	local cmd="$gcmd $2"
	#echo $execdate
	#echo $cmd
	echo "NOW Time: $(date +%Y-%m-%d" "%H:%M" "%Z)"
	echo "CMD Time: $execdate"
	if (( $(( $(date --date="$execdate" +%s) - $(date +%s) )) > 0 )) ;
	then
		echo Next Execution Step on: $(date --date="$execdate").
		echo "Command to be executed: "$cmd
		sleep $(( $(date --date="$execdate" +%s) - $(date +%s) ));
		echo "Now executing..."
		eval $cmd
	else
		echo "Next Execution step is in the past, skipping that..."
	fi
	echo "#############################"
	echo "-----------------------------"

}

#ed="2017-09-21 04:07"
#com="^?T1^"
#execute_on_date "$ed" "$com"

execute_on_date "2017-09-21 07:30" "^CR1^"


#Registration and Breakfast 07:30-08:40
execute_on_date "2017-10-17 07:30" "^CR1^"

#Inform about next event 08:40 - 09:05 	Welcome & Awards in 5min at 08:35
execute_on_date "2017-10-17 08:35" "^CR1^^C[W^"

#Inform about next event 08:40 - 09:05  Welcome & Awards in 3min at 08:37
execute_on_date "2017-10-17 08:37" "^CR1^^C]W^"

#Inform about next event 08:40 - 09:05  Welcome & Awards in 1min at 08:39
execute_on_date "2017-10-17 08:39" "^CR1^^C}W^"

#Welcome & Awards 08:40 - 09:05
execute_on_date "2017-10-17 08:40" "^CXW^"

#Keynote1 in 5min
execute_on_date "2017-10-17 09:00" "^CXW^^C[K^"

#Keynote1 in 3min
execute_on_date "2017-10-17 09:02" "^CXW^^C]K^"

#Keynote1 in 1min
execute_on_date "2017-10-17 09:04" "^CXW^^C}K^"

#Keynote1 09:05 - 09:55
execute_on_date "2017-10-17 09:05" "^CK1^"

 

