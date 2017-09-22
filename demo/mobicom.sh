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

#Break in 5min
execute_on_date "2017-10-17 09:50" "^CK1^^C[C^"

#Break in 3min
execute_on_date "2017-10-17 09:52" "^CK1^^C]C^"

#Break in 1min
execute_on_date "2017-10-17 09:54" "^CK1^^C}C^"

#Break 09:05 - 09:55
execute_on_date "2017-10-17 09:55" "^CC1^"

#1st Session in 5min
execute_on_date "2017-10-17 10:15" "^CC1^^C[S^"

#1st Session in 3min
execute_on_date "2017-10-17 10:17" "^CC1^^C]S^"

#1st Session in 1min
execute_on_date "2017-10-17 10:19" "^CC1^^C}S^"

#Paper session I: Wireless High Jinks  10:20 - 11:40
execute_on_date "2017-10-17 10:20" "^CS1^"

#Talk 1
execute_on_date "2017-10-17 10:20" "^CS1^^CT1^"

#Talk 2
execute_on_date "2017-10-17 10:40" "^CS1^^CT2^"

#Talk 3
execute_on_date "2017-10-17 11:00" "^CS1^^CT3^"

#Talk 4
execute_on_date "2017-10-17 11:20" "^CS1^^CT4^"

#Lunch in 5min
execute_on_date "2017-10-17 11:35" "^CS1^^CT4^^C[L^"

#Lunch in 3min
execute_on_date "2017-10-17 11:37" "^CS1^^CT4^^C]L^"

#Lunch in 1min
execute_on_date "2017-10-17 11:39" "^CS1^^CT4^^C}L^"

#Lunch 11:40 - 13:10
execute_on_date "2017-10-17 11:40" "^CL1^"

#N2Women in 5min
execute_on_date "2017-10-17 11:55" "^CL1^^C[N^"

#N2Women in 3min
execute_on_date "2017-10-17 11:57" "^CL1^^C]N^"

#N2Women in 1min
execute_on_date "2017-10-17 11:59" "^CL1^^C}N^"

#N2Women 12:00 - 13:00 + Lunch
execute_on_date "2017-10-17 12:00" "^CN1^^CL1^"

#Only Lunch till 13:10
execute_on_date "2017-10-17 13:00" "^CL1^"

#Session 2 in 5min
execute_on_date "2017-10-17 13:05" "^CL1^^C[T^"

#Session 2 in 3min
execute_on_date "2017-10-17 13:07" "^CL1^^C]T^"

#Session 2 in 1min
execute_on_date "2017-10-17 13:09" "^CL1^^C}T^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 5
execute_on_date "2017-10-17 13:10" "^CS2^^CT5^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 6
execute_on_date "2017-10-17 13:30" "^CS2^^CT6^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 7
execute_on_date "2017-10-17 13:50" "^CS2^^CT7^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 8
execute_on_date "2017-10-17 14:10" "^CS2^^CT8^"

#Break in 5min
execute_on_date "2017-10-17 14:25" "^CS2^^CT8^^C[C^"

#Break in 3min
execute_on_date "2017-10-17 14:27" "^CS2^^CT8^^C]C^"

#Break in 1min
execute_on_date "2017-10-17 14:29" "^CS2^^CT8^^C}C^"

#Break2 14:30 - 14:55
execute_on_date "2017-10-17 14:30" "^CC2^"

#Session 3 in 5min
execute_on_date "2017-10-17 14:50" "^CC2^^C[U^"

#Session 3 in 3min
execute_on_date "2017-10-17 14:52" "^CC2^^C]U^"

#Session 2 in 1min
execute_on_date "2017-10-17 14:54" "^CC2^^C}U^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 9 
execute_on_date "2017-10-17 14:55" "^CS3^^CT9^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 10 
execute_on_date "2017-10-17 15:15" "^CS3^^CTA^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 11
execute_on_date "2017-10-17 15:35" "^CS3^^CTB^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 12
execute_on_date "2017-10-17 15:55" "^CS3^^CTC^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 13
execute_on_date "2017-10-17 16:15" "^CS3^^CTD^"


#AppContest in 5min
execute_on_date "2017-10-17 16:30" "^CS3^^CTD^^C[A^"

#App Contest in 3min
execute_on_date "2017-10-17 16:32" "^CS3^^CTD^^C]A^"

#App Contest in 1min
execute_on_date "2017-10-17 16:34" "^CS3^^CTD^^C}A^"

#App Contest 16:35-17:35
execute_on_date "2017-10-17 16:35" "^CX2^"

#Demo Session in 5min
execute_on_date "2017-10-17 17:30" "^CX2^^C[D^"

#Demo Session in 3min
execute_on_date "2017-10-17 17:32" "^CX2^^C]D^"

#Demo Session in 1min
execute_on_date "2017-10-17 17:34" "^CX2^^C}D^"

#Demo Session 17:35 - 19:15
execute_on_date "2017-10-17 17:35" "^CD1^"


#MobiJob Starting in 5min
execute_on_date "2017-10-17 19:15" "^C[J^"

#MobiJob Starting in 3min
execute_on_date "2017-10-17 19:17" "^C]J^"

#Mobi Job Starting in 1min
execute_on_date "2017-10-17 19:19" "^C}J^"

#MobiJob dinner 19:20-21:20
execute_on_date "2017-10-17 19:20" "^CX3^"

#EOB
execute_on_date "2017-10-17 21:20" "LoWS"







 


