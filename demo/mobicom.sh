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

#execute_on_date "2017-09-21 07:30" "^CR1^"

echo "###################################"
echo "##########DAY1!!!##################"
echo "###################################"

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

#Break   09:55 - 10:20
execute_on_date "2017-10-17 09:55" "^CC1^"

#1st Session in 5min
execute_on_date "2017-10-17 10:15" "^CC1^^C[S^"

#1st Session in 3min
execute_on_date "2017-10-17 10:17" "^CC1^^C]S^"

#1st Session in 1min
execute_on_date "2017-10-17 10:19" "^CC1^^C}S^"

#Paper session I: Wireless High Jinks  10:20 - 11:40
#execute_on_date "2017-10-17 10:20" "^CS1^"

#Talk 1
execute_on_date "2017-10-17 10:20" "^CS1^^Ct1^"

#Talk 2
execute_on_date "2017-10-17 10:40" "^CS1^^Ct2^"

#Talk 3
execute_on_date "2017-10-17 11:00" "^CS1^^Ct3^"

#Talk 4
execute_on_date "2017-10-17 11:20" "^CS1^^Ct4^"

#Lunch in 5min
execute_on_date "2017-10-17 11:35" "^CS1^^Ct4^^C[L^"

#Lunch in 3min
execute_on_date "2017-10-17 11:37" "^CS1^^Ct4^^C]L^"

#Lunch in 1min
execute_on_date "2017-10-17 11:39" "^CS1^^Ct4^^C}L^"

#Lunch 11:40 - 13:10
execute_on_date "2017-10-17 11:40" "^CL1^"

#N2Women in 5min
execute_on_date "2017-10-17 11:55" "^CL1^^C[N^"

#N2Women in 3min
execute_on_date "2017-10-17 11:57" "^CL1^^C]N^"

#N2Women in 1min
execute_on_date "2017-10-17 11:59" "^CL1^^C}N^"

#N2Women 12:00 - 13:00 + Lunch
execute_on_date "2017-10-17 12:00" "^CXN^"

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
execute_on_date "2017-10-17 13:10" "^CS2^^Ct5^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 6
execute_on_date "2017-10-17 13:30" "^CS2^^Ct6^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 7
execute_on_date "2017-10-17 13:50" "^CS2^^Ct7^"

#Paper session II: Can You Hear Me Now? 13:10 - 14:30
#+talk 8
execute_on_date "2017-10-17 14:10" "^CS2^^Ct8^"

#Break in 5min
execute_on_date "2017-10-17 14:25" "^CS2^^Ct8^^C[C^"

#Break in 3min
execute_on_date "2017-10-17 14:27" "^CS2^^Ct8^^C]C^"

#Break in 1min
execute_on_date "2017-10-17 14:29" "^CS2^^Ct8^^C}C^"

#Break2 14:30 - 14:55
execute_on_date "2017-10-17 14:30" "^CC2^"

#Session 3 in 5min
execute_on_date "2017-10-17 14:50" "^CC2^^C[U^"

#Session 3 in 3min
execute_on_date "2017-10-17 14:52" "^CC2^^C]U^"

#Session 3 in 1min
execute_on_date "2017-10-17 14:54" "^CC2^^C}U^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 9 
execute_on_date "2017-10-17 14:55" "^CS3^^Ct9^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 10 
execute_on_date "2017-10-17 15:15" "^CS3^^CtA^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 11
execute_on_date "2017-10-17 15:35" "^CS3^^CtB^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 12
execute_on_date "2017-10-17 15:55" "^CS3^^CtC^"

#Paper session II: Paper session III: Invisible Cobwebs 14:55 - 16:35
#+talk 13
execute_on_date "2017-10-17 16:15" "^CS3^^CtD^"

#AppContest in 5min
execute_on_date "2017-10-17 16:30" "^CS3^^CtD^^C[A^"

#App Contest in 3min
execute_on_date "2017-10-17 16:32" "^CS3^^CtD^^C]A^"

#App Contest in 1min
execute_on_date "2017-10-17 16:34" "^CS3^^CtD^^C}A^"

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




echo "###################################"
echo "##########DAY2!!!##################"
echo "###################################"

#Registration and Breakfast 07:30-08:45
execute_on_date "2017-10-18 07:30" "^CR2^"

#4rd Session in 5min
execute_on_date "2017-10-18 08:40" "^CR2^^C[V^"

#4rd Session in 3min
execute_on_date "2017-10-18 08:42" "^CR2^^C]V^"

#4rd Session in 1min
execute_on_date "2017-10-18 08:44" "^CR2^^C}V^"

#Paper session IV: 
#execute_on_date "2017-10-18 08:45" "^CS4^"

#Talk E
execute_on_date "2017-10-18 08:45" "^CS4^^CtE^"

#Talk F
execute_on_date "2017-10-18 09:05" "^CS4^^CtF^"

#Talk G
execute_on_date "2017-10-18 09:25" "^CS4^^CtG^"

#Rock star presentation: in 5min
execute_on_date "2017-10-18 09:40" "^CS4^^CtG^^C[R^"

#Rock star presentation: in 3min
execute_on_date "2017-10-18 09:42" "^CS4^^CtG^^C]R^"

#Rock star presentation: in 1min
execute_on_date "2017-10-18 09:44" "^CS4^^CtG^^C}R^"

#Rock star presentation: 09:45 - 10:35
execute_on_date "2017-10-18 09:45" "^CX4^"

#Break in 5min
execute_on_date "2017-10-18 10:30" "^CX4^^C[C^"

#Break in 3min
execute_on_date "2017-10-18 10:32" "^CX4^^C]C^"

#Break in 1min
execute_on_date "2017-10-18 10:34" "^CX4^^C}C^"

#Break3 10:35 - 11:00
execute_on_date "2017-10-18 10:35" "^CC3^"

#Second round of SRC (65 min) in 5min
execute_on_date "2017-10-18 10:55" "^CC3^^C[Z^"

#Second round of SRC (65 min) in 3min
execute_on_date "2017-10-18 10:57" "^CC3^^C]Z^"

#Second round of SRC  (65 min) 11:00 - 12:05 in 1min
execute_on_date "2017-10-18 10:59" "^CC3^^C}Z^"

#Second round of SRC  (65 min)11:00 - 12:05 
execute_on_date "2017-10-18 11:00" "^CX5^"

#Lunch in 5min
execute_on_date "2017-10-18 12:00" "^CX5^^C[L^"

#Lunch in 3min
execute_on_date "2017-10-18 12:02" "^CX5^^C]L^"

#Lunch in 1min
execute_on_date "2017-10-18 12:04" "^CX5^^C}L^"

#Lunch 12:05 - 13:35
execute_on_date "2017-10-18 12:05" "^CL2^"

#Session 5 in 5min
execute_on_date "2017-10-18 13:30" "^CL2^^C[a^"

#Session 2 in 3min
execute_on_date "2017-10-18 13:32" "^CL2^^C]a^"

#Session 2 in 1min
execute_on_date "2017-10-18 13:34" "^CL2^^C}a^"

#Paper session V: Location! Location! Location! 13:35 - 14:55
#+talk H
execute_on_date "2017-10-18 13:35" "^CS5^^CtH^"

#Paper session V: Location! Location! Location! 13:35 - 14:55
#+talk I
execute_on_date "2017-10-18 13:55" "^CS5^^CtI^"

#Paper session V: Location! Location! Location! 13:35 - 14:55
#+talk J
execute_on_date "2017-10-18 14:15" "^CS5^^CtJ^"

#Paper session V: Location! Location! Location! 13:35 - 14:55
#+talk K
execute_on_date "2017-10-18 14:35" "^CS5^^CtK^"

#Break in 5min
execute_on_date "2017-10-18 14:50" "^CS5^^CtK^^C[C^"

#Break in 3min
execute_on_date "2017-10-18 14:52" "^CS5^^CtK^^C]C^"

#Break in 1min
execute_on_date "2017-10-18 14:54" "^CS5^^CtK^^C}C^"

#Break4 14:55 - 15:15
execute_on_date "2017-10-18 14:55" "^CC4^"


#Test of Time Award in 5min
execute_on_date "2017-10-18 15:10" "^CC4^^C[t^"

#Test of Time Award in  in 3min
execute_on_date "2017-10-18 15:12" "^CC4^^C]t^"

#Test of Time Award in  in 1min
execute_on_date "2017-10-18 15:14" "^CC4^^C}t^"

#Test of Time Award 15:15 - 15:30
execute_on_date "2017-10-18 15:15" "^CXT^"





#Session 6 in 5min
execute_on_date "2017-10-18 15:25" "^CXT^^C[b^"

#Session 6 in 3min
execute_on_date "2017-10-18 15:27" "^CXT^^C]b^"

#Session 6 in 1min
execute_on_date "2017-10-18 15:29" "^CXT^^C}b^"

#15:30 - 16:50 	Paper session VI: Tag, You’re It!
#+talk L
execute_on_date "2017-10-18 15:30" "^CS6^^CtL^"

#15:15 - 16:35 	Paper session VI: Tag, You’re It!
#+talk M
execute_on_date "2017-10-18 15:50" "^CS6^^CtM^"

#15:15 - 16:35 	Paper session VI: Tag, You’re It!
#+talk N
execute_on_date "2017-10-18 16:10" "^CS6^^CtN^"

#15:15 - 16:35 	Paper session VI: Tag, You’re It!
#+talk O
execute_on_date "2017-10-18 16:30" "^CS6^^CtO^"

execute_on_date "2017-10-18 16:50" "LoWS"

#Tram rides to Hidden Peak in 5min
execute_on_date "2017-10-18 17:10" "^C[c^"

#Tram rides to Hidden Peak in 3min
execute_on_date "2017-10-18 17:12" "^C]c^"

#Tram rides to Hidden Peak in 1min
execute_on_date "2017-10-18 17:14" "^C}c^"

#Tram rides to Hidden Peak 17:15 - 18:00
execute_on_date "2017-10-18 17:15" "^CX6^"

#Banquet 18-21:00
execute_on_date "2017-10-18 18:00" "^CX7^"

#EOB
execute_on_date "2017-10-18 21:00" "LoWS"






echo "###################################"
echo "##########DAY3!!!##################"
echo "###################################"

#Registration and Breakfast 07:30-08:45
execute_on_date "2017-10-19 07:30" "^CR2^"

# SIGMobile business meetingin 5min
execute_on_date "2017-10-19 08:40" "^CR2^^C[d^"

# 	SIGMobile business meeting in 3min
execute_on_date "2017-10-19 08:42" "^CR2^^C]d^"

#4 	SIGMobile business meeting in 1min
execute_on_date "2017-10-19 08:44" "^CR2^^C}d^"

# 	SIGMobile business meeting: 
execute_on_date "2017-10-19 08:45" "^CX8^"

#Session 7 in 5min
execute_on_date "2017-10-19 09:40" "^CX8^^C[e^"

#Session 7 in 3min
execute_on_date "2017-10-19 09:42" "^CX8^^C]e^"

#Session 7 in 1min
execute_on_date "2017-10-19 09:44" "^CX8^^C}e^"

#Paper session VII: Leaks, Plugs, Alice and Bob   09:45 - 11:05
#+talk P
execute_on_date "2017-10-19 09:45" "^CS7^^CtP^"

#Paper session VII: Leaks, Plugs, Alice and Bob   09:45 - 11:05
#+talk Q
execute_on_date "2017-10-19 10:05" "^CS7^^CtQ^"

#Paper session VII: Leaks, Plugs, Alice and Bob   09:45 - 11:05
#+talk R
execute_on_date "2017-10-19 10:25" "^CS7^^CtR^"

#Paper session VII: Leaks, Plugs, Alice and Bob   09:45 - 11:05
#+talk S
execute_on_date "2017-10-19 10:45" "^CS7^^CtS^"

#Break in 5min
execute_on_date "2017-10-19 11:00" "^CS7^^CtS^^C[C^"

#Break in 3min
execute_on_date "2017-10-19 11:02" "^CS7^^CtS^^C]C^"

#Break in 1min
execute_on_date "2017-10-19 11:04" "^CS7^^CtS^^C}C^"

#Break5 11:05 - 11:30
execute_on_date "2017-10-19 11:05" "^CC5^"

#Keynote2 in 5min
execute_on_date "2017-10-19 11:25" "^CC5^^C[B^"

#Keynote2 in 3min
execute_on_date "2017-10-19 11:27" "^CC5^^C]B^"

#Keynote2 in 1min
execute_on_date "2017-10-19 11:29" "^CC5^^C}B^"

#Keynote2 11:30 - 12:20
execute_on_date "2017-10-19 11:30" "^CK2^"

#Lunch in 5min
execute_on_date "2017-10-19 12:15" "^CK2^^C[L^"

#Lunch in 3min
execute_on_date "2017-10-19 12:17" "^CK2^^C]L^"

#Lunch in 1min
execute_on_date "2017-10-19 12:19" "^CK2^^C}L^"

#Lunch  12:20 - 13:50
execute_on_date "2017-10-19 12:20" "^CL3^"

#Session 8 in 5min
execute_on_date "2017-10-19 13:45" "^CL3^^C[f^"

#Session 8 in 3min
execute_on_date "2017-10-19 13:47" "^CL3^^C]f^"

#Session 8 in 1min
execute_on_date "2017-10-19 13:49" "^CL3^^C}f^"

#Paper session VIII: Frameworks and Such 13:50 - 15:10
#+talk T
execute_on_date "2017-10-19 13:50" "^CS8^^CtT^"

#Paper session VIII: Frameworks and Such 13:50 - 15:10
#+talk U
execute_on_date "2017-10-19 14:10" "^CS8^^CtU^"

#Paper session VIII: Frameworks and Such 13:50 - 15:10
#+talk V
execute_on_date "2017-10-19 14:30" "^CS8^^CtV^"

#Paper session VIII: Frameworks and Such 13:50 - 15:10
#+talk W
execute_on_date "2017-10-19 14:50" "^CS8^^CtW^"

#Panel in 5min
execute_on_date "2017-10-19 15:05" "^CS8^^CtW^^C[g^"

#Panel in 3min
execute_on_date "2017-10-19 15:07" "^CS8^^CtW^^C]g^"

#Panel in 1min
execute_on_date "2017-10-19 15:09" "^CS8^^CtW^^C}g^"

#Panel 15:10 - 16:10
execute_on_date "2017-10-19 15:10" "^CP1^"

#Break in 5min
execute_on_date "2017-10-19 16:05" "^CP1^^C[C^"

#Break in 3min
execute_on_date "2017-10-19 16:07" "^CP1^^C]C^"

#Break in 1min
execute_on_date "2017-10-19 16:09" "^CP1^^C}C^"

#Break6 16:10 - 16:35
execute_on_date "2017-10-19 16:10" "^CC6^"

#Session 9 in 5min
execute_on_date "2017-10-19 16:30" "^CC6^^C[h^"

#Session 9 in 3min
execute_on_date "2017-10-19 16:32" "^CC6^^C]h^"

#Session 9 in 1min
execute_on_date "2017-10-19 16:34" "^CC6^^C}h^"

#Paper session IX: Better, Faster Apps and Web  16:35 - 17:35
#+talk X
execute_on_date "2017-10-19 16:35" "^CS9^^CtX^"

#Paper session IX: Better, Faster Apps and Web  16:35 - 17:35
#+talk Y
execute_on_date "2017-10-19 16:55" "^CS9^^CtY^"

#Paper session IX: Better, Faster Apps and Web  16:35 - 17:35
#+talk Z
execute_on_date "2017-10-19 17:15" "^CS9^^CtZ^"

#Closing remarks in 5min
execute_on_date "2017-10-19 17:30" "^CS9^^CtZ^^C[i^"

#Closing remarks in 3min
execute_on_date "2017-10-19 17:32" "^CS9^^CtZ^^C]i^"

#Closing remarks in 1min
execute_on_date "2017-10-19 17:34" "^CS9^^CtZ^^C}i^"

#Closing remarks 17:35 - 17:40
execute_on_date "2017-10-19 17:35" "^CX9^"

#EOB
execute_on_date "2017-10-19 17:40" "LoWS"

 





 


