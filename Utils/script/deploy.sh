#!/usr/bin/env bash

# function that takes one parameter: xml_file_path and return the names of the hosts
get_hosts_names () {
	xpath $1 "//server/name" 2>/dev/null | sed -e 's/<[^>]*>/,/g' | tr ',' '\n' | awk '
	BEGIN {}
	{
		if($0 != "")
			print $0
	}
	END {}   
	'
}

# function that takes two parameters: xml_file_path and host_name and return the ip of the host
get_host_ip () {
	xpath $1 "//server[name='$2']/ip" 2>/dev/null | sed -e 's/<[^>]*>/,/g' | tr ',' '\n' | awk '
	BEGIN {}
	{
		if($0 != "")
			print $0
	}
	END {}   
	'
}

# function that takes two parameters: xml_file_path and host_name and return the username of the host
get_host_username () {
	xpath $1 "//server[name='$2']/username" 2>/dev/null | sed -e 's/<[^>]*>/,/g' | tr ',' '\n' | awk '
	BEGIN {}
	{
		if($0 != "")
			print $0
	}
	END {}   
	'
}

# function that takes two parameters: xml_file_path and host_name and return the password of the host
get_host_password () {
	xpath $1 "//server[name='$2']/password" 2>/dev/null | sed -e 's/<[^>]*>/,/g' | tr ',' '\n' | awk '
	BEGIN {}
	{
		if($0 != "")
			print $0
	}
	END {}   
	'
}

ssh_copy_id () {
	ID_FILE="${HOME}/.ssh/id_rsa.pub"
	
	if [ "-i" = "$1" ]; then
	  shift
	  # check if we have 2 parameters left, if so the first is the new ID file
	  if [ -n "$2" ]; then
	    if expr "$1" : ".*\.pub" > /dev/null ; then
	      ID_FILE="$1"
	    else
	      ID_FILE="$1.pub"
	    fi
	    shift         # and this should leave $1 as the target name
	  fi
	else
	  if [ x$SSH_AUTH_SOCK != x ] ; then
	    GET_ID="$GET_ID ssh-add -L"
	  fi
	fi
	
	if [ -z "`eval $GET_ID`" ] && [ -r "${ID_FILE}" ] ; then
	  GET_ID="cat ${ID_FILE}"
	fi
	
	if [ -z "`eval $GET_ID`" ]; then
	  echo "$0: ERROR: No identities found" >&2
	  exit 1
	fi
	
	if [ "$#" -lt 1 ] || [ "$1" = "-h" ] || [ "$1" = "--help" ]; then
	  echo "Usage: $0 [-i [identity_file]] [user@]machine" >&2
	  exit 1
	fi
	
	{ eval "$GET_ID" ; } | ssh $1 "umask 077; test -d .ssh || mkdir .ssh ; cat >> .ssh/authorized_keys" || exit 1
}

# function that takes two parameters: server_folder and hostname; it will deploy the server on the host
deploy () {
	echo "Deploying $1 in $2..."
	
	ip=$(get_host_ip "hosts.xml" $2)
	username=$(get_host_username "hosts.xml" $2)
	password=$(get_host_password "hosts.xml" $2)
	echo "connection to the node $1, please type this password: \"$password\""
	`ssh -i "$HOME/.ssh/id_dsa" $username@$ip 'mkdir -p /opt/local/criojo/$1'`
	
	echo "Done!"
}

command_exists () {
    type "$1" &> /dev/null ;
}

if ! command_exists xpath ; then
	apt-get update
    apt-get install -y libxml-xpath-perl
fi

declare -a hosts=()
cpt=0
for server in `get_hosts_names "hosts.xml"`; do
	hosts[$cpt]=$server
	cpt=$((cpt+1))
done

cpt=0
for server in `ls -d server*`; do
	
	deploy $server "${hosts[$cpt]}"
	
	cpt=$((cpt+1))
	cpt=$((cpt%${#hosts[*]}))
done