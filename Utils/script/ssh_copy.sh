#!/usr/bin/expect -f

# example: ./ssh_copy.sh password 172.28.3.151 test /truc/test



# set Variables
set password [lindex $argv 0]
set ipaddr [lindex $argv 1]
set file1 [lindex $argv 2]
set file2 [lindex $argv 3]
set timeout -1
# now connect to remote UNIX box (ipaddr) with given script to execute
spawn scp $file1 $ipaddr:$file2
match_max 100000

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