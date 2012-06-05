#!/usr/bin/expect -f

# example: ./ssh_execute.sh password 172.28.3.151 "mkdir /test2"



# set Variables
set password [lindex $argv 0]
set ipaddr [lindex $argv 1]
set scriptname [lindex $argv 2]
set timeout -1
# now connect to remote UNIX box (ipaddr) with given script to execute
spawn ssh $ipaddr "$scriptname"
match_max 100000
# Look for yes/no prompt

while {1} {
	expect {
		# Look for yes/no prompt
		"*?re you sure you want*" {
			send -- "yes\r"
			send -- "\r"
			puts "skipping confirmation"
		}
		# Look for password prompt
		"*?assword:*" {
			send -- "$password\r"
			send -- "\r"
			puts "skipping password"
		}
		eof {break}
	}
}