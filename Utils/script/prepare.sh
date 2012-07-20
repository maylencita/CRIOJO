#!/usr/bin/env bash

## hosts.xml
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

## mappings.xml
# function that takes one parameter: xml_file_path and return the names of the hosts
get_mapping_node_name () {
	xpath $1 "//mapping[server='$2']/node" 2>/dev/null | sed -e 's/<[^>]*>/,/g' | tr ',' '\n' | awk '
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

# function that takes two parameters: server_folder and hostname; it will prepare communications with hornetQ
prepare () {
	echo "Preparing HornetQ $1 in $2..."
	
	ip=$(get_host_ip "hosts.xml" $2)	
	username=$(get_host_username "hosts.xml" $2)
	
	if [ "$usernamea" = "a" ]; then
		username="root"
	fi
	
	password=$(get_host_password "hosts.xml" $2)

	eval "expect ssh_execute.sh $password $username@$ip \"mkdir -p /opt/local/criojo/hornetQ\""
	eval "expect ssh_copy_directory.sh $password $username@$ip hq.zip /opt/local/criojo/hornetQ/hq.zip"
	eval "expect ssh_execute.sh $password $username@$ip \"unzip -d /opt/local/criojo/hornetQ/ -o /opt/local/criojo/hornetQ/hq.zip \""
	eval "expect ssh_execute.sh $password $username@$ip \"mv /opt/local/criojo/hornetQ/horn* /opt/local/criojo/hornetQ/hq \""

	echo "Done!"
}

# function that takes two parameters: server_folder and hostname; it will bridge the hornetQ nodes
bridge () {
	echo "Preparing HornetQ $1 in $2..."

	ip=$(get_host_ip "hosts.xml" $2)
	username=$(get_host_username "hosts.xml" $2)

	if [ "$usernamea" = "a" ]; then
		username="root"
	fi

	password=$(get_host_password "hosts.xml" $2)

	eval "expect ssh_execute.sh $password $username@$ip \"mkdir -p /opt/local/criojo/hornetQ\""
	eval "expect ssh_copy_directory.sh $password $username@$ip hq.zip /opt/local/criojo/hornetQ/hq.zip"
	eval "expect ssh_execute.sh $password $username@$ip \"unzip -d /opt/local/criojo/hornetQ/ -o /opt/local/criojo/hornetQ/hq.zip \""
	eval "expect ssh_execute.sh $password $username@$ip \"mv /opt/local/criojo/hornetQ/horn* /opt/local/criojo/hornetQ/hq \""

	echo "Done!"
}


command_exists () {
    type "$1" &> /dev/null ;
}

if ! command_exists xpath ; then
	apt-get update
    apt-get install -y libxml-xpath-perl
fi

for server in `ls -d */`; do
	
	node=$(get_mapping_node_name "mappings.xml" ${server%%?})
	prepare $server $node
done

echo "" > bridge.txt

for server in `ls -d */`; do

	node=$(get_mapping_node_name "mappings.xml" ${server%%?})
	bridge $server $node
done